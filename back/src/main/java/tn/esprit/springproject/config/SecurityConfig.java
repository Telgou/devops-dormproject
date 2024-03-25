package tn.esprit.springproject.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RequiredArgsConstructor
@Configuration
@EnableWebMvc
@Slf4j
public class SecurityConfig {
    private final UserAuthProvider userAuthProvider;

   @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       log.info(" NEW WORKING ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ");
       http.csrf().disable();

       return http.build();
    }
}
