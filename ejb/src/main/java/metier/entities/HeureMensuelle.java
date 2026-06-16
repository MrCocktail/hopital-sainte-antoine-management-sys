package metier.entities;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Table(name = "HeureMensuelle")
public class HeureMensuelle implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code; // Code du médecin concerné 
    private int mois;    // Ex: 6 pour Juin
    private int annee;   // Ex: 2026
    private double nb_heure; // Total des heures calculées pour ce mois 

    // Constructeurs
    public HeureMensuelle() {}

    public HeureMensuelle(String code, int mois, int annee, double nb_heure) {
        this.code = code;
        this.mois = mois;
        this.annee = annee;
        this.nb_heure = nb_heure;
    }

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public int getMois() { return mois; }
    public void setMois(int mois) { this.mois = mois; }

    public int getAnnee() { return annee; }
    public void setAnnee(int annee) { this.annee = annee; }

    public double getNb_heure() { return nb_heure; }
    public void setNb_heure(double nb_heure) { this.nb_heure = nb_heure; }
}