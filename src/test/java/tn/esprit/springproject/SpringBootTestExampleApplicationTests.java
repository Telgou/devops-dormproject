package tn.esprit.springproject;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.springproject.Service.UniversiteService;
import tn.esprit.springproject.entity.Universite;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AllArgsConstructor
public class SpringBootTestExampleApplicationTests {

    private UniversiteService universiteService;


}
