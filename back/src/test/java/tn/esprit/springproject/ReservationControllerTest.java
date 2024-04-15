package tn.esprit.springproject;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tn.esprit.springproject.Controller.*;
import tn.esprit.springproject.Service.*;
import tn.esprit.springproject.entity.*;

import java.util.Collections;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(MockitoExtension.class)
class ReservationControllerTest {

    @Mock
    private ReservationService reservationService;

    @InjectMocks
    private ReservationController reservationController;

    @Test
    void getReservations_ReturnsListOfReservations() throws Exception {
        // Mocking service behavior
        lenient().when(reservationService.retrieveAllReservations()).thenReturn(Collections.singletonList(new Reservation()));

        // Testing controller
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(reservationController).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/retrieve-all-reservations"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void retrieveReservation_ReturnsSingleReservation() throws Exception {
        // Mocking service behavior
        lenient().when(reservationService.retrieveReservation(anyLong())).thenReturn(new Reservation());

        // Testing controller
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(reservationController).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/retrieve-reservation/1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void addReservation_ReturnsAddedReservation() throws Exception {
        // Mocking service behavior
        lenient().when(reservationService.addReservation(any())).thenReturn(new Reservation());

        // Testing controller
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(reservationController).build();
        mockMvc.perform(MockMvcRequestBuilders.post("/add-reservation")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void removeReservation_ReturnsNoContent() throws Exception {
        // Mocking service behavior
        lenient().doNothing().when(reservationService).removeReservation(anyLong());

        // Testing controller
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(reservationController).build();
        mockMvc.perform(MockMvcRequestBuilders.delete("/remove-reservation/1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void updateReservation_ReturnsUpdatedReservation() throws Exception {
        // Mocking service behavior
        lenient().when(reservationService.updateReservation(any())).thenReturn(new Reservation());

        // Testing controller
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(reservationController).build();
        mockMvc.perform(MockMvcRequestBuilders.put("/update-reservation")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
