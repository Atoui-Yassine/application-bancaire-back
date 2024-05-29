package vermeg.com.applicationbancaire.Models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="Contrat")
public class ContratModel {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private String codecontrat;
    private Date datedebut;
    private Date datefin;

    public ContratModel(Long id, String codecontrat, Date datedebut, Date datefin) {
        this.id = id;
        this.codecontrat = codecontrat;
        this.datedebut = datedebut;
        this.datefin = datefin;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCodecontrat(String codecontrat) {
        this.codecontrat = codecontrat;
    }

    public void setDatedebut(Date datedebut) {
        this.datedebut = datedebut;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    public ContratModel() {
    }

    public Long getId() {
        return id;
    }

    public String getCodecontrat() {
        return codecontrat;
    }

    public Date getDatedebut() {
        return datedebut;
    }

    public Date getDatefin() {
        return datefin;
    }
}