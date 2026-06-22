package com.hsa.bean;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.inject.Inject;
import jakarta.faces.event.ComponentSystemEvent;
import metier.entities.Patient;
import metier.session.IHopitalLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class PatientBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private IHopitalLocal hopitalEJB;

    @Inject
    private NavigationBean navigationBean;

    // Propriétés pour le formulaire
    private Patient nouveauPatient = new Patient();
    private Patient patientSelectionne = new Patient();
    private List<Patient> listePatients = new ArrayList<>();

    @PostConstruct
    public void init() {
        rafraichirListe();
    }

    public void rafraichirListe() {
        listePatients = hopitalEJB.listeTousLesPatients();
    }

    public void rafraichirListe(ComponentSystemEvent event) {
        rafraichirListe();
    }

    public void enregistrerPatient() {
        hopitalEJB.ajouterPatient(nouveauPatient);
        nouveauPatient = new Patient(); // Reset formulaire
        rafraichirListe();
        navigationBean.changerVue("patients-all");
    }

    public void preparerEdition(Patient p) {
        this.patientSelectionne = p;
        navigationBean.changerVue("patients-edit");
    }

    public void mettreAJourPatient() {
        if (patientSelectionne != null) {
            hopitalEJB.modifierPatient(patientSelectionne);
            rafraichirListe();
            navigationBean.changerVue("patients-all");
        }
    }

    public void supprimerPatient(Patient p) {
        if (p != null) {
            hopitalEJB.supprimerPatient(p.getPatientCode());
            rafraichirListe();
        }
    }

    // Getters / Setters
    public Patient getNouveauPatient() { return nouveauPatient; }
    public void setNouveauPatient(Patient nouveauPatient) { this.nouveauPatient = nouveauPatient; }
    public Patient getPatientSelectionne() { return patientSelectionne; }
    public void setPatientSelectionne(Patient patientSelectionne) { this.patientSelectionne = patientSelectionne; }
    public List<Patient> getListePatients() { return listePatients; }
}