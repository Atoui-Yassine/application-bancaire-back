package vermeg.com.applicationbancaire.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name="Pidmodel")
public class PIDModel extends PJModel {
    private String numcin;
    private LocalDate datededélivrance;
    private LocalDate datedenaissance;
    private LocalDate datedevalidité;
    private String lieudedélivrance;

    public String getNumcin() {
        return numcin;
    }

    public void setNumcin(String numcin) {
        this.numcin = numcin;
    }
    public String getLieudedélivrance() {
        return lieudedélivrance;
    }

    public void setLieudedélivrance(String lieudedélivrance) {
        this.lieudedélivrance = lieudedélivrance;
    }

    public PIDModel() {
    }


    public PIDModel(String byle, String numcin, LocalDate datededélivrance, LocalDate datedenaissance, LocalDate datedevalidité, String lieudedélivrance) {
        super(byle);
        this.numcin = numcin;
        this.datededélivrance = datededélivrance;
        this.datedenaissance = datedenaissance;
        this.datedevalidité = datedevalidité;
        this.lieudedélivrance = lieudedélivrance;
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
}
