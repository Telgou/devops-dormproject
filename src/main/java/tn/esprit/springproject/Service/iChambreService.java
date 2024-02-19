package tn.esprit.springproject.Service;

import tn.esprit.springproject.entity.Chambre;
import tn.esprit.springproject.entity.TypeChambre;

import java.util.List;
import java.util.Set;

public interface iChambreService {
    List<Chambre> retrieveAllChambers();

    Chambre addChambre(Chambre e);

    Chambre updateChambre(Chambre e);

    Chambre retrieveChambre(Long idChambre);

    void removeChambre(Long idChambre);
    public Set<Chambre> getChambresParNomBloc(String nomBloc);
    public long nbChambreParTypeEtBloc(TypeChambre type, long idBloc);
}
