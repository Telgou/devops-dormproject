package tn.esprit.springproject.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.springproject.entity.Bloc;

import tn.esprit.springproject.Repository.BlocRepository;
import tn.esprit.springproject.entity.Chambre;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class BlocService implements iBlocService {

    BlocRepository blocRepository;
    ChambreService chambreService;

    @Override
    public List<Bloc> retrieveAllBlocs() {
        return blocRepository.findAll();
    }

    @Override
    public Bloc addBloc(Bloc e) {
        return blocRepository.save(e);
    }

    @Override
    public Bloc updateBloc(Bloc e) {
        return blocRepository.save(e);
    }

    @Override
    public Bloc retrieveBloc(Long idBloc) {
        return blocRepository.findById(idBloc)
                .orElseThrow(() -> new NoSuchElementException("Bloc not found with id: " + idBloc));
    }


    @Override
    public void removeBloc(Long idBloc) {
        blocRepository.deleteById(idBloc);

    }

    public Bloc affecterChambresABloc(List<Long> numChambres, String nomBloc) {

        Bloc bloc = blocRepository.findByNomBloc(nomBloc);


        for (Long numeroChambre : numChambres) {
            Chambre chambre = chambreService.retrieveChambreByNumero(numeroChambre);

            if (chambre != null) {

                chambre.setBlocs(bloc);

                chambreService.updateChambre(chambre);
            }
        }


        return bloc;
    }

}

