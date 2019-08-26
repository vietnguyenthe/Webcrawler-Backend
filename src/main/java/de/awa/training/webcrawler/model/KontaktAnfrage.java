package de.awa.training.webcrawler.model;

public class KontaktAnfrage {


    private String firmennamen;
    private String firmenadresse;
    private String plz;
    private String kontaktperson;
    private String emailAdresse;
    private String nachricht;
    private String betreff;

    public String getFirmennamen() {
        return firmennamen;
    }

    public void setFirmennamen(String firmennamen) {
        this.firmennamen = firmennamen;
    }

    public String getFirmenadresse() {
        return firmenadresse;
    }

    public void setFirmenadresse(String firmenadresse) {
        this.firmenadresse = firmenadresse;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getKontaktperson() {
        return kontaktperson;
    }

    public void setKontaktperson(String kontaktperson) {
        this.kontaktperson = kontaktperson;
    }

    public String getEmailAdresse() {
        return emailAdresse;
    }

    public void setEmailAdresse(String emailAdresse) {
        this.emailAdresse = emailAdresse;
    }

    public String getNachricht() {
        return nachricht;
    }

    public void setNachricht(String nachricht) {
        this.nachricht = nachricht;
    }

    public String getBetreff() {
        return betreff;
    }

    public void setBetreff(String betreff) {
        this.betreff = betreff;
    }
}
