package tn.esprit.springproject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.springproject.entity.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {

}
