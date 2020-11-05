//package com.boubyan.orderportal.OPAuthentication;
//
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
//	@Autowired
//	DataSource dataSource;
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
//		auth.jdbcAuthentication()
//		.dataSource(dataSource)
//		.usersByUsernameQuery("SELECT email, password,1 from users where email=?")
//		.authoritiesByUsernameQuery("select email,authority from users_roles where email=?")
//		;
//	}
//	
////	@Override
////	protected void configure(HttpSecurity http) throws Exception {
////		// TODO Auto-generated method stub
////		
////	
////	}
//	
//@Bean
//@Override
//protected UserDetailsService userDetailsService() {
//	// TODO Auto-generated method stub
//	UserDetails user = User.withDefaultPasswordEncoder().username("user").password("pass").roles("USER").build();
//	return new InMemoryUserDetailsManager(user);
//}
//	
//}
