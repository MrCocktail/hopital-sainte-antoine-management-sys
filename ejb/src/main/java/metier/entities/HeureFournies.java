package metier.entities;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "HeureFournies")
public class HeureFournies implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    private String code; 

    @Temporal(TemporalType.DATE)
    private Date date; 

    @Temporal(TemporalType.TIME)
    private Date heure_debut; 

    @Temporal(TemporalType.TIME)
    private Date heure_fin; 

    // Constructeurs
    public HeureFournies() {}

    public HeureFournies(String code, Date date, Date heure_debut, Date heure_fin) {
        this.code = code;
        this.date = date;
        this.heure_debut = heure_debut;
        this.heure_fin = heure_fin;
    }

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public Date getHeure_debut() { return heure_debut; }
    public void setHeure_debut(Date heure_debut) { this.heure_debut = heure_debut; }

    public Date getHeure_fin() { return heure_fin; }
    public void setHeure_fin(Date heure_fin) { this.heure_fin = heure_fin; }
}