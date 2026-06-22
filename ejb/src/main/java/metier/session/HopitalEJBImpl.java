package metier.session;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import metier.entities.Employe;
import metier.entities.HeureFournies;
import metier.entities.HeureMensuelle;
import metier.entities.Patient;

@Stateless(name = "HopitalEJB")
public class HopitalEJBImpl implements IHopitalLocal {

    @PersistenceContext(unitName = "hsaPersistenceUnit")
    private EntityManager em;

    @Override
    public List<Patient> listeTousLesPatients() {
        return em.createQuery("SELECT p FROM Patient p WHERE p.patientCode IS NOT NULL", Patient.class).getResultList();
    }

    @Override
    public void ajouterPatient(Patient p) {
        em.persist(p);
    }

    @Override
    public void modifierPatient(Patient p) {
        if (p != null) {
            em.merge(p);
        }
    }

    @Override
    public void supprimerPatient(Long code) {
        Patient p = em.find(Patient.class, code);
        if (p != null) {
            em.remove(p);
        }
    }

    @Override
    public void enregistrerHeuresQuotidiennes(Long employeCode, Date date, Date debut, Date fin) {
        Employe emp = em.find(Employe.class, employeCode);
        if (emp != null) {
            HeureFournies hf = new HeureFournies(emp, date, debut, fin);
            em.persist(hf);
        } else {
            throw new IllegalArgumentException("Aucun employé trouvé avec le code : " + employeCode);
        }
    }

    @Override
    public void calculerHeuresMensuelles(Long employeCode, int mois, int annee) {
        // 1. Récupérer l'employé
        Employe emp = em.find(Employe.class, employeCode);
        if (emp == null) {
            throw new IllegalArgumentException("Aucun employé trouvé avec le code : " + employeCode);
        }

        // 2. Récupérer toutes les présences de l'employé pour le mois et l'année spécifiés
        TypedQuery<HeureFournies> queryHeures = em.createQuery(
            "SELECT h FROM HeureFournies h WHERE h.employe.employeCode = :empCode " +
            "AND FUNCTION('MONTH', h.date) = :mois " +
            "AND FUNCTION('YEAR', h.date) = :annee", 
            HeureFournies.class
        );
        queryHeures.setParameter("empCode", employeCode);
        queryHeures.setParameter("mois", mois);
        queryHeures.setParameter("annee", annee);
        List<HeureFournies> listeHeures = queryHeures.getResultList();

        // 3. Récupérer les dates des jours fériés pour ce mois et cette année
        TypedQuery<Date> queryFeries = em.createQuery(
            "SELECT j.date FROM JourFerie j WHERE FUNCTION('MONTH', j.date) = :mois " +
            "AND FUNCTION('YEAR', j.date) = :annee", 
            Date.class
        );
        queryFeries.setParameter("mois", mois);
        queryFeries.setParameter("annee", annee);
        List<Date> listeDatesFeries = queryFeries.getResultList();

        double totalHeuresCumulees = 0.0;
        double salaireBrutGlobal = 0.0;
        double salaireHoraireBase = emp.getSalaireBase();

        // 4. Boucle de calcul algorithmique
        for (HeureFournies hf : listeHeures) {
            // Conversion java.util.Date -> java.time.LocalTime pour un calcul propre
            LocalTime tDebut = hf.getHeureDebut().toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
            LocalTime tFin = hf.getHeureFin().toInstant().atZone(ZoneId.systemDefault()).toLocalTime();

            // Soustraction brute du temps passé (en minutes)
            long minutesTravaillees = ChronoUnit.MINUTES.between(tDebut, tFin);
            double heuresDuJour = minutesTravaillees / 60.0;

            // Cumuler les heures réelles de présence
            totalHeuresCumulees += heuresDuJour;

            // Vérification si la date travaillée était un jour férié
            boolean estFerie = false;
            for (Date dateFerie : listeDatesFeries) {
                if (hf.getDate().compareTo(dateFerie) == 0) {
                    estFerie = true;
                    break;
                }
            }

            // Application de la règle de paie (Taux normal vs Taux Double)
            if (estFerie) {
                salaireBrutGlobal += heuresDuJour * (salaireHoraireBase * 2.0);
            } else {
                salaireBrutGlobal += heuresDuJour * salaireHoraireBase;
            }
        }

        // 5. Archivage immuable dans HeureMensuelle (Fiche de Paie)
        HeureMensuelle hm = new HeureMensuelle(emp, mois, annee, totalHeuresCumulees, salaireBrutGlobal);
        em.persist(hm);
    }
}