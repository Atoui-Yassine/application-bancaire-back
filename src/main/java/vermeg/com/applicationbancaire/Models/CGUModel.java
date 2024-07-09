package vermeg.com.applicationbancaire.Models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="CGU")
public class CGUModel  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//héritage car classe mère
    private Long id;
    private Date date ;
    private String signature ;
    private String nombredepages;
    private String type ;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getNombredepages() {
        return nombredepages;
    }

    public void setNombredepages(String nombredepages) {
        this.nombredepages = nombredepages;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public CGUModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CGUModel(Date date, String signature, String nombredepages, String type) {
        this.date = date;
        this.signature = signature;
        this.nombredepages = nombredepages;
        this.type = type;
    }
}
