package tn.esprit.springproject.Service;

import org.springframework.core.io.ByteArrayResource;
import tn.esprit.springproject.entity.Reservation;

import java.util.Date;
import java.util.List;

public interface iReservationService {

    List<Reservation> retrieveAllReservations();

    Reservation addReservation(Reservation e);

    Reservation updateReservation(Reservation e);

    Reservation retrieveReservation(Long idReservation);

    void removeReservation(Long idReservation);

    ByteArrayResource generatePDFFromReservationData(Long idReservation);



}