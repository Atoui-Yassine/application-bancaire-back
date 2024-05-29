package vermeg.com.applicationbancaire.Models;

import jakarta.persistence.*;

@Entity
@Table(name="piecesJustificatifs")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class PJModel {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private String Byle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getByle() {
        return Byle;
    }

    public void setByle(String byle) {
        Byle = byle;
    }

    public PJModel() {
    }

    public PJModel(String byle) {
        Byle = byle;
    }
}
