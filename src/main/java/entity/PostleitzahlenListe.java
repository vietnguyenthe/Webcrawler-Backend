package entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class PostleitzahlenListe {

    @Id
    @GeneratedValue
    private Long id;

    // Beziehung zu PostleitzahlenListe Tabelle.
    @OneToMany(mappedBy = "plz")
    private List<MeinFluessiggas> meinFluessiggasPreise = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<MeinFluessiggas> getMeinFluessiggasPreise() {
        return meinFluessiggasPreise;
    }

    public void setMeinFluessiggasPreise(List<MeinFluessiggas> meinFluessiggasPreise) {
        this.meinFluessiggasPreise = meinFluessiggasPreise;
    }
}
