package metier.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Medecin")
@PrimaryKeyJoinColumn(name = "employe_code")
public class Medecin extends Employe {
    private static final long serialVersionUID = 1L;

    private String specialite;

    public Medecin() {}

    public Medecin(String nom, String prenom, String adresse, String telephone, String poste, double salaireBase, String specialite) {
        super(nom, prenom, adresse, telephone, poste, salaireBase);
        this.specialite = specialite;
    }

    // Getters et Setters
    public String getSpecialite() { return specialite; }
    public void setSpecialite(String specialite) { this.specialite = specialite; }
}