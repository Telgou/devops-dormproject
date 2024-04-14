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
import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;
import org.mockito.Mock;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@ExtendWith(SpringExtension.class)
class UniversiteServiceTest {
    @Mock
    UniversiteRepository universiteRepository;

    @InjectMocks
    UniversiteService nouveauServiceUniversite;
    @Mock
    FoyerRepository foyerRepository;
/*
    @Test
    void testAjouterUniversite() {
        // Créez une nouvelle université
        Universite universite = new Universite(3, "Nouvelle Universite");

        // Définissez le comportement simulé du repository
        when(universiteRepository.save(any(Universite.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Appelez la méthode du service pour ajouter l'université
        Universite universiteAjoutee = nouveauServiceUniversite.addUniversites(universite);

        // Vérifiez si l'université a été ajoutée avec succès
        assertNotNull(universiteAjoutee);
        assertEquals(3, universiteAjoutee.getIdUniversite());
        assertEquals("Nouvelle Universite", universiteAjoutee.getNomUniversite());
    }

    @Test
    void testRecupererToutesUniversites() {
        // Créez des données simulées pour les universités
        Universite universite1 = new Universite(1, "Universite 1");
        Universite universite2 = new Universite(2, "Universite 2");

        // Définissez le comportement simulé du repository pour retourner les universités simulées
        when(universiteRepository.findAll()).thenReturn(Arrays.asList(universite1, universite2));

        // Appelez la méthode du service pour récupérer toutes les universités
        List<Universite> universiteList = nouveauServiceUniversite.retrieveAllUniversites();

        // Vérifiez les résultats
        assertEquals(2, universiteList.size());
        assertEquals("Universite 1", universiteList.get(0).getNomUniversite());
        assertEquals("Universite 2", universiteList.get(1).getNomUniversite());
    }

    @Test
    void testRecupererUniversiteParId() {
        // Créez une université simulée
        Universite universite = new Universite(4, "Universite Test");

        // Définissez le comportement simulé du repository pour renvoyer l'université simulée par son ID
        when(universiteRepository.findById(4L)).thenReturn(Optional.of(universite));

        // Appelez la méthode du service pour récupérer l'université par son ID
        Universite universiteRecuperee = nouveauServiceUniversite.retrieveAllUniversites().get(4);

        // Vérifiez si l'université récupérée correspond à celle attendue
        assertNotNull(universiteRecuperee);
        assertEquals(4, universiteRecuperee.getIdUniversite());
        assertEquals("Universite Test", universiteRecuperee.getNomUniversite());
    }

*/
    /*
    @Test
    void testSupprimerUniversite() {
        // Appelez la méthode du service pour supprimer une université avec un ID donné
        nouveauServiceUniversite.removeUniversites(5L);

        // Vérifiez que la méthode deleteById du repository est appelée avec le bon ID
        verify(universiteRepository, times(1)).deleteById(5L);
    }

    @Test
    void testAffecterFoyerAUniversite() {
        // Créez des données simulées pour le foyer et l'université
        Foyer foyer = new Foyer();
        Universite universite = new Universite(6, "Universite Affectée");

        // Définissez le comportement simulé du repository pour retourner les objets correspondants
        when(universiteRepository.findByNomUniversite("Universite Affectée")).thenReturn(universite);
        when(foyerRepository.findById(1L)).thenReturn(Optional.of(foyer));

        // Appelez la méthode du service pour affecter un foyer à une université
        Universite universiteAffectee = nouveauServiceUniversite.affecterFoyerAUniversite(1, "Universite Affectée");

        // Vérifiez si le foyer a été correctement affecté à l'université
        assertNotNull(universiteAffectee.getFoyer());
        assertEquals(foyer, universiteAffectee.getFoyer());
    }*/

/*
    @Test
    void testDesaffecterFoyerAUniversite() {
        // Créez des données simulées pour le foyer et l'université
        Foyer foyer = new Foyer();
        Universite universite = new Universite(6, "Universite Desaffectée");
        universite.setFoyer(foyer);

        // Définissez le comportement simulé du repository pour retourner les objets correspondants
        when(universiteRepository.findById(6L)).thenReturn(Optional.of(universite)); // Assurez-vous que vous utilisez l'ID comme long (7L)
        // Utilisez le foyerRepository simulé
        when(foyerRepository.findById(anyLong())).thenReturn(Optional.of(foyer)); // Utilisez cette ligne pour corriger le problème

        // Appelez la méthode du service pour désaffecter un foyer d'une université
        Universite universiteDesaffectee = nouveauServiceUniversite.desaffecterFoyerAUniversite(1);

        // Vérifiez si le foyer a été correctement désaffecté de l'université
        assertNull(universiteDesaffectee.getFoyer());
    }

*/
}

/*
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

/*
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