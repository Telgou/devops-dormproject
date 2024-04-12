package tn.esprit.springproject.entity;

import jakarta.persistence.*;

import java.util.Set;


@Entity
public class Chambre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long idChambre;
    private Long numeroChambre;
    private TypeChambre typeC;
    @ManyToOne
    private Bloc blocs;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Reservation> reservationss;

    public Chambre(Long idChambre, Long numeroChambre, TypeChambre typeC, Bloc blocs, Set<Reservation> reservationss) {
        this.idChambre = idChambre;
        this.numeroChambre = numeroChambre;
        this.typeC = typeC;
        this.blocs = blocs;
        this.reservationss = reservationss;
    }

    public Chambre() {
    }

    public Long getIdChambre() {
        return idChambre;
    }

    public void setIdChambre(Long idChambre) {
        this.idChambre = idChambre;
    }

    public Long getNumeroChambre() {
        return numeroChambre;
    }

    public void setNumeroChambre(Long numeroChambre) {
        this.numeroChambre = numeroChambre;
    }

    public TypeChambre getTypeC() {
        return typeC;
    }

    public void setTypeC(TypeChambre typeC) {
        this.typeC = typeC;
    }

    public Bloc getBlocs() {
        return blocs;
    }

    public void setBlocs(Bloc blocs) {
        this.blocs = blocs;
    }

    public Set<Reservation> getReservationss() {
        return reservationss;
    }

    public void setReservationss(Set<Reservation> reservationss) {
        this.reservationss = reservationss;
    }
}
