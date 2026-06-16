package metier.entities;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Table(name = "Patients")
public class Patient implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "code")
    private String code; // Identifiant unique (ex: PAT-001)

    private String nom;
    private String prenom;
    private String telephone;

    // Constructeurs
    public Patient() {}

    public Patient(String code, String nom, String prenom, String telephone) {
        this.code = code;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
    }

    // Getters et Setters
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
}