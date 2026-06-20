package metier.entities;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "Consultation")
public class Consultation implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "consultation_code")
    private Long consultationCode;

    @ManyToOne
    @JoinColumn(name = "patient_code", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "medecin_code", referencedColumnName = "employe_code", nullable = false)
    private Medecin medecin;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_consultation")
    private Date dateConsultation;

    @Lob
    private String notes;

    public Consultation() {}

    public Consultation(Patient patient, Medecin medecin, Date dateConsultation, String notes) {
        this.patient = patient;
        this.medecin = medecin;
        this.dateConsultation = dateConsultation;
        this.notes = notes;
    }

    // Getters et Setters
    public Long getConsultationCode() { return consultationCode; }
    public void setConsultationCode(Long consultationCode) { this.consultationCode = consultationCode; }

    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }

    public Medecin getMedecin() { return medecin; }
    public void setMedecin(Medecin medecin) { this.medecin = medecin; }

    public Date getDateConsultation() { return dateConsultation; }
    public void setDateConsultation(Date dateConsultation) { this.dateConsultation = dateConsultation; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}