package metier.session;

import java.util.Date;
import jakarta.ejb.Local;

@Local
public interface IHopitalLocal {
    // Fonctionnalité 4 : Saisie journalière des heures d'un médecin
    void enregistrerHeuresQuotidiennes(String codeMedecin, Date date, Date debut, Date fin);
    
    // Fonctionnalité 6 : Évaluer et calculer les heures mensuelles avant le Payroll
    void calculerHeuresMensuelles(String codeMedecin, int mois, int annee);
}