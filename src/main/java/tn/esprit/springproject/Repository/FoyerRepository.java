package tn.esprit.springproject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.springproject.entity.Foyer;


@Repository
public interface FoyerRepository extends JpaRepository<Foyer,Long> {

}
