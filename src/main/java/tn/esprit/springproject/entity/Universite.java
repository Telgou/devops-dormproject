package tn.esprit.springproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;


@Entity
public class Universite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUniversite;
    private String nomUniversite;
    private String adresse;
    @OneToOne
    private Foyer foyer ;

    public Universite(Long idUniversite, String nomUniversite, String adresse, Foyer foyer) {
        this.idUniversite = idUniversite;
        this.nomUniversite = nomUniversite;
        this.adresse = adresse;
        this.foyer = foyer;
    }

    public Universite() {
    }

    public Long getIdUniversite() {
        return idUniversite;
    }

    public void setIdUniversite(Long idUniversite) {
        this.idUniversite = idUniversite;
    }

    public String getNomUniversite() {
        return nomUniversite;
    }

    public void setNomUniversite(String nomUniversite) {
        this.nomUniversite = nomUniversite;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Foyer getFoyer() {
        return foyer;
    }

    public void setFoyer(Foyer foyer) {
        this.foyer = foyer;
    }

    // Add this equals method in your Universite class
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Universite universite = (Universite) obj;
        return Objects.equals(idUniversite, universite.idUniversite)
                && Objects.equals(nomUniversite, universite.nomUniversite)
                && Objects.equals(adresse, universite.adresse)
                && Objects.equals(foyer, universite.foyer);
    }

}
