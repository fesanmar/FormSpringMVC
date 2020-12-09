package com.felipesantacruz.spring.seguridad;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth
			.inMemoryAuthentication()
			.passwordEncoder(NoOpPasswordEncoder.getInstance())
			.withUser("admin")
			.password("admin")
			.roles("ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http
			.authorizeRequests()
				.antMatchers("/webjars/**", "/css/**", "/", "/h2-console/**").permitAll() // h2 console is open to anyone, very bad idea for production
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
			.logout()
				.permitAll();
		// disble csrf and frame options so we can work with h2-console
		http.csrf().disable();
		http.headers().frameOptions().disable();
	}
	
	
	
}
