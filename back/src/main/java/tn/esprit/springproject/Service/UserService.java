package tn.esprit.springproject.Service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tn.esprit.springproject.Dto.CredentialsDto;
import tn.esprit.springproject.Dto.SignUpDto;
import tn.esprit.springproject.Dto.UserDto;
import tn.esprit.springproject.entity.Role;
import tn.esprit.springproject.entity.User;
import tn.esprit.springproject.exceptions.AppException;
import tn.esprit.springproject.mappers.UserMapper;
import tn.esprit.springproject.Repository.UserRespository;

import java.nio.CharBuffer;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRespository userRespository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    public UserDto login(CredentialsDto credentialsDto) {
        User user = userRespository.findByLogin(credentialsDto.login())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.password()), user.getPassword())) {
            return userMapper.toUserDto(user);
        }
        throw new AppException("Invalid password", HttpStatus
                .BAD_REQUEST);
    }

    public UserDto register(SignUpDto userDto) {
        Optional<User> optionalUser = userRespository.findByLogin(userDto.login());

        if (optionalUser.isPresent()) {
            throw new AppException("Login already exists", HttpStatus.BAD_REQUEST);
        }

        User user = userMapper.SignUptoUser(userDto);
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(userDto.password())));
        user.setRole(Role.UNIVERSITY);
        User savedUser = userRespository.save(user);

        return userMapper.toUserDto(savedUser);
    }

    public UserDto findByLogin(String login) {
        User user = userRespository.findByLogin(login)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        return userMapper.toUserDto(user);
    }


}