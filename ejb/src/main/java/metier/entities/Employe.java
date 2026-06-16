package metier.entities;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Table(name = "Employes")
public class Employe implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "code")
    private String code; // Identifiant unique (ex: EMP-001)

    private String nom;
    private String prenom;
    private String poste;
    private double salaireBase; // Nécessaire pour afficher le payroll des employés

    // Constructeurs
    public Employe() {}

    public Employe(String code, String nom, String prenom, String poste, double salaireBase) {
        this.code = code;
        this.nom = nom;
        this.prenom = prenom;
        this.poste = poste;
        this.salaireBase = salaireBase;
    }

    // Getters et Setters
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getPoste() { return poste; }
    public void setPoste(String poste) { this.poste = poste; }

    public double getSalaireBase() { return salaireBase; }
    public void setSalaireBase(double salaireBase) { this.salaireBase = salaireBase; }
}