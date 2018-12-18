package com.wep.iftube.seguranca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	 @Autowired
	    JwtTokenProvider jwtTokenProvider;

	    @Bean
	    @Override
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	    }

	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        //@formatter:off
	        http
	            .httpBasic().disable()
	            .csrf().disable()
	            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	            .and()
	                .authorizeRequests()
	                .antMatchers(HttpMethod.POST, "/canal").permitAll()
	                .antMatchers("/login").permitAll()
//	                .antMatchers(HttpMethod.GET, "/vehicles/**").permitAll()
//	                .antMatchers(HttpMethod.DELETE, "/vehicles/**").hasRole("ADMIN")
//	                .antMatchers(HttpMethod.GET, "/v1/vehicles/**").permitAll()
	                .anyRequest().permitAll()
	            .and()
	            .apply(new JwtConfigurer(jwtTokenProvider));
	        //@formatter:on
	    }

	
}
