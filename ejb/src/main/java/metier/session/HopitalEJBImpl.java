package metier.session;

import java.util.Date;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import metier.entities.HeureFournies;

@Stateless(name = "HopitalEJB")
public class HopitalEJBImpl implements IHopitalLocal {

    // Injection de l'unité de persistance définie dans ton persistence.xml
    @PersistenceContext(unitName = "hsaPersistenceUnit")
    private EntityManager em;

    @Override
    public void enregistrerHeuresQuotidiennes(String codeMedecin, Date date, Date debut, Date fin) {
        HeureFournies hf = new HeureFournies(codeMedecin, date, debut, fin);
        em.persist(hf); // Sauvegarde automatique dans MySQL via Hibernate
    }

    @Override
    public void calculerHeuresMensuelles(String codeMedecin, int mois, int annee) {
        // La logique de calcul JPQL sera rédigée ici
    }
}