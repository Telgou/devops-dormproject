package tn.esprit.springproject.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tn.esprit.springproject.config.PasswordConfig;
import tn.esprit.springproject.entity.ResetPasswordToken;
import tn.esprit.springproject.entity.User;
import tn.esprit.springproject.Repository.ResetPasswordTokenRepository;
import tn.esprit.springproject.Repository.UserRespository;

import java.nio.CharBuffer;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PasswordResetService {

    private final ResetPasswordTokenRepository tokenRepository;
    private final UserRespository userRespository;
    private final MailSender mailSender;
    private final PasswordEncoder passwordEncoder;

    public ResetPasswordToken  initiatePasswordReset(String login) {
        // Generate a unique token
        String token = UUID.randomUUID().toString();
        LocalDateTime expirationDateTime = LocalDateTime.now().plusHours(24);

        // Save the token in the database
        ResetPasswordToken resetToken = new ResetPasswordToken();
        resetToken.setToken(token);
        resetToken.setLogin(login);
        resetToken.setExpiryDate(expirationDateTime);
        tokenRepository.save(resetToken);

        // Send password reset email
        sendPasswordResetEmail(login, token);

        return resetToken;
    }

    private void sendPasswordResetEmail(String login, String token) {
        if (login != null && login.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(login.trim());
            mailMessage.setSubject("Password Reset Request");
            mailMessage.setText("To reset your password, click the following link: "
                    + "http://localhost:4200/confirmpassword/" + token);

            // Send the email
            mailSender.send(mailMessage);
        } else {
            // Handle invalid email address
            System.out.println("Invalid email address: " + login);
        }
    }

    public boolean confirmPasswordReset(String token, String newPassword) {
        ResetPasswordToken resetToken = tokenRepository.findByToken(token);

        if (resetToken != null) {
            // Reset the user's password
            String login = resetToken.getLogin();
            Optional<User> user = userRespository.findByLogin(login);
            user.ifPresent(u -> {
                u.setPassword(passwordEncoder.encode(CharBuffer.wrap(newPassword)));
                userRespository.save(u);
            });
            resetToken.setExpiryDate(LocalDateTime.now());
            tokenRepository.save(resetToken);
            return true;
        }
        return false;
    }

}