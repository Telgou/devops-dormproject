package tn.esprit.springproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.engine.internal.Cascade;

import java.util.Set;

@Entity

public class Bloc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBloc;
    private String nomBloc;
    private Long capaciteBloc;
    @ManyToOne
    Foyer foyer;
    @OneToMany (cascade =CascadeType.ALL , mappedBy = "blocs")
    @JsonIgnore
private Set<Chambre> chambres ;

    public Bloc() {
    }

    public Bloc(Long idBloc, String nomBloc, Long capaciteBloc, Foyer foyer, Set<Chambre> chambres) {
        this.idBloc = idBloc;
        this.nomBloc = nomBloc;
        this.capaciteBloc = capaciteBloc;
        this.foyer = foyer;
        this.chambres = chambres;
    }

    public Long getIdBloc() {
        return idBloc;
    }

    public void setIdBloc(Long idBloc) {
        this.idBloc = idBloc;
    }

    public String getNomBloc() {
        return nomBloc;
    }

    public void setNomBloc(String nomBloc) {
        this.nomBloc = nomBloc;
    }

    public Long getCapaciteBloc() {
        return capaciteBloc;
    }

    public void setCapaciteBloc(Long capaciteBloc) {
        this.capaciteBloc = capaciteBloc;
    }

    public Foyer getFoyer() {
        return foyer;
    }

    public void setFoyer(Foyer foyer) {
        this.foyer = foyer;
    }

    public Set<Chambre> getChambres() {
        return chambres;
    }

    public void setChambres(Set<Chambre> chambres) {
        this.chambres = chambres;
    }

}
