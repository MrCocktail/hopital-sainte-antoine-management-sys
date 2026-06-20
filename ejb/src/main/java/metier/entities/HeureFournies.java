package metier.entities;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "HeureFournie") 
public class HeureFournies implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "heure_fournie_code")
    private Long heureFournieCode;

    @ManyToOne
    @JoinColumn(name = "employe_code", nullable = false)
    private Employe employe;

    // Protection contre le mot-clé réservé SQL 'date'
    @Temporal(TemporalType.DATE)
    @Column(name = "date_travail", nullable = false)
    private Date date;

    @Temporal(TemporalType.TIME)
    @Column(name = "heure_debut", nullable = false)
    private Date heureDebut;

    @Temporal(TemporalType.TIME)
    @Column(name = "heure_fin", nullable = false)
    private Date heureFin;

    // Constructeurs
    public HeureFournies() {}

    public HeureFournies(Employe employe, Date date, Date heureDebut, Date heureFin) {
        this.employe = employe;
        this.date = date;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
    }

    // Getters et Setters
    public Long getHeureFournieCode() { return heureFournieCode; }
    public void setHeureFournieCode(Long heureFournieCode) { this.heureFournieCode = heureFournieCode; }

    public Employe getEmploye() { return employe; }
    public void setEmploye(Employe employe) { this.employe = employe; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public Date getHeureDebut() { return heureDebut; }
    public void setHeureDebut(Date heureDebut) { this.heureDebut = heureDebut; }

    public Date getHeureFin() { return heureFin; }
    public void setHeureFin(Date heureFin) { this.heureFin = heureFin; }
}