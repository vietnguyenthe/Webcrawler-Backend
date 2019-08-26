package de.awa.training.webcrawler.entity;

import javax.persistence.*;

@Entity
@Table(name = "unternehmen", schema = "fluessiggascrawler", catalog = "")
public class UnternehmenEntity {

    @Id
    private Integer id;

    private String name;
    private String adresse;
    private String plz;
    private String ort;


    private String benutzername;
    private Integer passwort;



    public UnternehmenEntity(){ }

    public UnternehmenEntity(Integer id, String name, String adresse, String plz, String ort) {
        this.id = id;
        this.name = name;
        this.adresse = adresse;
        this.plz = plz;
        this.ort = ort;
    }

    @Column(name = "ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "Adresse")
    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
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
    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }


    @Basic
    @Column(name = "Benutzername")
    public String getBenutzername() {
        return benutzername;
    }

    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }

    @Basic
    @Column(name = "Passwort")
    public Integer getPasswort() {
        return passwort;
    }

    public void setPasswort(int passwort) {
        this.passwort = passwort;
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
        if (benutzername != null ? !benutzername.equals(that.benutzername) : that.benutzername != null) return false;
        if (passwort != null ? !passwort.equals(that.passwort) : that.passwort != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (adresse != null ? adresse.hashCode() : 0);
        result = 31 * result + (plz != null ? plz.hashCode() : 0);
        result = 31 * result + (ort != null ? ort.hashCode() : 0);
        result = 31 * result + (benutzername != null ? benutzername.hashCode() : 0);
        result = 31 * result + (passwort != null ? passwort.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UnternehmenEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", adresse='" + adresse + '\'' +
                ", plz='" + plz + '\'' +
                ", ort='" + ort + '\'' +
                '}';
    }
}
