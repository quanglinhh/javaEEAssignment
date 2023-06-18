package com.example.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true,jsr250Enabled = true)
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        UserDetails user2 = User.withUsername("user2").password("{noop}123456").roles("USER").build();
        UserDetails user3 = User.withUsername("user3").password("{noop}123456").roles("ADMIN").build();
        return new InMemoryUserDetailsManager(user2,user3);
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
//        httpSecurity.csrf()
//                .disable()
//                .authorizeRequests()
//                .antMatchers("/login/**").permitAll()
//                .antMatchers("/css/*").permitAll()
//                .antMatchers("/user/*").hasRole("USER")
//                .antMatchers("/admin/*").hasRole("ADMIN")
//                .antMatchers(HttpMethod.DELETE).hasAnyRole("ADMIN")
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .defaultSuccessUrl("/user/hello")
//                .failureUrl("/login")
//                .successForwardUrl("/user/hello")
//                .failureForwardUrl("/login?error=true")
//                .and()
//                .logout().permitAll()
//                .and()
//                .exceptionHandling().accessDeniedPage("/access-denied");
//        return httpSecurity.build();
        httpSecurity.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/login/**").permitAll()
                .antMatchers("/css/*").permitAll()
                .antMatchers("/user/*").hasRole("USER")
                .antMatchers("/admin/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE).hasAnyRole("ADMIN")
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/user/hello")
                .failureUrl("/login?error=true")
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/access-denied");

        return httpSecurity.build();
    }
}
