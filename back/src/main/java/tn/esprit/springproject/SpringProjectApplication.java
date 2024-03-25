package tn.esprit.springproject;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.EnableScheduling;
import tn.esprit.springproject.Service.iFoyerService;

@SpringBootApplication
@EnableScheduling
@Slf4j
@ComponentScan(basePackages = {"tn.esprit.springproject", "tn.esprit.springproject.corsconfiguration"})
public class SpringProjectApplication {
    @Autowired
    private iFoyerService foyerService;

    public static void main(String[] args) {
        log.info(" NEW WORKING GAMGAMI AHMEDdddddddddddddddddddddddddddddddddddddddddddddddd");
        SpringApplication.run(SpringProjectApplication.class, args);
    }
/*
    @EventListener(ApplicationReadyEvent.class)
    public void sendEmail()
{
    foyerService.sendEmail(  "ramzi.bennaser@esprit.tn ", "Nouveau foyer disponible", "\n" +


            "Cher utilisateur,\n" +
            "\n" +
            "Nous avons le plaisir de vous informer qu'un nouveau foyer est disponible. Ce foyer est situé à Ariana Soghra, en Tunisie. Il dispose de 3 chambres, 2 salles de bains et d'une terrasse.\n" +
            "\n" +
            "Le foyer est équipé de tout le confort nécessaire, notamment d'une cuisine équipée, d'une climatisation et d'un parking. Il est également situé à proximité des commodités, notamment des écoles, des commerces et des transports publics.\" n\" +" +
            "Cordialement. ");
}
*/

}
