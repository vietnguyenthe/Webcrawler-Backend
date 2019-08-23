package de.awa.training.webcrawler.model;

import javax.persistence.criteria.CriteriaBuilder;

public class PreisDaten {

    private Integer id;
    private String preis;

    public PreisDaten(Integer id, String preis) {
        this.id = id;
        this.preis = preis;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPreis() {
        return preis;
    }

    public void setPreis(String preis) {
        this.preis = preis;
    }

    @Override
    public String toString() {
        return "PreisDaten{" +
                "id=" + id +
                ", preis='" + preis + '\'' +
                '}';
    }
}
