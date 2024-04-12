package tn.esprit.springproject.Service;

import tn.esprit.springproject.entity.Foyer;

import java.util.List;

public interface iFoyerService {
    List<Foyer> retrieveAllFoyers();

    Foyer addFoyer(Foyer e);

    Foyer updateFoyer(Foyer e);

    Foyer retrieveFoyer(Long idFoyer);

    void removeFoyer(Long idFoyer);

    void archiverFoyer(long idFoyer);
    public Foyer addFoyerWithBloc(Foyer foyer);
}
