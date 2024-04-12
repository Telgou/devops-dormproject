
package tn.esprit.springproject.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.springproject.Service.iBlocService;
import tn.esprit.springproject.entity.Bloc;

import java.util.List;

@RestController
@RequestMapping("/blocs")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor
public class BlocController {

    private iBlocService blocService;

    @GetMapping("/retrieve-all-blocs")
    public List<Bloc> getBlocs() {
        return blocService.retrieveAllBlocs();
    }

    @GetMapping("/retrieve-bloc/{bloc-id}")
    public Bloc retrieveBloc(@PathVariable("bloc-id") Long blocId) {
        return blocService.retrieveBloc(blocId);
    }

    @PostMapping("/add-bloc")
    public Bloc addBloc(@RequestBody Bloc e) {
        return blocService.addBloc(e);
    }

    @DeleteMapping("/remove-bloc/{bloc-id}")
    public void removeBloc(@PathVariable("bloc-id") Long blocId) {
        blocService.removeBloc(blocId);
    }

    @PutMapping("/update-bloc")
    public Bloc updateBloc(@RequestBody Bloc e) {
        return blocService.updateBloc(e);
    }
    @PostMapping("/affecter-chambres/{nomBloc}")
    public Bloc affecterChambresABloc(
            @RequestBody List<Long> numChambres,
            @PathVariable("nomBloc") String nomBloc
    ) {
        return blocService.affecterChambresABloc(numChambres, nomBloc);
    }
}
