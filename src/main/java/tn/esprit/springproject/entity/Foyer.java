package tn.esprit.springproject.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.Set;

@Getter
@Entity
public class Foyer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFoyer;
    private String nomFoyer;
    private Long capaciteFoyer;
    private boolean archived = false;
    private String responsableUsername;


    public String getResponsableUsername() {
        return responsableUsername;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy="foyer")
    @JsonBackReference
    private Set<Bloc> blocs;

    @OneToOne(mappedBy = "foyer", cascade = CascadeType.ALL)
    private Universite universite ;

    public Foyer() {
    }

    public Foyer(Long idFoyer, String nomFoyer, Long capaciteFoyer, boolean archived, Set<Bloc> blocs, Universite universite) {
        this.idFoyer = idFoyer;
        this.nomFoyer = nomFoyer;
        this.capaciteFoyer = capaciteFoyer;
        this.archived = archived;
        this.blocs = blocs;
        this.universite = universite;
    }

    public Long getIdFoyer() {
        return idFoyer;
    }

    public void setIdFoyer(Long idFoyer) {
        this.idFoyer = idFoyer;
    }

    public String getNomFoyer() {
        return nomFoyer;
    }

    public void setNomFoyer(String nomFoyer) {
        this.nomFoyer = nomFoyer;
    }

    public Long getCapaciteFoyer() {
        return capaciteFoyer;
    }

    public void setCapaciteFoyer(Long capaciteFoyer) {
        this.capaciteFoyer = capaciteFoyer;
    }

    public Set<Bloc> getBlocs() {
        return blocs;
    }

    public void setBlocs(Set<Bloc> blocs) {
        this.blocs = blocs;
    }

    public Universite getUniversite() {
        return universite;
    }

    public void setUniversite(Universite universite) {
        this.universite = universite;
    }
}
