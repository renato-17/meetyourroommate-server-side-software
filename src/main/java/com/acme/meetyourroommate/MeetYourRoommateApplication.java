package com.acme.meetyourroommate;

import com.acme.meetyourroommate.security.JWTAuthorizationFilter;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableJpaRepositories
@SpringBootApplication
@EnableJpaAuditing
public class MeetYourRoommateApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeetYourRoommateApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

//    @EnableWebSecurity
//    @Configuration
//    class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            http.cors().and()
//                    .csrf().disable()
//                    .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
//                    .authorizeRequests()
//                    .antMatchers(HttpMethod.POST, "/users/**").permitAll()
//                    .antMatchers("/v2/api-docs",
//                            "/configuration/ui",
//                            "/swagger-resources/**",
//                            "/configuration/security",
//                            "/swagger-ui.html",
//                            "/webjars/**").permitAll()
//                    .antMatchers("/api/**").authenticated();
//        }
//
//    }
}
