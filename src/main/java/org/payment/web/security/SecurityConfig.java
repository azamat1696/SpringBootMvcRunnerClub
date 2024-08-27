package org.payment.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private CustomUserDetailService userDetailService;
   @Autowired
    public SecurityConfig(CustomUserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @Bean
    public static PasswordEncoder passwordEncoder(){
       return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests( auth -> auth
                        .requestMatchers("/register").permitAll()
                        .requestMatchers("/register/save").permitAll()
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/logout").permitAll()
                        .requestMatchers("/css/**").permitAll()
                        .requestMatchers("/js/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form ->
                        form.defaultSuccessUrl("/clubs?success",true)
                                .loginPage("/login")
                                .failureUrl("/login?error=true")
                                .loginProcessingUrl("/login")
                                .permitAll()
                )
                .logout(config -> config.logoutSuccessUrl("/login")
                ).build();

    }

    public void configure(AuthenticationManagerBuilder builder) throws Exception {
       builder.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
    }

}
