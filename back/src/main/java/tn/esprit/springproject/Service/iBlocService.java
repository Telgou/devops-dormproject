package tn.esprit.springproject.Service;

import tn.esprit.springproject.entity.Bloc;

import java.util.List;

public interface iBlocService {

    List<Bloc> retrieveAllBlocs();

    Bloc addBloc(Bloc e);

    Bloc updateBloc(Bloc e);

    Bloc retrieveBloc(Long idBloc);

    void removeBloc(Long idBloc);
    public Bloc affecterChambresABloc(List<Long> numChambres, String nomBloc);
}
