package com.hsa.bean;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.inject.Inject;
import jakarta.faces.event.ComponentSystemEvent;
import metier.entities.Employe;
import metier.session.IHopitalLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class EmployeBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private IHopitalLocal hopitalEJB;

    @Inject
    private NavigationBean navigationBean;

    private Employe nouveauEmploye = new Employe();
    private Employe employeSelectionne = new Employe();
    private String searchTerm = "";
    private List<Employe> listeEmployes = new ArrayList<>();

    @PostConstruct
    public void init() {
        rafraichirListe();
    }

    public void rafraichirListe() {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            listeEmployes = hopitalEJB.listeTousLesEmployes();
        } else {
            listeEmployes = hopitalEJB.rechercheEmployes(searchTerm);
        }
    }

    public void rafraichirListe(ComponentSystemEvent event) {
        rafraichirListe();
    }

    public void rechercher() {
        rafraichirListe();
    }

    public void enregistrerEmploye() {
        hopitalEJB.ajouterEmploye(nouveauEmploye);
        nouveauEmploye = new Employe();
        rafraichirListe();
        navigationBean.changerVue("employees-all");
    }

    public void preparerEdition(Employe e) {
        this.employeSelectionne = e;
        navigationBean.changerVue("employees-edit");
    }

    public void mettreAJourEmploye() {
        if (employeSelectionne != null) {
            hopitalEJB.modifierEmploye(employeSelectionne);
            rafraichirListe();
            navigationBean.changerVue("employees-all");
        }
    }

    public void supprimerEmploye(Employe e) {
        if (e != null) {
            hopitalEJB.supprimerEmploye(e.getEmployeCode());
            rafraichirListe();
        }
    }

    // Getters et Setters
    public Employe getNouveauEmploye() { return nouveauEmploye; }
    public void setNouveauEmploye(Employe nouveauEmploye) { this.nouveauEmploye = nouveauEmploye; }
    public Employe getEmployeSelectionne() { return employeSelectionne; }
    public void setEmployeSelectionne(Employe employeSelectionne) { this.employeSelectionne = employeSelectionne; }
    public String getSearchTerm() { return searchTerm; }
    public void setSearchTerm(String searchTerm) { this.searchTerm = searchTerm; }
    public List<Employe> getListeEmployes() { return listeEmployes; }
    public void setListeEmployes(List<Employe> listeEmployes) { this.listeEmployes = listeEmployes; }
}