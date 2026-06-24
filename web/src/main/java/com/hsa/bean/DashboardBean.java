package com.hsa.bean;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.ejb.EJB;
import metier.session.IHopitalLocal;
import java.io.Serializable;
import java.util.Calendar;

@Named
@RequestScoped
public class DashboardBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @EJB
    private IHopitalLocal hopitalEJB;

    private long totalMedecins;
    private long totalEmployes;
    private long totalPatients;
    private long fichesValideesCeMois;

    @PostConstruct
    public void init() {
        // Récupération des vraies données de la BDD
        this.totalMedecins = hopitalEJB.listeTousLesMedecins().size();
        this.totalEmployes = hopitalEJB.listeTousLesEmployes().size();
        this.totalPatients = hopitalEJB.listeTousLesPatients().size();
        
        // Calcul du mois/année en cours pour les fiches de paie validées
        Calendar cal = Calendar.getInstance();
        int mois = cal.get(Calendar.MONTH) + 1;
        int annee = cal.get(Calendar.YEAR);
        
        try {
            // Compte les fiches dans HeureMensuelle pour la période actuelle
            this.fichesValideesCeMois = hopitalEJB.compterFichesPaieValidees(mois, annee);
        } catch (Exception e) {
            this.fichesValideesCeMois = 0;
        }
    }

    public double getPourcentageMedecins() {
    long total = totalMedecins + totalEmployes;
    if (total == 0) return 0.0;
    return (totalMedecins * 100.0) / total;
    }

    public double getPourcentageEmployes() {
        long total = totalMedecins + totalEmployes;
        if (total == 0) return 0.0;
        return (totalEmployes * 100.0) / total;
    }

    // --- Getters ---
    public long getTotalMedecins() { return totalMedecins; }
    public long getTotalEmployes() { return totalEmployes; }
    public long getTotalPatients() { return totalPatients; }
    public long getFichesValideesCeMois() { return fichesValideesCeMois; }
    
    // Valeurs statiques réalistes pour compléter l'infrastructure
    public int getTotalLits() { return 250; }
    public int getAmbulancesDispo() { return 4; }
}