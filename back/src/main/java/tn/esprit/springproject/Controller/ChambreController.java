package tn.esprit.springproject.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.springproject.Repository.ChambreRepository;
import tn.esprit.springproject.Service.iChambreService;
import tn.esprit.springproject.entity.Chambre;
import tn.esprit.springproject.entity.TypeChambre;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/chambres")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor
public class ChambreController {

    private iChambreService chambreService;

    private ChambreRepository chambreRepository;



    @GetMapping("/retrieve-all-chambers")
    public List<Chambre> getChambers() {
        return chambreService.retrieveAllChambers();
    }

    @GetMapping("/retrieve-chambre/{chambre-id}")
    public Chambre retrieveChambre(@PathVariable("chambre-id") Long chambreId) {
        return chambreService.retrieveChambre(chambreId);
    }

    @PostMapping("/add-chambre")
    public Chambre addChambre(@RequestBody Chambre e) {
        return chambreService.addChambre(e);
    }

    @DeleteMapping("/remove-chambre/{chambre-id}")
    public void removeChambre(@PathVariable("chambre-id") Long chambreId) {
        chambreService.removeChambre(chambreId);
    }

    @PutMapping("/update-chambre")
    public Chambre updateChambre(@RequestBody Chambre e) {
        return chambreService.updateChambre(e);
    }

    @GetMapping("/getchambres_nom/{nomBloc}")
    public Set<Chambre> getChambresParNomBloc(@PathVariable ("nomBloc") String nomBloc) {

        return  chambreService.getChambresParNomBloc(nomBloc);
    }
    @GetMapping("/nb-chambres-par-type-et-bloc")

    public long nbChambresParTypeEtBloc(@RequestParam(name = "type") TypeChambre type, @RequestParam(name = "idBloc") Long idBloc) {
        return chambreService.nbChambreParTypeEtBloc(type, idBloc);
    }


}
