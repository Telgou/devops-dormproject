package tn.esprit.springproject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.springproject.entity.Chambre;

@Repository
public interface ChambreRepository extends JpaRepository<Chambre,Long> {

    Chambre findByNumeroChambre(Long numeroChambre);
}
