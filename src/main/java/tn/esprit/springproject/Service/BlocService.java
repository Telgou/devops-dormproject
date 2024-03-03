package tn.esprit.springproject.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.springproject.entity.Bloc;

import tn.esprit.springproject.Repository.BlocRepository;
import tn.esprit.springproject.entity.Chambre;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class BlocService implements iBlocService {
    //    EtudiantRespository etudiantRespository;

    @Autowired
    BlocRepository blocRepository;
    @Autowired
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
        return blocRepository.findById(idBloc).get();
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

