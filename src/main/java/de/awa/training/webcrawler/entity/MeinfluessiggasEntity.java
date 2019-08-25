package de.awa.training.webcrawler.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "meinfluessiggas", schema = "fluessiggascrawler", catalog = "")
public class MeinfluessiggasEntity implements EntityInterface{

    @Id
    @GeneratedValue
    private Integer id;

    private String preis2700Liter;
    private String preis4850Liter;
    private String preis6400Liter;
    private Date datum;
    private Integer postleitzahlenId;
    private Integer unternehmenId;

    public  MeinfluessiggasEntity(){}

    public MeinfluessiggasEntity(Integer id, String preis2700Liter, String preis4850Liter, String preis6400Liter, Integer postleitzahlenId, Integer unternehmenId) {
        this.id = id;
        this.preis2700Liter = preis2700Liter;
        this.preis4850Liter = preis4850Liter;
        this.preis6400Liter = preis6400Liter;
        this.postleitzahlenId = postleitzahlenId;
        this.unternehmenId = unternehmenId;
    }




    @Column(name = "ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Preis2700Liter")
    public String getPreis2700Liter() {
        return preis2700Liter;
    }

    public void setPreis2700Liter(String preis2700Liter) {
        this.preis2700Liter = preis2700Liter;
    }

    @Basic
    @Column(name = "Preis4850Liter")
    public String getPreis4850Liter() {
        return preis4850Liter;
    }

    public void setPreis4850Liter(String preis4850Liter) {
        this.preis4850Liter = preis4850Liter;
    }

    @Basic
    @Column(name = "Preis6400Liter")
    public String getPreis6400Liter() {
        return preis6400Liter;
    }

    public void setPreis6400Liter(String preis6400Liter) {
        this.preis6400Liter = preis6400Liter;
    }

    @Basic
    @Column(name = "Datum")
    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    @Basic
    @Column(name = "postleitzahlen_ID")
    public Integer getPostleitzahlenId() {
        return postleitzahlenId;
    }

    public void setPostleitzahlenId(Integer postleitzahlenId) {
        this.postleitzahlenId = postleitzahlenId;
    }

    @Basic
    @Column(name = "unternehmen_ID")
    public Integer getUnternehmenId() {
        return unternehmenId;
    }

    public void setUnternehmenId(Integer unternehmenId) {
        this.unternehmenId = unternehmenId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MeinfluessiggasEntity that = (MeinfluessiggasEntity) o;

        if (id != that.id) return false;
        if (preis2700Liter != null ? !preis2700Liter.equals(that.preis2700Liter) : that.preis2700Liter != null)
            return false;
        if (preis4850Liter != null ? !preis4850Liter.equals(that.preis4850Liter) : that.preis4850Liter != null)
            return false;
        if (preis6400Liter != null ? !preis6400Liter.equals(that.preis6400Liter) : that.preis6400Liter != null)
            return false;
        if (datum != null ? !datum.equals(that.datum) : that.datum != null) return false;
        if (postleitzahlenId != null ? !postleitzahlenId.equals(that.postleitzahlenId) : that.postleitzahlenId != null)
            return false;
        if (unternehmenId != null ? !unternehmenId.equals(that.unternehmenId) : that.unternehmenId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (preis2700Liter != null ? preis2700Liter.hashCode() : 0);
        result = 31 * result + (preis4850Liter != null ? preis4850Liter.hashCode() : 0);
        result = 31 * result + (preis6400Liter != null ? preis6400Liter.hashCode() : 0);
        result = 31 * result + (datum != null ? datum.hashCode() : 0);
        result = 31 * result + (postleitzahlenId != null ? postleitzahlenId.hashCode() : 0);
        result = 31 * result + (unternehmenId != null ? unternehmenId.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(EntityInterface o) {
        if (this.getId() < o.getId()) {
            return -1;
        } else if (this.getId() == o.getId()) {
            return 0;
        } else {
            return 1;
        }

    }
}
