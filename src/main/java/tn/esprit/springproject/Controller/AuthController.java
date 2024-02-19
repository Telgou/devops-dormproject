package tn.esprit.springproject.Controller;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.springproject.Dto.CredentialsDto;
import tn.esprit.springproject.Dto.SignUpDto;
import tn.esprit.springproject.Dto.UserDto;
import tn.esprit.springproject.Repository.UserRespository;
import tn.esprit.springproject.config.UserAuthProvider;
import tn.esprit.springproject.entity.ResetPasswordToken;
import tn.esprit.springproject.entity.Role;
import tn.esprit.springproject.Service.PasswordResetService;
import tn.esprit.springproject.Service.UserService;
import tn.esprit.springproject.entity.User;

import java.net.URI;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final UserAuthProvider userAuthProvider;
    private final PasswordResetService passwordResetService;
    private final UserRespository respository;
    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody CredentialsDto credentialsDto){
        UserDto user = userService.login(credentialsDto);
        user.setToken(userAuthProvider.createToken(user));
        return ResponseEntity.ok(user);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody SignUpDto signUpDto){
        UserDto  user =   userService.register(signUpDto);
        user.setToken(userAuthProvider.createToken(user));
        user.setRole(Role.UNIVERSITY);
        return ResponseEntity.created(URI.create("/users/"+ user.getId())).body(user);
    }

    @PostMapping("/initiate")
    public ResponseEntity<ResetPasswordToken> initiatePasswordReset(@RequestBody Map<String, String> requestBody) {
        String email = requestBody.get("login");
        String token = requestBody.get("token");
        passwordResetService.initiatePasswordReset(email);

        return ResponseEntity.ok(passwordResetService.initiatePasswordReset(email));
    }
    @PostMapping("/confirm")
    public ResponseEntity<Void> confirmResetPassword(@RequestBody Map<String, String> requestBody) {
        String token = requestBody.get("token");
        String Password = requestBody.get("password");
        boolean success = passwordResetService.confirmPasswordReset(token,Password);

        if (success) {
            return ResponseEntity.ok().build();
        } else {
            // Token is invalid or expired
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/userinfo/{id}")
    public Optional<User> getUser(@PathVariable("id") Long id){
        Optional<User> user = respository.findById(id);
      return user;
    }
}


