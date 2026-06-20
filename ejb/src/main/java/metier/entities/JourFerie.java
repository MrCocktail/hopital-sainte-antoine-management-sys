package metier.entities;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "JourFerie")
public class JourFerie implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "jour_ferie_code")
    private Long jourFerieCode;

    @Temporal(TemporalType.DATE)
    @Column(unique = true, nullable = false)
    private Date date;

    private String description; // Ex: "Fête du Drapeau"

    public JourFerie() {}

    public JourFerie(Date date, String description) {
        this.date = date;
        this.description = description;
    }

    // Getters et Setters
    public Long getJourFerieCode() { return jourFerieCode; }
    public void setJourFerieCode(Long jourFerieCode) { this.jourFerieCode = jourFerieCode; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}