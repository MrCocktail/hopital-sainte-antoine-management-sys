package com.hsa.bean;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.inject.Inject;
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

    // Propriétés pour le formulaire
    private Patient nouveauPatient = new Patient();
    private Patient patientSelectionne = new Patient();
    private List<Patient> listePatients = new ArrayList<>();
    
    // Contrôle de la vue active : 'dashboard', 'all', 'add', 'edit'
    private String vueActive = "dashboard";
    
    // Contrôle de l'ouverture du menu déroulant (dropdown) dans l'aside
    private boolean dropdownOuvert = false;

    @PostConstruct
    public void init() {
        rafraichirListe();
    }

    public void rafraichirListe() {
        // TODO: Ajouter une méthode de récupération dans l'EJB, ou simuler pour le moment
        // listePatients = hopitalEJB.listeTousLesPatients();
        
        // Simulation temporaire basée sur tes données SQL de test si l'EJB n'est pas encore prêt
        if(listePatients.isEmpty()) {
            listePatients.add(new Patient("Casimir", "Woodline", "Avenue Poupelard, Port-au-Prince", "+509 3445-8821"));
            listePatients.add(new Patient("Clermont", "Jude", "Rue Chrétien, Nazon", "+509 4211-0093"));
            listePatients.add(new Patient("Saint-Fleur", "Anise", "Lalue, Port-au-Prince", "+509 3117-2245"));
        }
    }

    // Actions du CRUD
    public void changerVue(String nouvelleVue) {
        this.vueActive = nouvelleVue;
        if ("all".equals(nouvelleVue)) {
            rafraichirListe();
        }
    }

    public void basculerDropdown() {
        this.dropdownOuvert = !this.dropdownOuvert;
    }

    public void enregistrerPatient() {
        // hopitalEJB.ajouterPatient(nouveauPatient);
        listePatients.add(nouveauPatient); // Simulation locale
        nouveauPatient = new Patient(); // Reset formulaire
        changerVue("all");
    }

    public void preparerEdition(Patient p) {
        this.patientSelectionne = p;
        changerVue("edit");
    }

    public void mettreAJourPatient() {
        // hopitalEJB.modifierPatient(patientSelectionne);
        changerVue("all");
    }

    public void supprimerPatient(Patient p) {
        // hopitalEJB.supprimerPatient(p.getCode());
        listePatients.remove(p);
    }

    // Getters / Setters
    public String getVueActive() { return vueActive; }
    public void setVueActive(String vueActive) { this.vueActive = vueActive; }
    public boolean isDropdownOuvert() { return dropdownOuvert; }
    public void setDropdownOuvert(boolean dropdownOuvert) { this.dropdownOuvert = dropdownOuvert; }
    public Patient getNouveauPatient() { return nouveauPatient; }
    public void setNouveauPatient(Patient nouveauPatient) { this.nouveauPatient = nouveauPatient; }
    public Patient getPatientSelectionne() { return patientSelectionne; }
    public void setPatientSelectionne(Patient patientSelectionne) { this.patientSelectionne = patientSelectionne; }
    public List<Patient> getListePatients() { return listePatients; }
}