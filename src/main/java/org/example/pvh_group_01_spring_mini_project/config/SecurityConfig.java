package org.example.pvh_group_01_spring_mini_project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//     No Database
//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails user = User.builder()
//                .username("visa")
//                .password(passwordEncoder().encode("123"))
//                .roles("USER").build();
//        UserDetails admin = User.builder()
//                .username("piseth")
//                .password(passwordEncoder().encode("345"))
//                .roles("ADMIN").build();
//        return new InMemoryUserDetailsManager(user, admin);
//    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//        http.cors(Customizer.withDefaults())
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(requests ->
//                        requests.requestMatchers("/api/v1/admin").hasRole("ADMIN")
//                                .requestMatchers("/api/v1/user").hasAnyRole("USER","ADMIN")
//                                .requestMatchers("/v3/api-docs/**",
//                                        "/swagger-ui/**",
//                                        "/swagger-ui.html","/api/v1/authentication").permitAll()
//                                .anyRequest().authenticated())  // allow only already login
//                .httpBasic(Customizer.withDefaults());
//        return http.build();
//    }
}
