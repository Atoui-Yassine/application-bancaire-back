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
    @ManyToOne
    @JoinColumn(name="clientid")
    private ClientModel clientmap;

    public ClientModel getClientmap() {
        return clientmap;
    }

    public void setClientmap(ClientModel clientmap) {
        this.clientmap = clientmap;
    }

    public ContratModel( String codecontrat, Date datedebut, Date datefin) {

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
    @OneToOne(cascade =CascadeType.ALL)
    @JoinColumn(name = "idclient")
    private ClientModel clientModel;

    public ClientModel getClientModel() {
        return clientModel;
    }

    public void setClientModel(ClientModel clientModel) {
        this.clientModel = clientModel;
    }


    @OneToOne(cascade =CascadeType.ALL)
    @JoinColumn(name = "idPJ")
    private PJModel piecejustfs;

    public PJModel getPiecejustfs() {
        return piecejustfs;
    }

    public void setPiecejustfs(PJModel piecejustfs) {
        this.piecejustfs = piecejustfs;
    }

    @OneToOne(cascade =CascadeType.ALL)
    @JoinColumn(name = "idPID")
    private PIDModel pieceidentites;

    public PIDModel getPieceidentites() {
        return pieceidentites;
    }

    public void setPieceidentites(PIDModel pieceidentites) {
        this.pieceidentites = pieceidentites;
    }

    @OneToOne(cascade =CascadeType.ALL)
    @JoinColumn(name = "idCGU")
    private CGUModel conditiongenerales;

    public CGUModel getConditiongenerales() {
        return conditiongenerales;
    }

    public void setConditiongenerales(CGUModel conditiongenerales) {
        this.conditiongenerales = conditiongenerales;
    }
}