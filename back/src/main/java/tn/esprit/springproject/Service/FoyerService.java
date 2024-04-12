package tn.esprit.springproject.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.springproject.Repository.BlocRepository;
import tn.esprit.springproject.Repository.NotificationRepository;
import tn.esprit.springproject.entity.Foyer;
import tn.esprit.springproject.Repository.FoyerRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FoyerService implements iFoyerService {

    FoyerRepository foyerRespository;

    BlocRepository blocRepository;

    private NotificationRepository notificationRepository;

    @Override
    public List<Foyer> retrieveAllFoyers() {
        return foyerRespository.findAll();
    }

    @Override
    public Foyer addFoyer(Foyer foyer) {
        return foyerRespository.save(foyer);
    }

    @Override
    public Foyer updateFoyer(Foyer e) {
        Optional<Foyer> foyerUpdated = foyerRespository.findById(e.getIdFoyer());
        if (foyerUpdated.isPresent()) {
            Foyer readyToUpdate = foyerUpdated.get();
            readyToUpdate.setNomFoyer(e.getNomFoyer());
            readyToUpdate.setCapaciteFoyer(e.getCapaciteFoyer());
            return foyerRespository.save(readyToUpdate);

        } else return new Foyer();

    }

    @Override
    public Foyer retrieveFoyer(Long idFoyer) {
        return foyerRespository.findById(idFoyer)
                .orElseThrow(() -> new NoSuchElementException("Foyer not found with id: " + idFoyer));
    }


    @Override
    public void removeFoyer(Long idFoyer) {
        foyerRespository.deleteById(idFoyer);

    }

    public void archiverFoyer(long idFoyer) {
        Foyer foyer = foyerRespository.findById(idFoyer).orElse(null);
        if (foyer != null) {
            foyer.setArchived(true);
            foyerRespository.save(foyer);
        }
    }

    public Foyer addFoyerWithBloc(Foyer foyer) {

        Foyer f = foyerRespository.save(foyer);

        f.getBlocs().forEach(bloc ->
        {
            bloc.setFoyer(f);
            blocRepository.save(bloc);

        });
        return f;


    }

}
