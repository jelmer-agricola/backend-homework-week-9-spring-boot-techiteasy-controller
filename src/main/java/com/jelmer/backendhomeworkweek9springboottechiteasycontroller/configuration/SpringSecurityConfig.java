package com.jelmer.backendhomeworkweek9springboottechiteasycontroller.configuration;



import com.jelmer.backendhomeworkweek9springboottechiteasycontroller.filter.JwtRequestFilter;
import com.jelmer.backendhomeworkweek9springboottechiteasycontroller.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;
    private final JwtRequestFilter jwtRequestFilter;

    public SpringSecurityConfig(CustomUserDetailsService customUserDetailsService, JwtRequestFilter jwtRequestFilter) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    // Authenticatie met customUserDetailsService en passwordEncoder
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }



    // PasswordEncoderBean. Deze kun je overal in je applicatie injecteren waar nodig.
    // Je kunt dit ook in een aparte configuratie klasse zetten.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Authorizatie met jwt
    @Bean
    protected SecurityFilterChain filter (HttpSecurity http) throws Exception {

        //JWT token authentication

//        PADEN NOG NALOPEN MET CONTROLLERS
        http
                .csrf().disable()
                .httpBasic().disable()
                .cors().and()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "/users").permitAll()
                .requestMatchers(HttpMethod.GET,"/users").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST,"/users/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE,  "/cimodules/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST,  "/cimodules/**").hasRole("ADMIN").requestMatchers(HttpMethod.DELETE,  "/rc").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST,  "/rc").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST,  "/wallbrackets").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST,  "/tv/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE,  "/tv/**").hasRole("ADMIN")
                .requestMatchers( "/cimodules/**", "/rc", "/wallbrackets","/tv/**").hasAnyRole("ADMIN", "USER")
                //paden toevoegen - per entiteit
                /*voeg de requestmatchers toe voor admin(post en delete) en user (overige)*/
                .requestMatchers("/authenticated").authenticated()
                .requestMatchers("/authenticate").permitAll()/*allen dit punt mag toegankelijk zijn voor niet ingelogde gebruikers*/
                .anyRequest().denyAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}