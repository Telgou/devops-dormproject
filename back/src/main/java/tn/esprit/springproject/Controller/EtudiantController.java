package tn.esprit.springproject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.springproject.Repository.EtudiantRepository;
import tn.esprit.springproject.Service.EtudiantService;
import tn.esprit.springproject.Service.iChambreService;
import tn.esprit.springproject.Service.iEtudiantService;
import tn.esprit.springproject.entity.Etudiant;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/etudiant")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EtudiantController
{

    @Autowired
    private iEtudiantService etudiantsevice;
    @Autowired
    EtudiantRepository etudiantRepository;

    @GetMapping("/retrieve-all-etudiants")
    public List<Etudiant> getEtudiants() {
    List<Etudiant> listEtudiants = etudiantsevice.retrieveAllEtudiants();
    return listEtudiants;
}
    @GetMapping("/retrieve-etudiant/{etudiant-id}")
    public Etudiant retrieveEtudiant(@PathVariable("etudiant-id") Long etudiantId) { return etudiantsevice.retrieveEtudiant(etudiantId);
    }
    @PostMapping("/add-etudiant")
    public Etudiant addEtudiant(@RequestBody Etudiant e) {
        Etudiant etudiant = etudiantsevice.addEtudiant(e);
        return etudiant;
    }
    @DeleteMapping("/remove-etudiant/{etudiant-id}")
    public void removeEtudiant(@PathVariable("etudiant-id") Long etudiantId) { etudiantsevice.removeEtudiant(etudiantId);
    }
    @PutMapping("/update-etudiant")
    public Etudiant updateEtudiant(@RequestBody Etudiant e) {
        Etudiant etudiant= etudiantsevice.updateEtudiant(e);
        return etudiant;
    }
    @PostMapping("/affecter-etud/{nomEt}/{prenomEt}/{idReservation}")
    public Etudiant affecterEtudiantAReservation(  @PathVariable("nomEt") String nomEt,@PathVariable("prenomEt") String prenomEt,@PathVariable("idReservation") long idReservation)

    {

        return etudiantsevice.affecterEtudiantAReservation( nomEt,  prenomEt,  idReservation);
    }
    /*
    @GetMapping("/pagination/{offset}/{pageSize}")
    private APIResponse<Page<Etudiant>> findEtudiantsWithPagination(@PathVariable int offset, @PathVariable int pageSize) {
        Page<Etudiant> productsWithPagination = etudiantsevice.findEtudiantsWithPagination(offset, pageSize);
        return new APIResponse<>(productsWithPagination.getSize(), productsWithPagination);
    }*/

    @GetMapping(value = "/openpdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> etudiantReport()  throws IOException {
        List<Etudiant> etudiants = (List<Etudiant>) etudiantRepository.findAll();

        ByteArrayInputStream bis = etudiantsevice.etudiantPDFReport(etudiants);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=etudiants.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }


}
