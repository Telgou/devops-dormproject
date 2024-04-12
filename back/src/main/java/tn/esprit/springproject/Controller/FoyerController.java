package tn.esprit.springproject.Controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.springproject.Service.iFoyerService;
import tn.esprit.springproject.entity.Foyer;

import java.util.List;

@RestController
@RequestMapping("/foyer")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor
public class FoyerController {

    private iFoyerService foyerService;

    @GetMapping("/retrieve-all-foyers")
    public List<Foyer> getFoyers() {
        return foyerService.retrieveAllFoyers();
    }

    @GetMapping("/retrieve-foyer/{foyer-id}")
    public Foyer retrieveFoyer(@PathVariable("foyer-id") Long foyerId) {
        return foyerService.retrieveFoyer(foyerId);
    }

    @PostMapping("/add-foyer")
    public ResponseEntity<Foyer> addFoyer(@RequestBody Foyer foyer) {
        Foyer addedFoyer = foyerService.addFoyer(foyer);
        return new ResponseEntity<>(addedFoyer, HttpStatus.CREATED);
    }

    @DeleteMapping("/remove-foyer/{foyer-id}")
    public void removeFoyer(@PathVariable("foyer-id") Long foyerId) {
        foyerService.removeFoyer(foyerId);
    }

    @PutMapping("/update-foyer")
    public ResponseEntity<Object> updateFoyer(@RequestBody Foyer e) {
        try {
            Foyer updatedFoyer = foyerService.updateFoyer(e);
            return new ResponseEntity<>(updatedFoyer, HttpStatus.CREATED);
        } catch (Exception exp) {
            return new ResponseEntity<>("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/add-foyerwith-bloc")
    public Foyer addFoyerWithBloc(@RequestBody Foyer f) {

        return foyerService.addFoyerWithBloc(f);

    }

}
