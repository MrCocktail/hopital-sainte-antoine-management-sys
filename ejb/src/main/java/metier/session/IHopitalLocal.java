package metier.session;

import java.util.Date;
import java.util.List;
import jakarta.ejb.Local;
import metier.entities.Patient;

@Local
public interface IHopitalLocal {
    // Fonctionnalité 4 : Saisie journalière des heures d'un employé/médecin via son code Long
    void enregistrerHeuresQuotidiennes(Long employeCode, Date date, Date debut, Date fin);
    
    // Fonctionnalité 6 : Évaluer et calculer les heures mensuelles avant le Payroll
    void calculerHeuresMensuelles(Long employeCode, int mois, int annee);

    // Gestion des Patients
    List<Patient> listeTousLesPatients();
    List<Patient> recherchePatients(String query);
    void ajouterPatient(Patient p);
    void modifierPatient(Patient p);
    void supprimerPatient(Long code);
}