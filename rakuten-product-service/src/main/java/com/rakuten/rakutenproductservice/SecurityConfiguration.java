package com.rakuten.rakutenproductservice;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
/**
 * Security configuration
 * 
 * 
 * @author adarshsumma
 *
 *
*/

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http
         .authorizeRequests()
            .antMatchers("/api/products/**").authenticated()
            .anyRequest().permitAll()
          .and()
          .httpBasic()
          .and()
          .csrf().disable();
		 http.headers().frameOptions().disable();
 }

}