package vermeg.com.applicationbancaire.Models;

import jakarta.persistence.*;

@Entity
@Table(name="Client")
public class ClientModel extends UserModel {
    private String codeclient;
    private String Civilité;
    private String villedenaissance;
    private String codepostaledenaissance;
    private String paysdenaissance;
    private String Nationnalité;


    public String getCodeclient() {
        return codeclient;
    }

    public void setCodeclient(String codeclient) {
        this.codeclient = codeclient;
    }


    public ClientModel() {
    }

    public ClientModel(String username, String email, String phone, String photo, String password, String role, String codeclient, String civilité, String villedenaissance, String codepostaledenaissance, String paysdenaissance, String nationnalité) {
        super(username, email, phone, photo, password, role);
        this.codeclient = codeclient;
        Civilité = civilité;
        this.villedenaissance = villedenaissance;
        this.codepostaledenaissance = codepostaledenaissance;
        this.paysdenaissance = paysdenaissance;
        Nationnalité = nationnalité;
    }

    public String getCivilité() {
        return Civilité;
    }

    public void setCivilité(String civilité) {
        Civilité = civilité;
    }

    public String getVilledenaissance() {
        return villedenaissance;
    }

    public void setVilledenaissance(String villedenaissance) {
        this.villedenaissance = villedenaissance;
    }

    public String getCodepostaledenaissance() {
        return codepostaledenaissance;
    }

    public void setCodepostaledenaissance(String codepostaledenaissance) {
        this.codepostaledenaissance = codepostaledenaissance;
    }

    public String getPaysdenaissance() {
        return paysdenaissance;
    }

    public void setPaysdenaissance(String paysdenaissance) {
        this.paysdenaissance = paysdenaissance;
    }

    public String getNationnalité() {
        return Nationnalité;
    }

    public void setNationnalité(String nationnalité) {
        Nationnalité = nationnalité;
    }
}
