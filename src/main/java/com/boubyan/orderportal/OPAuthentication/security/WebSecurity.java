//package user.admin.ws.security;
//
//import java.util.Arrays;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//import user.admin.ws.service.UserService;
//
//@EnableWebSecurity
//public class WebSecurity extends WebSecurityConfigurerAdapter{
//	
//	private final UserService userDetailsService;
//	private final BCryptPasswordEncoder bCryptPasswordEncoder;
//	
//	public WebSecurity(UserService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
//		this.userDetailsService = userDetailsService;
//		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//	}
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		  http
//	        .cors().and()
//	        .csrf().disable()
//	        .authorizeRequests()
//	        	.antMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_URL).permitAll()
//	        	
//	        .anyRequest().authenticated()
//	        .and().addFilter(getAuthentionFilter())
//	        .addFilter(new AuthorizationFilter(authenticationManager()))
//	        .sessionManagement()
//	        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//	}
//	
//	@Override
//	public void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
//	
//	}
//
//
//	//custom login URL
//	protected AuthenticationFilter getAuthentionFilter() throws Exception {
//		final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager());
//		filter.setFilterProcessesUrl("/users/login");
//		return filter;
//	}
//	
//	
//	   @Bean
//	    public CorsConfigurationSource corsConfigurationSource()
//	    {
//	    	final CorsConfiguration configuration = new CorsConfiguration();
//	    	   
//	    	configuration.setAllowedOrigins(Arrays.asList("*"));
//	    	configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE","OPTIONS"));
//	    	configuration.setAllowCredentials(true);
//	    	configuration.setAllowedHeaders(Arrays.asList("*"));
//	    	
//	    	final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//	    	source.registerCorsConfiguration("/**", configuration);
//	    	
//	    	return source;
//	    }
//	
//}
