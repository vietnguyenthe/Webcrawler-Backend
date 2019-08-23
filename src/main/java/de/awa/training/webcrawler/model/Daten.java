package de.awa.training.webcrawler.model;

public class Daten {

    private String name;
    private String adresse;
    private String plz;
    private String ort;
    private String preis;

    public Daten(){}

    public Daten(String name, String adresse, String plz, String ort, String preis) {
        this.name = name;
        this.adresse = adresse;
        this.plz = plz;
        this.ort = ort;
        this.preis = preis;
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
}
