package de.awa.training.webcrawler.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "postleitzahlen", schema = "fluessiggascrawler", catalog = "")
public class PostleitzahlenEntity {

    @Id
    @GeneratedValue
    private Integer id;
    private String plz;

    @OneToOne(mappedBy = "pfiffiggas")
    private List<PfiffiggasEntity> entity = new ArrayList<>();

    @Column(name = "ID")
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "PLZ")
    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostleitzahlenEntity that = (PostleitzahlenEntity) o;

        if (id != that.id) return false;
        if (plz != null ? !plz.equals(that.plz) : that.plz != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (plz != null ? plz.hashCode() : 0);
        return result;
    }
}
