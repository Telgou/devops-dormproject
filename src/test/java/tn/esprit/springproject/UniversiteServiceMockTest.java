package tn.esprit.springproject;

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
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UniversiteServiceMockTest {
    @Mock
    private FoyerRepository foyerRepository;

    @Mock
    private UniversiteRepository universiteRepository;

    @InjectMocks
    private UniversiteService universiteService;
/*
    @Test
    void retrieveAllUniversites() {
        // Mocking the behavior of universiteRepository.findAll()
        List<Universite> universites = new ArrayList<>();
        when(universiteRepository.findAll()).thenReturn(universites);

        assertEquals(universites, universiteService.retrieveAllUniversites());

        // Verify that the repository method was called
        verify(universiteRepository, times(1)).findAll();
    }

    @Test
    void addUniversites() {
        // Mocking the behavior of universiteRepository.save()
        Universite universiteToAdd = new Universite();
        when(universiteRepository.save(universiteToAdd)).thenReturn(universiteToAdd);

        assertEquals(universiteToAdd, universiteService.addUniversites(universiteToAdd));

        // Verify that the repository method was called
        verify(universiteRepository, times(1)).save(universiteToAdd);
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

        // Verify that the repositories methods were called
        verify(universiteRepository, times(1)).findByNomUniversite(nomUniversite);
        verify(foyerRepository, times(1)).findById(idFoyer);
        verify(universiteRepository, times(1)).save(mockedUniversite);
    }

    @Test
    void desaffecterFoyerAUniversite() {
        // Simulation du comportement des repositories et des entités
        long idFoyer = 1;
        long idUniversite = 1;
        Foyer foyer = new Foyer();
        Universite universite = new Universite();

        when(universiteRepository.findById(idUniversite)).thenReturn(Optional.of(universite));
        when(foyerRepository.findById(idFoyer)).thenReturn(Optional.of(foyer));

        // Exécution de la méthode
        Universite result = universiteService.desaffecterFoyerAUniversite(idFoyer);

        if (result != null) {
            // Assertions
            assertNull(result.getFoyer());

            // Vérification que les méthodes des repositories ont été appelées
            verify(universiteRepository, times(1)).findById(idUniversite);
            verify(foyerRepository, times(1)).findById(idFoyer);
        } else {
            // Handle the case where the result is null
            // Log or throw an exception, depending on your requirements
        }
    }*/

}
