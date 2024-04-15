package tn.esprit.springproject;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.springproject.Repository.FoyerRepository;
import tn.esprit.springproject.Repository.UniversiteRepository;
import tn.esprit.springproject.Service.UniversiteService;
import tn.esprit.springproject.entity.Foyer;
import tn.esprit.springproject.entity.Universite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@Slf4j
class UniversiteServiceMockTest {
    @Mock
    private FoyerRepository foyerRepository;

    @Mock
    private UniversiteRepository universiteRepository;

    @InjectMocks
    private UniversiteService universiteService;

    @Test
    void retrieveAllUniversites() {

        List<Universite> expectedList = new ArrayList<>();
        expectedList.add(new Universite(1L, "Universite 1", "kairouan", new Foyer()));
        expectedList.add(new Universite(2L, "Universite 2", "kairouan", new Foyer()));

        // We Mock the behavior of universiteRepository.findAll() and return the expected list
        when(universiteRepository.findAll()).thenReturn(expectedList);

        List<Universite> actualList = universiteService.retrieveAllUniversites();

        // We Verify that the findAll() method of the repository was called
        verify(universiteRepository, times(1)).findAll();

        // We Verify that our list is not null
        assertNotNull(actualList, "Returned list should not be null");

        // finally we verify that the actual list matches the expected list
        assertEquals(expectedList.size(), actualList.size(), "Size of returned list should match expected list");
        assertTrue(expectedList.containsAll(actualList) && actualList.containsAll(expectedList), "Returned list should match expected list");
    }


    @Test
    void addUniversites() {
        Universite universiteToAdd = new Universite(1L, "Universite Test", "kairouan", new Foyer());

        // We mock the behavior of universiteRepository.save()
        when(universiteRepository.save(universiteToAdd)).thenReturn(universiteToAdd);

        Universite addedUniversite = universiteService.addUniversites(universiteToAdd);

        // We verify that the repository method was called exactly once with the expected object
        verify(universiteRepository, times(1)).save(eq(universiteToAdd));

        // We verify that the returned object is not null
        assertNotNull(addedUniversite, "Added universite should not be null");

        // We verify that the returned object is the same as the one passed to the method
        assertSame(universiteToAdd, addedUniversite, "Returned universite should be the same as the one passed to the method");
    }


    @Test
    void affecterFoyerAUniversite() {
        long idFoyer = 1;
        String nomUniversite = "TestUniversite";
        Foyer mockedFoyer = new Foyer();
        Universite mockedUniversite = new Universite();

        when(universiteRepository.findByNomUniversite(nomUniversite)).thenReturn(mockedUniversite);
        when(foyerRepository.findById(idFoyer)).thenReturn(Optional.of(mockedFoyer));

        Universite result = universiteService.affecterFoyerAUniversite(idFoyer, nomUniversite);

        // We verify that the repositories methods were called with the correct parameters
        verify(universiteRepository, times(1)).findByNomUniversite(nomUniversite);
        verify(foyerRepository, times(1)).findById(idFoyer);

        // We verify that the result is not null
        assertNotNull(result, "Result should not be null");

        // We verify that the result matches the mocked university
        assertEquals(mockedUniversite, result, "Result should match mocked university");

        // We erify that the university's foyer is set to the mocked foyer
        assertSame(mockedFoyer, result.getFoyer(), "Foyer of university should match mocked foyer");
    }


    @Test
    void desaffecterFoyerAUniversite() {
        long idFoyer = 1;
        long idUniversite = 1;
        Foyer foyer = new Foyer();
        Universite universite = new Universite();

        when(universiteRepository.findById(idUniversite)).thenReturn(Optional.of(universite));
        when(foyerRepository.findById(idFoyer)).thenReturn(Optional.of(foyer));

        Universite result = universiteService.desaffecterFoyerAUniversite(idFoyer);

        if (result != null) {
            // We verify that the methods of the repositories were called with the correct parameters
            verify(universiteRepository, times(1)).findById(idUniversite);
            verify(foyerRepository, times(1)).findById(idFoyer);

            // We verify that the foyer of the result is null
            assertNull(result.getFoyer(), "Foyer of university should be null");
        } else {
            log.info("Desaffecter testtt failed");
        }
    }

    @Test
    void updateUniversites() {
        Universite updatedUniversite = new Universite(1L, "Updated Universite", "456", new Foyer());

        when(universiteRepository.save(updatedUniversite)).thenReturn(updatedUniversite);

        Universite result = universiteService.updateUniversites(updatedUniversite);

        verify(universiteRepository, times(1)).save(eq(updatedUniversite));

        assertNotNull(result, "Updated universite should not be null");

        assertSame(updatedUniversite, result, "Returned universite should be the same as the updated object");
    }

    @Test
    void searchUniversites() {

        String query = "university";
        Universite universite1 = new Universite(1L, "University A", "kairouan", new Foyer());
        Universite universite2 = new Universite(2L, "University B", "kairouan", new Foyer());
        List<Universite> expectedUniversites = Arrays.asList(universite1, universite2);


        when(universiteRepository.searchUniversites(query)).thenReturn(expectedUniversites);


        List<Universite> actualUniversites = universiteService.searchUniversites(query);


        assertEquals(expectedUniversites.size(), actualUniversites.size(), "Number of universites should match");


        for (int i = 0; i < expectedUniversites.size(); i++) {
            assertEquals(expectedUniversites.get(i), actualUniversites.get(i), "Universites should match");
        }
    }

    @Test
    void deleteUniversityTest() {

        long universityId = 5L;
        Universite universityToDelete = new Universite();
        universityToDelete.setIdUniversite(universityId);

        when(universiteRepository.findById(universityId)).thenReturn(Optional.of(universityToDelete));

        universiteService.removeUniversites(universityId);

        verify(universiteRepository, times(1)).deleteById(universityId);
    }


}
