package tn.esprit.springproject.Controller;

import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import tn.esprit.springproject.Service.iReservationService;
import tn.esprit.springproject.entity.RecaptchaResponse;
import tn.esprit.springproject.entity.Reservation;

import java.util.List;
@RestController
@RequestMapping("/reservations")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor
public class ReservationController {

    private iReservationService reservationService;

    private static final String RECAPTCHA_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";


    @GetMapping("/retrieve-all-reservations")
    public List<Reservation> getReservations() {
        return reservationService.retrieveAllReservations();
    }

    @GetMapping("/retrieve-reservation/{reservation-id}")
    public Reservation retrieveReservation(@PathVariable("reservation-id") Long reservationId) {
        return reservationService.retrieveReservation(reservationId);
    }

    @PostMapping("/add-reservation")
    public Reservation addReservation(@RequestBody Reservation e) {
        return reservationService.addReservation(e);
    }

    @DeleteMapping("/remove-reservation/{reservation-id}")
    public void removeReservation(@PathVariable("reservation-id") Long reservationId) {
        reservationService.removeReservation(reservationId);
    }

    @PutMapping("/update-reservation")
    public Reservation updateReservation(@RequestBody Reservation e) {
        return reservationService.updateReservation(e);
    }
    @PostMapping("/yourEndpoint")
    public String verifyRecaptcha(@RequestParam("recaptchaResponse") String recaptchaResponse) {
        RestTemplate restTemplate = new RestTemplate();
        String url = RECAPTCHA_VERIFY_URL
                + "&response=" + recaptchaResponse;

        RecaptchaResponse response = restTemplate.postForObject(url, null, RecaptchaResponse.class);
        if (response != null && response.isSuccess()) {
            return "reCAPTCHA verified successfully";
        } else {
            return "reCAPTCHA verification failed";
        }
    }
    @GetMapping("/generate/{reservation-id}")
    public ByteArrayResource generatePDF(@PathVariable("reservation-id") Long idReservation) {
        return reservationService.generatePDFFromReservationData(idReservation);
    }
}

