package tn.esprit.springproject.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.springproject.Repository.FoyerRepository;
import tn.esprit.springproject.entity.Foyer;
import tn.esprit.springproject.entity.Universite;
import tn.esprit.springproject.Repository.UniversiteRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UniversiteService implements iUniversiteService {
    FoyerRepository foyerRespository;

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
        Optional<Universite> value = universiteRepository.findById(idUniversite);

        if (value.isPresent()) {
            return value.get();
        }
        return null;
    }


    @Override
    public void removeUniversites(Long idUniversite) {
        universiteRepository.deleteById(idUniversite);

    }

    @Override
    public Universite affecterFoyerAUniversite(long idFoyer, String nomUniversite) {
        Universite universite = universiteRepository.findByNomUniversite(nomUniversite);
        Optional<Foyer> vfoyer = foyerRespository.findById(idFoyer);
        Foyer foyer = vfoyer.get();

        if (universite != null && foyer != null) {
            universite.setFoyer(foyer);
            universiteRepository.save(universite);
        }

        return universite;
    }

    public Universite desaffecterFoyerAUniversite(long idFoyer) {
        Optional<Foyer> vfoyer = foyerRespository.findById(idFoyer);
        Foyer foyer = vfoyer.get();

        if (foyer != null) {
            Universite universite = foyer.getUniversite();
            if (universite != null) {
                universite.setFoyer(null);
                universiteRepository.save(universite);
            }

            return universite;
        }

        return null; // or handle as needed
    }

    @Override
    public List<Universite> searchUniversites(String query) {
        List<Universite> universites = universiteRepository.searchUniversites(query);
        return universites;
    }

}
