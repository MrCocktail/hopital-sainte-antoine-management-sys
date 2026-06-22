package com.hsa.bean;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.inject.Inject;
import jakarta.faces.event.ComponentSystemEvent;
import metier.entities.Medecin;
import metier.session.IHopitalLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class MedecinBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private IHopitalLocal hopitalEJB;

    @Inject
    private NavigationBean navigationBean;

    private Medecin nouveauMedecin = new Medecin();
    private Medecin medecinSelectionne = new Medecin();
    private String searchTerm = "";
    private List<Medecin> listeMedecins = new ArrayList<>();

    @PostConstruct
    public void init() {
        rafraichirListe();
    }

    public void rafraichirListe() {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            listeMedecins = hopitalEJB.listeTousLesMedecins();
        } else {
            listeMedecins = hopitalEJB.rechercheMedecins(searchTerm);
        }
    }

    public void rafraichirListe(ComponentSystemEvent event) {
        rafraichirListe();
    }

    public void rechercher() {
        rafraichirListe();
    }

    public void enregistrerMedecin() {
        hopitalEJB.ajouterMedecin(nouveauMedecin);
        nouveauMedecin = new Medecin();
        rafraichirListe();
        navigationBean.changerVue("medecins-all");
    }

    public void preparerEdition(Medecin m) {
        this.medecinSelectionne = m;
        navigationBean.changerVue("medecins-edit");
    }

    public void mettreAJourMedecin() {
        if (medecinSelectionne != null) {
            hopitalEJB.modifierMedecin(medecinSelectionne);
            rafraichirListe();
            navigationBean.changerVue("medecins-all");
        }
    }

    public void supprimerMedecin(Medecin m) {
        if (m != null) {
            hopitalEJB.supprimerMedecin(m.getEmployeCode());
            rafraichirListe();
        }
    }

    public Medecin getNouveauMedecin() { return nouveauMedecin; }
    public void setNouveauMedecin(Medecin nouveauMedecin) { this.nouveauMedecin = nouveauMedecin; }
    public Medecin getMedecinSelectionne() { return medecinSelectionne; }
    public void setMedecinSelectionne(Medecin medecinSelectionne) { this.medecinSelectionne = medecinSelectionne; }
    public List<Medecin> getListeMedecins() { return listeMedecins; }
    public String getSearchTerm() { return searchTerm; }
    public void setSearchTerm(String searchTerm) { this.searchTerm = searchTerm; }
}
