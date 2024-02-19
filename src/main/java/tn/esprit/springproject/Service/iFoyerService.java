package tn.esprit.springproject.Service;

import tn.esprit.springproject.entity.Foyer;
import tn.esprit.springproject.entity.Notification;

import java.util.List;

public interface iFoyerService {
    List<Foyer> retrieveAllFoyers();

    Foyer addFoyer(Foyer e);

    Foyer updateFoyer(Foyer e) throws Exception;

    Foyer retrieveFoyer(Long idFoyer);

    void removeFoyer(Long idFoyer);

    void archiverFoyer(long idFoyer);
    public Foyer addFoyerWithBloc(Foyer foyer);
    public void sendEmail(String toEmail,String subject, String body);
    public void createNotification(String username, String message);
    public List<Notification> getNotificationsByUser(String username);
}
