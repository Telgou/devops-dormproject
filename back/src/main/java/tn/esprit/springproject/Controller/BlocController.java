
package tn.esprit.springproject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.springproject.Service.iBlocService;
import tn.esprit.springproject.entity.Bloc;

import java.util.List;

@RestController
@RequestMapping("/blocs")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BlocController {

    @Autowired
    private iBlocService blocService;

    @GetMapping("/retrieve-all-blocs")
    public List<Bloc> getBlocs() {
        List<Bloc> listBlocs = blocService.retrieveAllBlocs();
        return listBlocs;
    }

    @GetMapping("/retrieve-bloc/{bloc-id}")
    public Bloc retrieveBloc(@PathVariable("bloc-id") Long blocId) {
        return blocService.retrieveBloc(blocId);
    }

    @PostMapping("/add-bloc")
    public Bloc addBloc(@RequestBody Bloc e) {
        Bloc bloc = blocService.addBloc(e);
        return bloc;
    }

    @DeleteMapping("/remove-bloc/{bloc-id}")
    public void removeBloc(@PathVariable("bloc-id") Long blocId) {
        blocService.removeBloc(blocId);
    }

    @PutMapping("/update-bloc")
    public Bloc updateBloc(@RequestBody Bloc e) {
        Bloc bloc = blocService.updateBloc(e);
        return bloc;
    }
    @PostMapping("/affecter-chambres/{nomBloc}")
    public Bloc affecterChambresABloc(
            @RequestBody List<Long> numChambres,
            @PathVariable("nomBloc") String nomBloc
    ) {
        return blocService.affecterChambresABloc(numChambres, nomBloc);
    }
}
