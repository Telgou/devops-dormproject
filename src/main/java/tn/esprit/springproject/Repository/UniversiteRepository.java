package tn.esprit.springproject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.springproject.entity.Universite;

import java.util.List;

@Repository
public interface UniversiteRepository extends JpaRepository<Universite,Long> {
    Universite findByNomUniversite(String nomUniversite);

    @Query("SELECT p FROM Universite p WHERE " +
            "p.nomUniversite LIKE CONCAT('%',:query, '%')" +
            "Or p.adresse LIKE CONCAT('%', :query, '%')")
    List<Universite> searchUniversites(String query);
}
