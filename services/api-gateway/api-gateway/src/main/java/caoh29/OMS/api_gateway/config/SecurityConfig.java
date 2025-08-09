package caoh29.OMS.api_gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
                .build();
//            .csrf().disable() // Disable CSRF for simplicity, consider enabling in production
//            .authorizeRequests()
//                .antMatchers("/api/order/**").authenticated() // Secure order endpoints
//                .antMatchers("/api/product/**").permitAll() // Allow public access to product endpoints
//                .anyRequest().authenticated() // Secure all other requests
//            .and()
//            .httpBasic(); // Use basic authentication
    }
}
