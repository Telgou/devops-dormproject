package tn.esprit.springproject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.springproject.entity.Etudiant;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant,Long> {

    Etudiant findByNomEtAndPrenomEt(String nomEt, String prenomEt);
}
