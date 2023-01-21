package com.nghianguyen.fitnesstracker.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.nghianguyen.fitnesstracker.service.UserServiceImpl;

/*
 * Configures what pages and resources are available in the configure() method that's 
 * overridden from WebSecurityConfigureAdapter class. Allows /registration and /login
 * along with static files.
 */
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
// We will create userService class in upcoming step
	
	@Value("${application.security.disabled:false}")
	private boolean securityDisabled;
	
   @Autowired
   private UserServiceImpl userService;

   @Override
   protected void configure(HttpSecurity http) throws Exception {
	   if (securityDisabled) {
	   
	   } else {
	       http
           .authorizeRequests()
               .antMatchers(
                       "/registration**",
                       "/js/**",
                       "/script/**",
                       "/css/**",
                       "/img/**",
                       "/webjars/**").permitAll()
               .anyRequest().authenticated()
           .and()
               .formLogin()
                   .loginPage("/login")
                       .permitAll()
           .and()
               .logout()
                   .invalidateHttpSession(true)
                   .clearAuthentication(true)
                   .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                   .logoutSuccessUrl("/login?logout")
           .permitAll();
   
		   http.sessionManagement()
		   		.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
		   
		   http.sessionManagement().maximumSessions(1).expiredUrl("/sessionExpired.html");
   
//   http.sessionManagement()
//       .invalidSessionUrl("/invalidSession.html");		   
	   }

   }

   /*
    * Instantiates the BCrypt encoder for the password.
    */
   @Bean
   public BCryptPasswordEncoder passwordEncoder(){
       return new BCryptPasswordEncoder();
   }

   /*
    * AuthenticationProvider that retrieves user details and the password encoder.
    */
   @Bean
   public DaoAuthenticationProvider authenticationProvider(){
       DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
       auth.setUserDetailsService(userService);
       auth.setPasswordEncoder(passwordEncoder());
       return auth;
   }

   /*
    * Sets the AuthenticationProvider for the AuthenticationManagerBuilder. Allows for easy
    * building for authentication.
    */
   @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.authenticationProvider(authenticationProvider());
   }
   
   /*
    * Adds a listener that gets notified when the session is destroyed.
    */
   @Bean
   public HttpSessionEventPublisher httpSessionEventPublisher() {
       return new HttpSessionEventPublisher();
   }

}