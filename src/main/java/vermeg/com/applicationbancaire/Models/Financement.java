package vermeg.com.applicationbancaire.Models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name="Financement")
public class Financement {
    @Id
    @GeneratedValue
    private Long id;

    private int num;

    private Double montanttotal;

    private String DateAchat;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Double getMontanttotal() {
        return montanttotal;
    }

    public void setMontanttotal(Double montanttotal) {
        this.montanttotal = montanttotal;
    }

    public String getDateAchat() {
        return DateAchat;
    }

    public void setDateAchat(String dateAchat) {
        DateAchat = dateAchat;
    }

    public Financement(Long id, int num, Double montanttotal, String dateAchat) {
        this.id = id;
        this.num = num;
        this.montanttotal = montanttotal;
        DateAchat = dateAchat;
    }

    public Financement() {
    }
}
