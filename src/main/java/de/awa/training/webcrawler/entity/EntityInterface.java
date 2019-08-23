package de.awa.training.webcrawler.entity;


import java.sql.Date;

public interface EntityInterface extends Comparable<EntityInterface> {

    public Integer getId();

    public void setId(Integer id);


    public String getPreis2700Liter();

    public void setPreis2700Liter(String preis2700Liter);


    public String getPreis4850Liter();

    public void setPreis4850Liter(String preis4850Liter);


    public String getPreis6400Liter();

    public void setPreis6400Liter(String preis6400Liter);


    public Date getDatum();

    public void setDatum(Date datum);


    public Integer getPostleitzahlenId();

    public void setPostleitzahlenId(Integer postleitzahlenId);


    public Integer getUnternehmenId();

    public void setUnternehmenId(Integer unternehmenId);

    public boolean equals(Object o);


    public int hashCode();







}
