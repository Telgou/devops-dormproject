package tn.esprit.springproject.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.springproject.Repository.FoyerRepository;
import tn.esprit.springproject.entity.Foyer;
import tn.esprit.springproject.entity.Universite;
import tn.esprit.springproject.Repository.UniversiteRepository;

import java.util.List;
@Service
public class UniversiteService implements iUniversiteService{
    FoyerRepository foyerRespository;

    @Autowired
    UniversiteRepository universiteRepository;
    @Override
    public List<Universite> retrieveAllUniversites() {
        return universiteRepository.findAll();
    }

    @Override
    public Universite addUniversites(Universite e) {
        return universiteRepository.save(e);
    }

    @Override
    public Universite updateUniversites(Universite e) {
        return universiteRepository.save(e);
    }

    @Override
    public Universite retrieveUniversites(Long idUniversite) {
        return universiteRepository.findById(idUniversite).get();
    }


    @Override
    public void removeUniversites(Long idUniversite) {
        universiteRepository.deleteById(idUniversite);

    }

    @Override
    public Universite affecterFoyerAUniversite(long idFoyer, String nomUniversite) {
        Universite universite = universiteRepository.findByNomUniversite(nomUniversite);
        Foyer foyer = foyerRespository.findById(idFoyer).get();


        universite.setFoyer(foyer);
        universiteRepository.save(universite);


        return universite;
    }
    public Universite desaffecterFoyerAUniversite (long idFoyer)
    {
        Foyer foyer = foyerRespository.findById(idFoyer).get();
Universite universite= foyer.getUniversite();
if (universite!= null)
{
    universite.setFoyer(null);
    universiteRepository.save(universite);

}

        return universite;
    }
    @Override
    public List<Universite> searchUniversites(String query) {
        List<Universite> universites = universiteRepository.searchUniversites(query);
        return universites;
    }

}
