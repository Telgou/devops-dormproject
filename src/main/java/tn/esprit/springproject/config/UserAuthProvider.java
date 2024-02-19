package tn.esprit.springproject.config;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import tn.esprit.springproject.Dto.UserDto;
import com.auth0.jwt.JWT;
import tn.esprit.springproject.exceptions.AppException;
import tn.esprit.springproject.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class UserAuthProvider {
    @Value("${security.jwt.token.secret-key:secret-key")
    private String secretKey;
    private static final Logger logger = LoggerFactory.getLogger(UserAuthProvider.class);

    private final UserService userService;
    @PostConstruct
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(UserDto dto){

        Date now = new Date();
        Date validity = new Date(now.getTime()+3_600_000);
        return JWT.create()
                .withIssuer(dto.getLogin())
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .withClaim("id",dto.getId())
                .withClaim("role", String.valueOf(dto.getRole()))

                .sign(Algorithm.HMAC256(secretKey));
    }

    public Authentication validateToken(String token) {
        try {
            DecodedJWT decoded = JWT.require(Algorithm.HMAC256(secretKey))
                    .build()
                    .verify(token);

            UserDto user = UserDto.builder()
                    .login(decoded.getIssuer())
                    .firstName(decoded.getClaim("id").asString())
                    .lastName(decoded.getClaim("role").asString())
                    .build();

            return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
        } catch (JWTVerificationException e) {
            // Log the exception
            logger.error("Error validating token", e);
            throw new AppException("Error validating token", HttpStatus.BAD_REQUEST);
        }
    }

    public Authentication validateTokenStrongly(String token) {
        try {
            DecodedJWT decoded = JWT.require(Algorithm.HMAC256(secretKey))
                    .build()
                    .verify(token);

          UserDto user = userService.findByLogin(decoded.getIssuer());
            return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
        } catch (JWTVerificationException e) {
            // Log the exception
            logger.error("Error validating token strongly", e);
            throw new AppException("Error validating token strongly", HttpStatus.BAD_REQUEST);
        }
    }

}
