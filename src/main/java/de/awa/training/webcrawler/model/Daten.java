package de.awa.training.webcrawler.model;

import de.awa.training.webcrawler.entity.EntityInterface;

public class Daten implements Comparable<Daten>{

    private String id;
    private String name;
    private String adresse;
    private String plz;
    private String ort;
    private String preis;

    public Daten(){}



    public Daten(String id, String name, String adresse, String plz, String ort) {
        this.id = id;
        this.name = name;
        this.adresse = adresse;
        this.plz = plz;
        this.ort = ort;
        //this.preis = preis;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getPreis() {
        return preis;
    }

    public void setPreis(String preis) {
        this.preis = preis;
    }

    @Override
    public String toString() {
        return "Daten{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", adresse='" + adresse + '\'' +
                ", plz='" + plz + '\'' +
                ", ort='" + ort + '\'' +
                ", preis='" + preis + '\'' +
                '}';
    }

    @Override
    public int compareTo(Daten b) {
        if (b.getPreis() == null && this.getPreis() == null) {
            return 0;
        }
        if (this.getPreis() == null) {
            return 1;
        }
        if (b.getPreis() == null) {
            return -1;}

            return this.getPreis().compareTo(b.getPreis());
        }
    }

