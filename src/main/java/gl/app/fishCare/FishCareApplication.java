package gl.app.fishCare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SpringBootApplication
public class FishCareApplication {

	public static void main(String[] args) {
		SpringApplication.run(FishCareApplication.class, args);
	}

	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		private final String[] AUTH_WHITELIST = {
				// -- swagger ui
				"/swagger-resources/**", "/swagger-ui.html", "/v2/api-docs", "/webjars/**", "/example/" };

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()
			.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
			.authorizeRequests().antMatchers(HttpMethod.POST, "/user/**").permitAll()
			.antMatchers(AUTH_WHITELIST).permitAll().antMatchers(HttpMethod.POST, "/example").permitAll()
			.antMatchers("/hatchery/**").permitAll().antMatchers("/sensor/**").permitAll()
			.antMatchers("/sensorValue/**").permitAll().antMatchers("/actuator/**").permitAll()
			.antMatchers("/notification/**").permitAll().anyRequest().authenticated();
		}

		//		@Override
		//		protected void configure(HttpSecurity http) throws Exception {
		//			http.csrf().disable()
		//			.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
		//			.authorizeRequests().antMatchers(HttpMethod.POST, "/user/**").permitAll()
		//			.antMatchers(AUTH_WHITELIST).permitAll().anyRequest().authenticated();
		//		}
	}

}
