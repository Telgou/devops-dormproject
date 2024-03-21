package tn.esprit.springproject.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import tn.esprit.springproject.Repository.BlocRepository;
import tn.esprit.springproject.Repository.NotificationRepository;
import tn.esprit.springproject.entity.Bloc;
import tn.esprit.springproject.entity.Foyer;
import tn.esprit.springproject.Repository.FoyerRepository;
import tn.esprit.springproject.entity.Notification;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FoyerService implements iFoyerService {
    @Autowired
    FoyerRepository foyerRespository;
    @Autowired
    BlocRepository blocRepository;
    @Autowired
    private JavaMailSender mailsender;
    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public List<Foyer> retrieveAllFoyers() {
        return foyerRespository.findAll();
    }

    @Override
    public Foyer addFoyer(Foyer foyer) {
        Foyer addedFoyer = foyerRespository.save(foyer);
        System.out.println("Foyer ajouté : " + addedFoyer.getNomFoyer());

        // Après avoir ajouté le foyer, créez une notification
        String message = "Un nouveau foyer a été ajouté : " + addedFoyer.getNomFoyer(); // Personnalisez le message selon vos besoins
        // createNotification(addedFoyer.getResponsableUsername(), message);

        return addedFoyer;
    }

    @Override
    public Foyer updateFoyer(Foyer e) throws Exception {
        Optional<Foyer> foyerUpdated = foyerRespository.findById(e.getIdFoyer());
        if (foyerUpdated.isPresent()) {
            Foyer readyToUpdate = foyerUpdated.get();
            readyToUpdate.setNomFoyer(e.getNomFoyer());
            readyToUpdate.setCapaciteFoyer(e.getCapaciteFoyer());
            return foyerRespository.save(readyToUpdate);

        } else throw new Exception("foyer non trouvable avec id " + e.getIdFoyer());

    }

    @Override
    public Foyer retrieveFoyer(Long idFoyer) {
        return foyerRespository.findById(idFoyer).get();
    }

    @Override
    public void removeFoyer(Long idFoyer) {
        foyerRespository.deleteById(idFoyer);

    }

    public void archiverFoyer(long idFoyer) {
        Foyer foyer = foyerRespository.findById(idFoyer).orElse(null);
        if (foyer != null) {
            foyer.setArchived(true);
            foyerRespository.save(foyer);
        }
    }

    public Foyer addFoyerWithBloc(Foyer foyer) {

        Foyer f = foyerRespository.save(foyer);

        f.getBlocs().forEach(bloc ->
        {
            bloc.setFoyer(f);
            blocRepository.save(bloc);

        });
        return f;


    }
/*
    public void sendEmail(String toEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("farah.battikh.2001@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        mailsender.send(message);
        System.out.println("mail sent");
    }

    public List<Notification> getNotificationsByUser(String username) {
        return notificationRepository.findByUsernameOrderByTimestampDesc(username);
    }

    public void createNotification(String username, String message) {
        System.out.println("Création d'une notification pour " + username + " : " + message);

        Notification notification = new Notification();
        notification.setUsername(username);
        notification.setMessage(message);
        notification.setTimestamp(LocalDateTime.now());
        notificationRepository.save(notification);
    }
*/
}
