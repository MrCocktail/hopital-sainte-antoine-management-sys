package metier.entities;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Table(name = "HeureMensuelle")
public class HeureMensuelle implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "heure_mensuelle_code")
    private Long heureMensuelleCode;

    @ManyToOne
    @JoinColumn(name = "employe_code", nullable = false)
    private Employe employe;

    private int mois;    
    private int annee;   
    
    @Column(name = "total_heures")
    private double totalHeures; 

    @Column(name = "salaire_brut_historique")
    private double salaireBrutHistorique; 

    public HeureMensuelle() {}

    public HeureMensuelle(Employe employe, int mois, int annee, double totalHeures, double salaireBrutHistorique) {
        this.employe = employe;
        this.mois = mois;
        this.annee = annee;
        this.totalHeures = totalHeures;
        this.salaireBrutHistorique = salaireBrutHistorique;
    }

    // Getters et Setters
    public Long getHeureMensuelleCode() { return heureMensuelleCode; }
    public void setHeureMensuelleCode(Long heureMensuelleCode) { this.heureMensuelleCode = heureMensuelleCode; }

    public Employe getEmploye() { return employe; }
    public void setEmploye(Employe employe) { this.employe = employe; }

    public int getMois() { return mois; }
    public void setMois(int mois) { this.mois = mois; }

    public int getAnnee() { return annee; }
    public void setAnnee(int annee) { this.annee = annee; }

    public double getTotalHeures() { return totalHeures; }
    public void setTotalHeures(double totalHeures) { this.totalHeures = totalHeures; }

    public double getSalaireBrutHistorique() { return salaireBrutHistorique; }
    public void setSalaireBrutHistorique(double salaireBrutHistorique) { this.salaireBrutHistorique = salaireBrutHistorique; }
}