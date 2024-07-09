package vermeg.com.applicationbancaire.Models;

import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name="Pidmodel")
public class PIDModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numcin;
    private LocalDate datededélivrance;
    private LocalDate datedenaissance;
    private LocalDate datedevalidité;
    private String lieudedélivrance;
    private String photoCINrecto;
    private String photoCINverso;


    public String getNumcin() {
        return numcin;
    }

    public void setNumcin(String numcin) {
        this.numcin = numcin;
    }

    public LocalDate getDatededélivrance() {
        return datededélivrance;
    }

    public void setDatededélivrance(LocalDate datededélivrance) {
        this.datededélivrance = datededélivrance;
    }

    public LocalDate getDatedenaissance() {
        return datedenaissance;
    }

    public void setDatedenaissance(LocalDate datedenaissance) {
        this.datedenaissance = datedenaissance;
    }

    public LocalDate getDatedevalidité() {
        return datedevalidité;
    }

    public void setDatedevalidité(LocalDate datedevalidité) {
        this.datedevalidité = datedevalidité;
    }

    public String getLieudedélivrance() {
        return lieudedélivrance;
    }

    public void setLieudedélivrance(String lieudedélivrance) {
        this.lieudedélivrance = lieudedélivrance;
    }

    public String getPhotoCINrecto() {
        return photoCINrecto;
    }

    public void setPhotoCINrecto(String photoCINrecto) {
        this.photoCINrecto = photoCINrecto;
    }

    public String getPhotoCINverso() {
        return photoCINverso;
    }

    public void setPhotoCINverso(String photoCINverso) {
        this.photoCINverso = photoCINverso;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PIDModel() {
    }

    public PIDModel(String numcin, LocalDate datededélivrance, LocalDate datedenaissance, LocalDate datedevalidité, String lieudedélivrance, String photoCINrecto, String photoCINverso) {
        this.numcin = numcin;
        this.datededélivrance = datededélivrance;
        this.datedenaissance = datedenaissance;
        this.datedevalidité = datedevalidité;
        this.lieudedélivrance = lieudedélivrance;
        this.photoCINrecto = photoCINrecto;
        this.photoCINverso = photoCINverso;
    }
}
