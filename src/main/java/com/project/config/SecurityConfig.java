package com.project.config;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
@Log
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**",
                "/fonts/**",
                "/img/**",
                "/js/**",
                "/plugins/**",
                "/sample/**",
                "/admins/**",
                "/dist/**",
                "/front/**",
                "/files/**"
        );
    }


    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.headers().defaultsDisabled()
                .addHeaderWriter(new StaticHeadersWriter("Cache-Control", " no-cache,max-age=0, must-revalidate"))
                .addHeaderWriter(new StaticHeadersWriter("Expires", "0"))
                .and()
                .exceptionHandling()
                .and()
                .formLogin()
                .loginProcessingUrl("/login")
                .loginPage("/login")
                .defaultSuccessUrl("/success", true)
                .failureUrl("/login?error=true")
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .deleteCookies("JSESSIONID")
                .permitAll()
                .and()
                .rememberMe().rememberMeParameter("remember").key("uniqueAndSecret").tokenValiditySeconds(1000 * 3600 * 24 * 30)
                .and()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers("/api/account/**").permitAll()
                .antMatchers("/admin/**", "/home/**", "/login/**", "/logout", "/api/contact","/api/account/create","/api/account/update").permitAll()
                .antMatchers("/api*/**").authenticated()
                .antMatchers("/admin/**/profile").authenticated()
                .antMatchers("/admin/**/update-pass").authenticated()
                .and()
                .csrf()
                .disable();
    }

    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
}
