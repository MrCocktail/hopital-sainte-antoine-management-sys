package com.hsa.bean;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.inject.Inject;
import metier.entities.Medecin;
import metier.session.IHopitalLocal;
import java.io.Serializable;
import java.util.Date;

@Named
@SessionScoped
public class TimeTrackingBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private IHopitalLocal hopitalEJB;

    @Inject
    private NavigationBean navigationBean;

    private Medecin medecinSelectionne;
    private Date dateTravail = new Date(); // Présélectionne le jour actuel
    private Date heureDebut;
    private Date heureFin;
    private String messageErreur;
    private boolean estJourFerieSelectionne = false;

    public void preparerSaisie(Medecin m) {
        this.medecinSelectionne = m;
        this.dateTravail = new Date(); // Reset à aujourd'hui
        this.heureDebut = null;
        this.heureFin = null;
        this.messageErreur = null;
        this.estJourFerieSelectionne = false;
        
        // Vérification immédiate pour le jour actuel au cas où aujourd'hui serait férié
        verifierDateFerie();
        
        navigationBean.changerVue("medecins-time");
    }

    public void verifierDateFerie() {
        this.messageErreur = null;
        this.estJourFerieSelectionne = false;

        if (dateTravail != null) {
            boolean cocheFerie = hopitalEJB.estUnJourFerie(dateTravail);
            if (cocheFerie) {
                this.messageErreur = "Impossible de sélectionner cette date : C'est un Jour Férié chômé !";
                this.estJourFerieSelectionne = true;
            }
        }
    }

    public void soumettreHeures() {
        try {
            messageErreur = null;
            
            // Sécurité ultime au cas où l'utilisateur contourne l'UI
            if (hopitalEJB.estUnJourFerie(dateTravail)) {
                this.messageErreur = "Action refusée : Cette journée est un jour férié enregistré.";
                this.estJourFerieSelectionne = true;
                return;
            }

            hopitalEJB.enregistrerHeuresQuotidiennes(
                medecinSelectionne.getEmployeCode(), 
                dateTravail, 
                heureDebut, 
                heureFin
            );
            
            navigationBean.changerVue("medecins-all");
        } catch (Exception ex) {
            messageErreur = ex.getMessage();
        }
    }

    // Getters & Setters
    public Medecin getMedecinSelectionne() { return medecinSelectionne; }
    public void setMedecinSelectionne(Medecin medecinSelectionne) { this.medecinSelectionne = medecinSelectionne; }
    public Date getDateTravail() { return dateTravail; }
    public void setDateTravail(Date dateTravail) { this.dateTravail = dateTravail; }
    public Date getHeureDebut() { return heureDebut; }
    public void setHeureDebut(Date heureDebut) { this.heureDebut = heureDebut; }
    public Date getHeureFin() { return heureFin; }
    public void setHeureFin(Date heureFin) { this.heureFin = heureFin; }
    public String getMessageErreur() { return messageErreur; }
    public boolean isEstJourFerieSelectionne() { return estJourFerieSelectionne; }
}