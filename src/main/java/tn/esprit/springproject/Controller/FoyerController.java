package tn.esprit.springproject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.springproject.Service.iFoyerService;
import tn.esprit.springproject.entity.Foyer;
import tn.esprit.springproject.entity.Notification;

import java.util.List;

@RestController
@RequestMapping("/foyer")
public class FoyerController {

    @Autowired
    private iFoyerService foyerService;


    @GetMapping("/retrieve-all-foyers")
    public List<Foyer> getFoyers() {
        List<Foyer> listFoyers = foyerService.retrieveAllFoyers();
        return listFoyers;
    }

    @GetMapping("/retrieve-foyer/{foyer-id}")
    public Foyer retrieveFoyer(@PathVariable("foyer-id") Long foyerId) {
        return foyerService.retrieveFoyer(foyerId);
    }

    @PostMapping("/add-foyer")
    public ResponseEntity<Foyer> addFoyer(@RequestBody Foyer foyer) {
        Foyer addedFoyer = foyerService.addFoyer(foyer);
        return new ResponseEntity<Foyer>(addedFoyer, HttpStatus.CREATED);
    }

    @DeleteMapping("/remove-foyer/{foyer-id}")
    public void removeFoyer(@PathVariable("foyer-id") Long foyerId) {
        foyerService.removeFoyer(foyerId);
    }

    @PutMapping("/update-foyer")
    public ResponseEntity<?> updateFoyer(@RequestBody Foyer e) throws Exception {
      try{
          return new ResponseEntity<>(foyerService.updateFoyer(e),HttpStatus.CREATED);

      }
catch (Exception exp){
          return new ResponseEntity<>(exp.getMessage(),HttpStatus.NOT_FOUND);
}
    }
    @PutMapping("/add-foyerwith-bloc")
    public Foyer addFoyerWithBloc(@RequestBody Foyer f){

        Foyer foyer = foyerService.addFoyerWithBloc(f);
        return foyer ;

    }

    @GetMapping("notif/{username}")
    public ResponseEntity<List<Notification>> getNotifications(@PathVariable String username) {
        List<Notification> notifications = foyerService.getNotificationsByUser(username);
        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }
    @PostMapping("/createNotification")
    public ResponseEntity<String> createNotification(@RequestParam String username, @RequestParam String message) {
        foyerService.createNotification(username, message);
        return new ResponseEntity<>("Notification créée avec succès", HttpStatus.CREATED);
    }

}
