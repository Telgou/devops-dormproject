package tn.esprit.springproject.Service;

import org.springframework.beans.factory.annotation.Autowired;
import tn.esprit.springproject.Repository.UniversiteRepository;
import tn.esprit.springproject.entity.Universite;

import java.util.List;

public interface iUniversiteService {


    List<Universite> retrieveAllUniversites();

    Universite addUniversites(Universite e);

    Universite updateUniversites(Universite e);

    Universite retrieveUniversites(Long idUniversite);

    void removeUniversites(Long idUniversite);
    public Universite affecterFoyerAUniversite (long idFoyer, String nomUniversite) ;
    public Universite desaffecterFoyerAUniversite (long idFoyer);
    List<Universite> searchUniversites(String query);
}
