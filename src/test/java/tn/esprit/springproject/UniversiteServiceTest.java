package tn.esprit.springproject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.springproject.Repository.FoyerRepository;
import tn.esprit.springproject.Repository.UniversiteRepository;
import tn.esprit.springproject.Service.UniversiteService;
import tn.esprit.springproject.entity.Foyer;
import tn.esprit.springproject.entity.Universite;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class UniversiteServiceTest {
    private FoyerRepository foyerRepository;
    private UniversiteRepository universiteRepository;
    @Autowired
    private UniversiteService universiteService;
    private List<Universite> universiteList;

    @BeforeEach
    void setUp() {
        foyerRepository = mock(FoyerRepository.class);
        universiteRepository = mock(UniversiteRepository.class);

        universiteList = new ArrayList<>();
    }
/*
    @Test
    void addUniversites() {
        // Given
        Universite universiteToAdd = new Universite(1L, "Test1", "Address1", null);

        // When
        Universite result = universiteService.addUniversites(universiteToAdd);

        // Then
        assertEquals(universiteToAdd, result);
        //assertEquals(1, universiteList.size());
        //assertEquals(universiteToAdd, universiteList.get(0));
    }

    @Test
    void retrieveAllUniversites() {
        // Given
        universiteList.add(new Universite(1L, "Test1", "Address1", null));
        universiteList.add(new Universite(2L, "Test2", "Address2", null));

        // When
        List<Universite> result = universiteService.retrieveAllUniversites();

        // Then
        assertEquals(universiteList, result);
    }


    @Test
    void affecterFoyerAUniversite() {
        // Given
        long idFoyer = 1L;
        String nomUniversite = "TestUniversite";
        Foyer foyer = new Foyer();
        Universite universite = new Universite(1L, nomUniversite, "Address1", null);

        // When
        Universite result = universiteService.affecterFoyerAUniversite(idFoyer, nomUniversite);

        // Then
        assertEquals(universite, result);
        assertEquals(foyer, result.getFoyer());
    }

    @Test
    void desaffecterFoyerAUniversite() {
        // Given
        long idFoyer = 1L;
        Foyer foyer = new Foyer();
        Universite universite = new Universite(1L, "TestUniversite", "Address1", foyer);

        // When
        Universite result = universiteService.desaffecterFoyerAUniversite(idFoyer);

        // Then
        assertEquals(universite, result);
        assertNull(result.getFoyer());
    }
*/
    // Add similar tests for other methods in UniversiteService
}

/*
@SpringBootTest
class UniversiteServiceTest {

    private FoyerRepository foyerRepository;
    private UniversiteRepository universiteRepository;
    @Autowired
    private UniversiteService universiteService;

    @BeforeEach
    void setUp() {
        foyerRepository = mock(FoyerRepository.class);
        universiteRepository = mock(UniversiteRepository.class);
    }

    @Test
    void retrieveAllUniversites() {
        // Mocking the behavior of universiteRepository.findAll()
        List<Universite> universites = new ArrayList<>();
        when(universiteRepository.findAll()).thenReturn(universites);

        assertEquals(universites, universiteService.retrieveAllUniversites());
    }

    @Test
    void addUniversites() {
        // Mocking the behavior of universiteRepository.save()
        Universite universiteToAdd = new Universite();
        when(universiteRepository.save(universiteToAdd)).thenReturn(universiteToAdd);

        assertEquals(universiteToAdd, universiteService.addUniversites(universiteToAdd));
    }

    // Add similar tests for other methods in UniversiteService

    @Test
    void affecterFoyerAUniversite() {
        // Mocking the behavior of repositories and entities
        long idFoyer = 1;
        String nomUniversite = "TestUniversite";
        Foyer mockedFoyer = new Foyer();
        Universite mockedUniversite = new Universite();

        when(universiteRepository.findByNomUniversite(nomUniversite)).thenReturn(mockedUniversite);
        when(foyerRepository.findById(idFoyer)).thenReturn(Optional.of(mockedFoyer));

        // Perform the method
        Universite result = universiteService.affecterFoyerAUniversite(idFoyer, nomUniversite);

        // Assertions
        assertEquals(mockedUniversite, result);
        assertEquals(mockedFoyer, mockedUniversite.getFoyer());
    }

    // Add similar tests for other methods in UniversiteService


}
*/