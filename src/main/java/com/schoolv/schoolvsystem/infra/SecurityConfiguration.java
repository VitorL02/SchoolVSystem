package com.schoolv.schoolvsystem.infra;


import com.schoolv.schoolvsystem.filter.SecurityFilter;
import io.swagger.v3.oas.models.PathItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private SecurityFilter securityFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http.csrf().disable()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        .and()
                    .authorizeRequests(authorizeRequests -> authorizeRequests.requestMatchers(HttpMethod.POST, "/users/login").permitAll()
                            .requestMatchers("/swagger-ui/**").permitAll()
                            .requestMatchers("/swagger-resources/**").permitAll()
                            .requestMatchers("/swagger-ui.html").permitAll()
                            .requestMatchers("/v3/**").permitAll()
                            .requestMatchers(HttpMethod.POST, "/users/register").permitAll()
                            .requestMatchers(HttpMethod.POST,"/users/recover_password_code").permitAll()
                            .requestMatchers(HttpMethod.POST,"/users/recover_password").permitAll()
                            .requestMatchers(HttpMethod.POST,"/users/register/staff").hasAnyRole("ADMIN")
                            .requestMatchers(HttpMethod.POST,"/users/register/professor").hasAnyRole("STAFF","ADMIN").anyRequest().authenticated()
                    )
                    .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}