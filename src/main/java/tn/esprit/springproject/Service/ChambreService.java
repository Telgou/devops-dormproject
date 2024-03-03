package tn.esprit.springproject.Service;

import com.sun.tools.javac.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.springproject.Repository.BlocRepository;
import tn.esprit.springproject.entity.Bloc;
import tn.esprit.springproject.entity.Chambre;


import tn.esprit.springproject.Repository.ChambreRepository;
import tn.esprit.springproject.entity.TypeChambre;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ChambreService implements iChambreService {

    @Autowired
    ChambreRepository chambreRepository;
    @Autowired
    BlocRepository blocRepository;
    private static final Logger log = LoggerFactory.getLogger(Main.class);

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
        return chambreRepository.findById(idChambre).get();
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

        Bloc bloc = blocRepository.findById(idBloc).get();

        long count = 0;
        for (Chambre chambre : bloc.getChambres()) {
            if (chambre.getTypeC() == type) {
                count++;
            }
        }

        return count;
    }


    //@Scheduled(cron = "*/5 * * * * *")
   /* public void listeChambresParBloc() {
        log.info("Liste des chambres par bloc");
        Map<Bloc, List<Chambre>> chambresParBloc = chambreRepository.findAll().stream()
                .collect(Collectors.groupingBy(Chambre::getBlocs));
        chambresParBloc.forEach((bloc, chambres) -> {
            System.out.println("Bloc : " + bloc);
            chambres.forEach(chambre -> System.out.println("\t" + chambre));
        });
    }

    @Scheduled(fixedRate = 300000)
    public void pourcentageChambreParTypeChambre() {


        log.info("Pourcentage de chambres par type de chambre");
        Map<TypeChambre, Long> chambresParType = chambreRepository.findAll().stream()
                .collect(Collectors.groupingBy(Chambre::getTypeC, Collectors.counting()));
        chambresParType.forEach((typeChambre, nombreChambres) -> {
            log.info("Type de chambre : " + typeChambre);
            log.info("\tPourcentage : " + (nombreChambres * 100) / chambreRepository.count());
        });


    }*/


}
