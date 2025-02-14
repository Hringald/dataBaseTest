package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
@Configuration
public class WebSecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())  // Disable CSRF for API access
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // No session tracking
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/test").permitAll()  // Allow public access to this endpoint
                        .anyRequest().authenticated()  // Secure all other endpoints
                )
                .httpBasic(httpBasic -> httpBasic.disable())  // Disable Basic Authentication
                .formLogin(form -> form.disable())  // Disable form-based authentication
                .build();
    }

}












