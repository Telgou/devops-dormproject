package tn.esprit.springproject.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.springproject.Repository.BlocRepository;
import tn.esprit.springproject.entity.Bloc;
import tn.esprit.springproject.entity.Chambre;


import tn.esprit.springproject.Repository.ChambreRepository;
import tn.esprit.springproject.entity.TypeChambre;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class ChambreService implements iChambreService {

    ChambreRepository chambreRepository;
    BlocRepository blocRepository;

    @Override
    public List<Chambre> retrieveAllChambers() {
        return chambreRepository.findAll();
    }

    @Override
    public Chambre addChambre(Chambre e) {
        return chambreRepository.save(e);
    }

    @Override
    public Chambre updateChambre(Chambre e) {
        return chambreRepository.save(e);
    }

    @Override
    public Chambre retrieveChambre(Long idChambre) {
        Optional<Chambre> optionalChambre = chambreRepository.findById(idChambre);
        if (optionalChambre.isPresent()) {
            return optionalChambre.get();
        } else {
            // Handle the case where the Chambre with the given ID doesn't exist
            // You might want to throw an exception, return null, or handle it differently based on your application logic
            return null; // or throw an exception
        }
    }


    @Override
    public void removeChambre(Long idChambre) {
        chambreRepository.deleteById(idChambre);
    }

    public Chambre retrieveChambreByNumero(Long numeroChambre) {
        return chambreRepository.findByNumeroChambre(numeroChambre);
    }

    public Set<Chambre> getChambresParNomBloc(String nomBloc) {
        Bloc bloc = blocRepository.findByNomBloc(nomBloc);
        return bloc.getChambres();
    }

    public long nbChambreParTypeEtBloc(TypeChambre type, long idBloc) {
        Optional<Bloc> optionalBloc = blocRepository.findById(idBloc);
        if (optionalBloc.isPresent()) {
            Bloc bloc = optionalBloc.get();

            long count = 0;
            for (Chambre chambre : bloc.getChambres()) {
                if (chambre.getTypeC() == type) {
                    count++;
                }
            }
            return count;
        } else {
            return 0;
        }
    }


}
