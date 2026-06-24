package com.hsa.bean;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.inject.Inject;
import jakarta.ejb.EJB;
import metier.entities.Employe;
import metier.entities.Medecin;
import metier.session.IHopitalLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Named
@SessionScoped
public class PayrollBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @EJB
    private IHopitalLocal hopitalEJB;

    @Inject
    private NavigationBean navigationBean;

    // Filtres de la Top Bar
    private String filtreType = "ALL"; // ALL, MEDECIN, EMPLOYE
    private int moisSelectionne;
    private int anneeSelectionne;
    private String searchTerm = "";

    // Données du Carousel
    private List<Employe> listeCarousel = new ArrayList<>();
    private int indexCourant = 0;
    private String messageErreur;
    private String messageSucces;

    @PostConstruct
    public void init() {
        // Initialiser par défaut sur le mois et l'année en cours
        Calendar cal = Calendar.getInstance();
        this.moisSelectionne = cal.get(Calendar.MONTH) + 1; // Janvier = 1
        this.anneeSelectionne = cal.get(Calendar.YEAR);
        
        rafraichirCarousel();
    }

    /**
     * Charge et filtre la liste des employés constituant les slides du Carousel
     */
    public void rafraichirCarousel() {
        this.messageErreur = null;
        this.messageSucces = null;
        
        List<Employe> tousLesEmployes;
        if ("MEDECIN".equals(filtreType)) {
            tousLesEmployes = new ArrayList<>(hopitalEJB.listeTousLesMedecins());
        } else if ("EMPLOYE".equals(filtreType)) {
            tousLesEmployes = hopitalEJB.listeTousLesEmployes();
        } else {
            tousLesEmployes = new ArrayList<>();
            tousLesEmployes.addAll(hopitalEJB.listeTousLesEmployes());
            tousLesEmployes.addAll(hopitalEJB.listeTousLesMedecins());
        }

        // Filtrage textuel (Recherche Nom / Prénom)
        this.listeCarousel = new ArrayList<>();
        for (Employe emp : tousLesEmployes) {
            if (searchTerm == null || searchTerm.trim().isEmpty()) {
                this.listeCarousel.add(emp);
            } else {
                String nomComplet = (emp.getPrenom() + " " + emp.getNom()).toLowerCase();
                if (nomComplet.contains(searchTerm.toLowerCase().trim())) {
                    this.listeCarousel.add(emp);
                }
            }
        }

        // Réinitialisation de l'index de sécurité
        if (indexCourant >= listeCarousel.size()) {
            indexCourant = 0;
        }
    }

    public void passerSuivant() {
        if (!listeCarousel.isEmpty()) {
            indexCourant = (indexCourant + 1) % listeCarousel.size();
            this.messageErreur = null;
            this.messageSucces = null;
        }
    }

    public void passerPrecedent() {
        if (!listeCarousel.isEmpty()) {
            indexCourant = (indexCourant - 1 + listeCarousel.size()) % listeCarousel.size();
            this.messageErreur = null;
            this.messageSucces = null;
        }
    }

    public Employe getEmployeCourant() {
        if (listeCarousel == null || listeCarousel.isEmpty() || indexCourant < 0 || indexCourant >= listeCarousel.size()) {
            return null;
        }
        return listeCarousel.get(indexCourant);
    }

    public boolean isMedecin(Employe e) {
        return e instanceof Medecin;
    }

    public double getSalaireBrutSimule() {
        Employe courant = getEmployeCourant();
        if (courant == null) return 0.0;
        return hopitalEJB.calculerSalaireBrutSimule(courant.getEmployeCode(), moisSelectionne, anneeSelectionne);
    }

    public double getHeuresCumuleesSimulees() {
        Employe courant = getEmployeCourant();
        if (courant == null || !(courant instanceof Medecin)) return 0.0;
        return hopitalEJB.obtenirHeuresMedecinParMois(courant.getEmployeCode(), moisSelectionne, anneeSelectionne);
    }

    public boolean isFicheDejaValidee() {
        Employe courant = getEmployeCourant();
        if (courant == null) return false;
        return hopitalEJB.estPayrollValide(courant.getEmployeCode(), moisSelectionne, anneeSelectionne);
    }

    public void validerFicheCourante() {
        Employe courant = getEmployeCourant();
        if (courant == null) return;

        try {
            hopitalEJB.calculerHeuresMensuelles(courant.getEmployeCode(), moisSelectionne, anneeSelectionne);
            this.messageSucces = "Le bulletin de paie a été scellé et archivé avec succès !";
            this.messageErreur = null;
        } catch (Exception ex) {
            this.messageErreur = "Erreur lors de la validation : " + ex.getMessage();
            this.messageSucces = null;
        }
    }

    // À AJOUTER dans PayrollBean.java (sous la méthode init)
public void onPageLoad() {
    // Si la liste est déjà filtrée par une recherche en cours, 
    // on préserve le terme mais on force la relecture de la BDD
    rafraichirCarousel();
}

    // --- Getters & Setters ---
    public String getFiltreType() { return filtreType; }
    public void setFiltreType(String filtreType) { this.filtreType = filtreType; }
    public int getMoisSelectionne() { return moisSelectionne; }
    public void setMoisSelectionne(int moisSelectionne) { this.moisSelectionne = moisSelectionne; }
    public int getAnneeSelectionne() { return anneeSelectionne; }
    public void setAnneeSelectionne(int anneeSelectionne) { this.anneeSelectionne = anneeSelectionne; }
    public String getSearchTerm() { return searchTerm; }
    public void setSearchTerm(String searchTerm) { this.searchTerm = searchTerm; }
    public List<Employe> getListeCarousel() { return listeCarousel; }
    public int getIndexCourant() { return indexCourant; }
    public String getMessageErreur() { return messageErreur; }
    public String getMessageSucces() { return messageSucces; }
}