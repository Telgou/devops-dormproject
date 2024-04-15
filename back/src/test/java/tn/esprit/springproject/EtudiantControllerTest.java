import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.Collections;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tn.esprit.springproject.Controller.*;
import tn.esprit.springproject.Service.*;
import tn.esprit.springproject.entity.*;

@ExtendWith(MockitoExtension.class)
class EtudiantControllerTest {

    @Mock
    private iEtudiantService etudiantService;

    @InjectMocks
    private EtudiantController etudiantController;

    @Test
    void getEtudiants_ReturnsListOfEtudiants() throws Exception {
        // Mocking service behavior
        when(etudiantService.retrieveAllEtudiants()).thenReturn(Collections.singletonList(new Etudiant()));

        // Testing controller
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(etudiantController).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/etudiant/retrieve-all-etudiants"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void retrieveEtudiant_ReturnsSingleEtudiant() throws Exception {
        // Mocking service behavior
        when(etudiantService.retrieveEtudiant(anyLong())).thenReturn(new Etudiant());

        // Testing controller
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(etudiantController).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/etudiant/retrieve-etudiant/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$").isMap());
    }

    @Test
    void addEtudiant_ReturnsAddedEtudiant() throws Exception {
        // Mocking service behavior
        when(etudiantService.addEtudiant(any())).thenReturn(new Etudiant());

        // Testing controller
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(etudiantController).build();
        mockMvc.perform(MockMvcRequestBuilders.post("/etudiant/add-etudiant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$").isMap());
    }

    @Test
    void removeEtudiant_ReturnsNoContent() throws Exception {
        // Mocking service behavior
        doNothing().when(etudiantService).removeEtudiant(anyLong());

        // Testing controller
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(etudiantController).build();
        mockMvc.perform(MockMvcRequestBuilders.delete("/etudiant/remove-etudiant/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void updateEtudiant_ReturnsUpdatedEtudiant() throws Exception {
        // Mocking service behavior
        when(etudiantService.updateEtudiant(any())).thenReturn(new Etudiant());

        // Testing controller
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(etudiantController).build();
        mockMvc.perform(MockMvcRequestBuilders.put("/etudiant/update-etudiant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$").isMap());
    }
}
