package tn.esprit.springproject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import tn.esprit.springproject.Service.iReservationService;
import tn.esprit.springproject.entity.RecaptchaResponse;
import tn.esprit.springproject.entity.Reservation;

import java.util.Date;
import java.util.List;
@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private iReservationService reservationService;

    private static final String RECAPTCHA_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";
    private static final String SECRET_KEY = "6Lf2jSMpAAAAACW0OFYQGJTxlYdFPf6JYgbqrjms";


    @GetMapping("/retrieve-all-reservations")
    public List<Reservation> getReservations() {
        List<Reservation> listReservations = reservationService.retrieveAllReservations();
        return listReservations;
    }

    @GetMapping("/retrieve-reservation/{reservation-id}")
    public Reservation retrieveReservation(@PathVariable("reservation-id") Long reservationId) {
        return reservationService.retrieveReservation(reservationId);
    }

    @PostMapping("/add-reservation")
    public Reservation addReservation(@RequestBody Reservation e) {
        Reservation reservation = reservationService.addReservation(e);
        return reservation;
    }

    @DeleteMapping("/remove-reservation/{reservation-id}")
    public void removeReservation(@PathVariable("reservation-id") Long reservationId) {
        reservationService.removeReservation(reservationId);
    }

    @PutMapping("/update-reservation")
    public Reservation updateReservation(@RequestBody Reservation e) {
        Reservation reservation = reservationService.updateReservation(e);
        return reservation;
    }
    @PostMapping("/yourEndpoint")
    public String verifyRecaptcha(@RequestParam("recaptchaResponse") String recaptchaResponse) {
        RestTemplate restTemplate = new RestTemplate();
        System.out.println("are we here");
        String url = RECAPTCHA_VERIFY_URL
                + "?secret=" + SECRET_KEY
                + "&response=" + recaptchaResponse;

        RecaptchaResponse response = restTemplate.postForObject(url, null, RecaptchaResponse.class);
        System.out.println("are we here too "+ response.isSuccess());
        if (response != null && response.isSuccess()) {
            System.out.println("successfuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuul");
            return "reCAPTCHA verified successfully";
        } else {
            System.out.println("faileeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeed");
            return "reCAPTCHA verification failed";
        }
    }
    @GetMapping("/generate/{reservation-id}")
    public ByteArrayResource generatePDF(@PathVariable("reservation-id") Long idReservation) {
        return reservationService.generatePDFFromReservationData(idReservation);
    }
}

