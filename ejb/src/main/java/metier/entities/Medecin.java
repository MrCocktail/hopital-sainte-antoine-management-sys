package metier.entities;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Table(name = "Medecins")
public class Medecin implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "code")
    private String code; // Identifiant unique (ex: MED-001)

    private String nom;
    private String prenom;
    private String specialite;
    private double tarifHoraire; // Nécessaire pour le calcul du payroll basé sur les heures

    // Constructeurs
    public Medecin() {}

    public Medecin(String code, String nom, String prenom, String specialite, double tarifHoraire) {
        this.code = code;
        this.nom = nom;
        this.prenom = prenom;
        this.specialite = specialite;
        this.tarifHoraire = tarifHoraire;
    }

    // Getters et Setters
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getSpecialite() { return specialite; }
    public void setSpecialite(String specialite) { this.specialite = specialite; }

    public double getTarifHoraire() { return tarifHoraire; }
    public void setTarifHoraire(double tarifHoraire) { this.tarifHoraire = tarifHoraire; }
}