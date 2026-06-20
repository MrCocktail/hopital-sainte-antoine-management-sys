package metier.entities;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Table(name = "Patient")
public class Patient implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_code")
    private Long patientCode;

    private String nom;
    private String prenom;
    private String adresse;
    private String telephone;

    public Patient() {}

    public Patient(String nom, String prenom, String adresse, String telephone) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
    }

    // Getters et Setters
    public Long getPatientCode() { return patientCode; }
    public void setPatientCode(Long patientCode) { this.patientCode = patientCode; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
}