package vermeg.com.applicationbancaire.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class SignupRequest {
    @NotBlank(message = "Le champ ne doit pas Etre vide")
    private String codeAdmin;
    @NotBlank(message = "Le champ ne doit pas être vide")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Le champ doit contenir uniquement des lettres")
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;
    @Email
    @NotBlank(message = "Le champ ne doit pas Etre vide")
    private String email;
    @NotBlank(message = "Le champ ne doit pas Etre vide")
    @Size(min = 8, max = 8)
    private String phone;
    @NotBlank(message = "Le champ ne doit pas Etre vide")
    private String photo;
    @NotBlank(message = "Le champ ne doit pas Etre vide")
    private String role;
    @NotBlank(message = "Le champ ne doit pas Etre vide")

    private String codeclient;
    @NotBlank(message = "Le champ ne doit pas Etre vide")
    private String password;
    @NotBlank(message = "Le champ ne doit pas Etre vide")
    private String Civilité;
    @NotBlank(message = "Le champ ne doit pas Etre vide")
    private String villedenaissance;
    @NotBlank(message = "Le champ ne doit pas Etre vide")
    private String codepostaledenaissance;
    @NotBlank(message = "Le champ ne doit pas Etre vide")
    private String paysdenaissance;
    @NotBlank(message = "Le champ ne doit pas Etre vide")
    private String Nationnalité;


    public String getCodeAdmin() {
        return codeAdmin;
    }

    public void setCodeAdmin(String codeAdmin) {
        this.codeAdmin = codeAdmin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCodeclient() {
        return codeclient;
    }

    public void setCodeclient(String codeclient) {
        this.codeclient = codeclient;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
