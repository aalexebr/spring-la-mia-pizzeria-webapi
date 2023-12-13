package org.java.spring.auth.conf;

import org.java.spring.auth.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AuthConfiguration {
	
	@Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.csrf().disable()
			.cors().disable()
			.authorizeHttpRequests()
			.requestMatchers("/ingredients").hasAnyAuthority("USER","ADMIN")
	        .requestMatchers("pizza/create").hasAnyAuthority("ADMIN")
	        .requestMatchers("/pizza/edit/**").hasAnyAuthority("ADMIN")
	        .requestMatchers("/pizza/delete/**").hasAnyAuthority("ADMIN")
	        .requestMatchers("/pizza/*/special-offer").hasAnyAuthority("ADMIN")
	        .requestMatchers("pizza/update-special-offer/**").hasAnyAuthority("ADMIN")
	        .requestMatchers("/create-ingredient").hasAnyAuthority("ADMIN")
	        .requestMatchers("/ingredient/delete/**").hasAnyAuthority("ADMIN")
			.requestMatchers("/api/**").permitAll()
	        .requestMatchers("/**").permitAll()
	        .and().formLogin()
	        .and().logout()
	        ;
	    
		
		return http.build();
	}

    @Bean
    UserDetailsService userDetailsService() {
	    return new UserService();
	}
    
    @Bean
	public static PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
    }
    
    @Bean
    DaoAuthenticationProvider authenticationProvider() {
      
    	DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
   
    	authProvider.setUserDetailsService(userDetailsService());
    	authProvider.setPasswordEncoder(passwordEncoder());
   
    	return authProvider;
    }
    

}
