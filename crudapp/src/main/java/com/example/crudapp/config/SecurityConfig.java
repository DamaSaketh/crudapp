// package com.example.crudapp.config;

// import com.example.crudapp.security.JwtFilter;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.AuthenticationProvider;
// import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
// import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


// @Configuration
// @EnableMethodSecurity
// public class SecurityConfig {

//     private final JwtFilter jwtFilter;
//     private final AuthenticationProvider authenticationProvider;

//     public SecurityConfig(JwtFilter jwtFilter, AuthenticationProvider authenticationProvider) {
//         this.jwtFilter = jwtFilter;
//         this.authenticationProvider = authenticationProvider;
//     }

//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         http
//             .csrf(csrf -> csrf.disable()) // disable CSRF
//             .authorizeHttpRequests(auth -> auth
//                 .requestMatchers("/auth/**").permitAll() // allow login/register
//                 .anyRequest().authenticated()           // secure all other endpoints
//             )
//             .authenticationProvider(authenticationProvider)
//             .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

//         return http.build();
//     }

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }

//     // Example AuthenticationProvider (can use your UserDetailsService)
//     @Bean
//     public AuthenticationProvider daoAuthenticationProvider(org.springframework.security.core.userdetails.UserDetailsService userDetailsService) {
//         DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//         provider.setUserDetailsService(userDetailsService);
//         provider.setPasswordEncoder(passwordEncoder());
//         return provider;
//     }
// }

// package com.example.crudapp.config;

// import com.example.crudapp.security.JwtFilter;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.context.annotation.Lazy;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
// import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;

// @SuppressWarnings("unused")
// @Configuration
// public class SecurityConfig {

//     private final JwtFilter jwtFilter;

//     public SecurityConfig(@Lazy JwtFilter jwtFilter) { // lazy breaks circular dependency
//         this.jwtFilter = jwtFilter;
//     }

//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         http.csrf(csrf -> csrf.disable())
//         .headers(headers -> headers.frameOptions(frame -> frame.disable()))
//             .authorizeHttpRequests(auth -> auth
//                 .requestMatchers("/auth/**").permitAll() 
//                 .requestMatchers("/h2-console/**").permitAll()// allow login/register
//                 .anyRequest().authenticated()
//             )
//             .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

//         return http.build();
//     }

//     // Password encoder
//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }

//     // UserDetailsService example (replace with your custom one if needed)
//     @Bean
//     public UserDetailsService userDetailsService() {
//         UserDetails user = User.builder()
//                 .username("admin")
//                 .password(passwordEncoder().encode("admin123"))
//                 .roles("USER")
//                 .build();

//         return new InMemoryUserDetailsManager(user);
//     }

//     // AuthenticationManager bean for login authentication
//     @Bean
//     public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//         return config.getAuthenticationManager();
//     }
// }

package com.example.crudapp.config;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.crudapp.security.JwtFilter;

@SuppressWarnings("unused")
@Configuration
// @ConditionalOnProperty(
//     name = "app.security.enabled",
//     havingValue = "true",
//     matchIfMissing = true
// )
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(@Lazy JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

  
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .headers(headers -> headers.frameOptions(frame -> frame.disable()))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers("/h2-console/**").permitAll()
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

   

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    
}
    
