package com.hsa.bean;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.Date;
import metier.session.IHopitalLocal;

@Named
@RequestScoped
public class PayrollBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @EJB
    private IHopitalLocal hopitalEJB;

    // Champs pour le formulaire d'heures quotidiennes
    private Long employeCode;
    private Date dateTravail;
    private Date heureDebut;
    private Date heureFin;

    // Champs pour le calcul mensuel
    private Long empCodeCalcul;
    private int mois;
    private int annee;

    // Méthode d'action pour enregistrer les heures
    public String enregistrer() {
        try {
            hopitalEJB.enregistrerHeuresQuotidiennes(employeCode, dateTravail, heureDebut, heureFin);
            // Vous pouvez ajouter un message de succès PrimeFaces ici
            return "index?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Méthode d'action pour clore le mois
    public String cloreMois() {
        try {
            hopitalEJB.calculerHeuresMensuelles(empCodeCalcul, mois, annee);
            return "index?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // --- Getters et Setters ---
    public Long getEmployeCode() { return employeCode; }
    public void setEmployeCode(Long employeCode) { this.employeCode = employeCode; }

    public Date getDateTravail() { return dateTravail; }
    public void setDateTravail(Date dateTravail) { this.dateTravail = dateTravail; }

    public Date getHeureDebut() { return heureDebut; }
    public void setHeureDebut(Date heureDebut) { this.heureDebut = heureDebut; }

    public Date getHeureFin() { return heureFin; }
    public void setHeureFin(Date heureFin) { this.heureFin = heureFin; }

    public Long getEmpCodeCalcul() { return empCodeCalcul; }
    public void setEmpCodeCalcul(Long empCodeCalcul) { this.empCodeCalcul = empCodeCalcul; }

    public int getMois() { return mois; }
    public void setMois(int mois) { this.mois = mois; }

    public int getAnnee() { return annee; }
    public void setAnnee(int annee) { this.annee = annee; }
}