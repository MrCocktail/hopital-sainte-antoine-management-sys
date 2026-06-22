package metier.entities;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Table(name = "Employe")
@Inheritance(strategy = InheritanceType.JOINED)
public class Employe implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employe_code")
    private Long employeCode;

    private String nom;
    private String prenom;
    private String adresse;
    private String telephone;
    private String poste;
    
    @Column(name = "salaire_base")
    private double salaireBase; 

    public Employe() {}

    public Employe(String nom, String prenom, String adresse, String telephone, String poste, double salaireBase) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.poste = poste;
        this.salaireBase = salaireBase;
    }

    // Getters et Setters
    public Long getEmployeCode() { return employeCode; }
    public void setEmployeCode(Long employeCode) { this.employeCode = employeCode; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    public String getPoste() { return poste; }
    public void setPoste(String poste) { this.poste = poste; }

    public double getSalaireBase() { return salaireBase; }
    public void setSalaireBase(double salaireBase) { this.salaireBase = salaireBase; }
}