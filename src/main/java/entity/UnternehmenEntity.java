package entity;

import javax.persistence.*;

@Entity
@Table(name = "unternehmen", schema = "fluessiggascrawler", catalog = "")
public class UnternehmenEntity {
    private int id;
    private Object name;
    private Object adresse;
    private String plz;
    private Object ort;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Name")
    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    @Basic
    @Column(name = "Adresse")
    public Object getAdresse() {
        return adresse;
    }

    public void setAdresse(Object adresse) {
        this.adresse = adresse;
    }

    @Basic
    @Column(name = "PLZ")
    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    @Basic
    @Column(name = "Ort")
    public Object getOrt() {
        return ort;
    }

    public void setOrt(Object ort) {
        this.ort = ort;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UnternehmenEntity that = (UnternehmenEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (adresse != null ? !adresse.equals(that.adresse) : that.adresse != null) return false;
        if (plz != null ? !plz.equals(that.plz) : that.plz != null) return false;
        if (ort != null ? !ort.equals(that.ort) : that.ort != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (adresse != null ? adresse.hashCode() : 0);
        result = 31 * result + (plz != null ? plz.hashCode() : 0);
        result = 31 * result + (ort != null ? ort.hashCode() : 0);
        return result;
    }
}
