package vermeg.com.applicationbancaire.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name="CGU")
public class CGUModel extends PJModel {
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

    public CGUModel(String byle, Date date, String signature, String nombredepages, String type) {
        super(byle);
        this.date = date;
        this.signature = signature;
        this.nombredepages = nombredepages;
        this.type = type;
    }

}
