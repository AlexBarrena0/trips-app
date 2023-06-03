package edu.uoc.abarrena.trips;

import edu.uoc.abarrena.trips.application.security.StoreTokenFilter;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.crypto.SecretKey;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    @Autowired
    private StoreTokenFilter storeTokenFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .addFilterBefore(storeTokenFilter, BasicAuthenticationFilter.class)
                .csrf()
                .disable()
                .cors()
                .and()
                .authorizeHttpRequests((auth) -> auth
                        // Booking
                        .requestMatchers(HttpMethod.POST, "/bookings").hasAnyRole("TRAVELER")
                        .requestMatchers(HttpMethod.PATCH, "/images/**").hasAnyRole("COMPANY")
                        // Cruise
                        .requestMatchers(HttpMethod.POST, "/cruises").hasAnyRole("COMPANY")
                        .requestMatchers(HttpMethod.GET, "/cruises/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/cruises").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/cruises/**").hasAnyRole("COMPANY")
                        .requestMatchers(HttpMethod.DELETE, "/cruises/**").hasAnyRole("COMPANY")
                        // Destination
                        .requestMatchers(HttpMethod.POST, "/destinations").hasAnyRole("COMPANY")
                        .requestMatchers(HttpMethod.GET, "/destinations").permitAll()
                        // Rating
                        .requestMatchers(HttpMethod.POST, "/ratings").hasAnyRole("TRAVELER")
                        .requestMatchers(HttpMethod.GET, "/ratings").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/ratings/**").hasAnyRole("ADMIN")
                        // Trip
                        .requestMatchers(HttpMethod.POST, "/trips").hasAnyRole("COMPANY")
                        .requestMatchers(HttpMethod.GET, "/trips/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/trips").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/trips/**").hasAnyRole("COMPANY")
                )
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .build();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        SecretKey key = Keys.hmacShaKeyFor(extendKey(SECRET_KEY));
        return NimbusJwtDecoder.withSecretKey(key).build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        final JwtGrantedAuthoritiesConverter gac = new JwtGrantedAuthoritiesConverter();
        gac.setAuthoritiesClaimName("role");
        gac.setAuthorityPrefix("ROLE_");

        final JwtAuthenticationConverter jac = new JwtAuthenticationConverter();
        jac.setJwtGrantedAuthoritiesConverter(gac);
        return jac;
    }

    private byte[] extendKey(String key) {
        byte[] extendedKey = new byte[32];
        for (int i = 0; i < key.length(); i++) {
            extendedKey[i] = (byte) key.charAt(i);
        }
        return extendedKey;
    }
}
