package vermeg.com.applicationbancaire.Models;

import jakarta.persistence.*;

@Entity
@Table(name="piecesJustificatifs")

public class PJModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//héritage car classe mère
    private Long id;

    private  String photo;
    private String relev1;
    private String relev2;
    private String relev3;
    private String relev4;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getRelev1() {
        return relev1;
    }

    public void setRelev1(String relev1) {
        this.relev1 = relev1;
    }

    public String getRelev2() {
        return relev2;
    }

    public void setRelev2(String relev2) {
        this.relev2 = relev2;
    }

    public String getRelev3() {
        return relev3;
    }

    public void setRelev3(String relev3) {
        this.relev3 = relev3;
    }

    public String getRelev4() {
        return relev4;
    }

    public void setRelev4(String relev4) {
        this.relev4 = relev4;
    }

    public PJModel(String photo, String relev1, String relev2, String relev3, String relev4) {
        this.photo = photo;
        this.relev1 = relev1;
        this.relev2 = relev2;
        this.relev3 = relev3;
        this.relev4 = relev4;
    }

    public PJModel() {
    }
}
