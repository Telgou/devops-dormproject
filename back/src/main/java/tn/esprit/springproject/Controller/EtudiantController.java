package tn.esprit.springproject.Controller;

import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.springproject.Repository.EtudiantRepository;
import tn.esprit.springproject.Service.iEtudiantService;
import tn.esprit.springproject.entity.Etudiant;


import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping("/etudiant")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor
public class EtudiantController
{

    private iEtudiantService etudiantsevice;
    EtudiantRepository etudiantRepository;

    @GetMapping("/retrieve-all-etudiants")
    public List<Etudiant> getEtudiants() {
    return etudiantsevice.retrieveAllEtudiants();
}
    @GetMapping("/retrieve-etudiant/{etudiant-id}")
    public Etudiant retrieveEtudiant(@PathVariable("etudiant-id") Long etudiantId) { return etudiantsevice.retrieveEtudiant(etudiantId);
    }
    @PostMapping("/add-etudiant")
    public Etudiant addEtudiant(@RequestBody Etudiant e) {
        return etudiantsevice.addEtudiant(e);
    }
    @DeleteMapping("/remove-etudiant/{etudiant-id}")
    public void removeEtudiant(@PathVariable("etudiant-id") Long etudiantId) { etudiantsevice.removeEtudiant(etudiantId);
    }
    @PutMapping("/update-etudiant")
    public Etudiant updateEtudiant(@RequestBody Etudiant e) {
        return etudiantsevice.updateEtudiant(e);
    }
    @PostMapping("/affecter-etud/{nomEt}/{prenomEt}/{idReservation}")
    public Etudiant affecterEtudiantAReservation(  @PathVariable("nomEt") String nomEt,@PathVariable("prenomEt") String prenomEt,@PathVariable("idReservation") long idReservation)

    {

        return etudiantsevice.affecterEtudiantAReservation( nomEt,  prenomEt,  idReservation);
    }

    @GetMapping(value = "/openpdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> etudiantReport() {
        List<Etudiant> etudiants = etudiantRepository.findAll();

        ByteArrayInputStream bis = etudiantsevice.etudiantPDFReport(etudiants);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=etudiants.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }


}
