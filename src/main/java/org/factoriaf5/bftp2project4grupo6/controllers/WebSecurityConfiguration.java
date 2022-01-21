package org.factoriaf5.bftp2project4grupo6.controllers;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/games/", "/login", "/css/**", "/img/**", "/data/**/**").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().permitAll()
                .and().logout().permitAll();
        http.formLogin().defaultSuccessUrl("/", true);

    }
}
