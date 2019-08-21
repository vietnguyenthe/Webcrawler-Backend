package entity;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class MeinFluessiggas {

    // Attribute/ Spalten der Tabelle
    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime datum;

    private double preis12Tonner;

    private double preis21Tonner;

    private double preis29Tonner;

    @ManyToOne
    @JoinColumn
    private PostleitzahlenListe postleitzahlenListe;


    // Getter und Setter für die einzelnen Attribute
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDatum() {
        return datum;
    }

    public void setDatum(LocalDateTime datum) {
        this.datum = datum;
    }

    public double getPreis12Tonner() {
        return preis12Tonner;
    }

    public void setPreis12Tonner(double preis12Tonner) {
        this.preis12Tonner = preis12Tonner;
    }

    public double getPreis21Tonner() {
        return preis21Tonner;
    }

    public void setPreis21Tonner(double preis21Tonner) {
        this.preis21Tonner = preis21Tonner;
    }

    public double getPreis29Tonner() {
        return preis29Tonner;
    }

    public void setPreis29Tonner(double preis29Tonner) {
        this.preis29Tonner = preis29Tonner;
    }

    public PostleitzahlenListe getPostleitzahlenListe() {
        return postleitzahlenListe;
    }

    public void setPostleitzahlenListe(PostleitzahlenListe postleitzahlenListe) {
        this.postleitzahlenListe = postleitzahlenListe;
    }
}
