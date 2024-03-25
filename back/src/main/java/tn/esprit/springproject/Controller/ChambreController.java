package tn.esprit.springproject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.springproject.Repository.ChambreRepository;
import tn.esprit.springproject.Service.iBlocService;
import tn.esprit.springproject.Service.iChambreService;
import tn.esprit.springproject.entity.Bloc;
import tn.esprit.springproject.entity.Chambre;
import tn.esprit.springproject.entity.TypeChambre;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/chambres")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ChambreController {
    @Autowired
    private iChambreService chambreService;
    @Autowired
    private ChambreRepository chambreRepository;



    @GetMapping("/retrieve-all-chambers")
    public List<Chambre> getChambers() {
        List<Chambre> listChambers = chambreService.retrieveAllChambers();
        return listChambers;
    }

    @GetMapping("/retrieve-chambre/{chambre-id}")
    public Chambre retrieveChambre(@PathVariable("chambre-id") Long chambreId) {
        return chambreService.retrieveChambre(chambreId);
    }

    @PostMapping("/add-chambre")
    public Chambre addChambre(@RequestBody Chambre e) {
        Chambre chambre = chambreService.addChambre(e);
        return chambre;
    }

    @DeleteMapping("/remove-chambre/{chambre-id}")
    public void removeChambre(@PathVariable("chambre-id") Long chambreId) {
        chambreService.removeChambre(chambreId);
    }

    @PutMapping("/update-chambre")
    public Chambre updateChambre(@RequestBody Chambre e) {
        Chambre chambre = chambreService.updateChambre(e);
        return chambre;
    }

    @GetMapping("/getchambres_nom/{nomBloc}")
    public Set<Chambre> getChambresParNomBloc(@PathVariable ("nomBloc") String nomBloc) {

        return  chambreService.getChambresParNomBloc(nomBloc);
    }
    @GetMapping("/nb-chambres-par-type-et-bloc")

    public long nbChambresParTypeEtBloc(@RequestParam(name = "type") TypeChambre type, @RequestParam(name = "idBloc") Long idBloc) {

        long count = chambreService.nbChambreParTypeEtBloc(type, idBloc);

        return count;
    }


}
