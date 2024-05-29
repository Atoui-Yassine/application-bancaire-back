package vermeg.com.applicationbancaire.Models;

import jakarta.persistence.*;

@Entity
@Table(name="Admin")
public class AdminModel extends UserModel {

    private String codeAdmin;




    public String getCodeAdmin() {
        return codeAdmin;
    }

    public void setCodeAdmin(String codeAdmin) {
        this.codeAdmin = codeAdmin;
    }


    public AdminModel() {
    }

    public AdminModel(String username, String email, String phone, String photo, String role,String password, String codeAdmin) {
        super(username, email, phone, photo,role, password);
        this.codeAdmin = codeAdmin;
    }
}
