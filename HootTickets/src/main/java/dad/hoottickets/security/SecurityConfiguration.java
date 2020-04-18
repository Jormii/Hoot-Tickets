package dad.hoottickets.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import dad.hoottickets.database.User;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserRepositoryAuthenticationProvider authenticationProvider;

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		setUpPublicURLs(http);
		setUpRegisteredUsersURLs(http);
		setUpSellersURLs(http);
		
		// Login y logout
		http.formLogin().loginPage("/loginUser");
		http.formLogin().usernameParameter("username");
		http.formLogin().passwordParameter("password");
		http.formLogin().defaultSuccessUrl("/loginUser/success");
		http.formLogin().failureUrl("/loginUser");

		http.logout().logoutUrl("/logoutUser");
		http.logout().logoutSuccessUrl("/");
	}

	private void setUpPublicURLs(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/").permitAll();
		http.authorizeRequests().antMatchers("/event").permitAll();
		http.authorizeRequests().antMatchers("/event/tickets").permitAll();
		http.authorizeRequests().antMatchers("/checkout").permitAll();
		http.authorizeRequests().antMatchers("/checkout/success").permitAll();
		http.authorizeRequests().antMatchers("/loginUser").permitAll();
		http.authorizeRequests().antMatchers("/loginUser/success").permitAll();
		http.authorizeRequests().antMatchers("/logoutUser").permitAll();
		http.authorizeRequests().antMatchers("/logoutUser/success").permitAll();
		http.authorizeRequests().antMatchers("/registerUser").permitAll();
		http.authorizeRequests().antMatchers("/registerUser/sendData").permitAll();
	}
	private void setUpSellersURLs(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/eventCreation").access("hasRole('ROLE_SELLER')");
		http.authorizeRequests().antMatchers("/eventCreation/sendEventData").access("hasRole('ROLE_SELLER')");
		http.authorizeRequests().antMatchers("/eventCreation/showings").access("hasRole('ROLE_SELLER')");
		http.authorizeRequests().antMatchers("/eventCreation/createShowing").access("hasRole('ROLE_SELLER')");
		http.authorizeRequests().antMatchers("/eventCreation/sendShowingData").access("hasRole('ROLE_SELLER')");
		http.authorizeRequests().antMatchers("/eventCreation/createTicket").access("hasRole('ROLE_SELLER')");
		http.authorizeRequests().antMatchers("/eventCreation/sendTicketData").access("hasRole('ROLE_SELLER')");
		http.authorizeRequests().antMatchers("/eventCreation/checkIfValid").access("hasRole('ROLE_SELLER')");
		http.authorizeRequests().antMatchers("/eventCreation/completed").access("hasRole('ROLE_SELLER')");
		http.authorizeRequests().antMatchers("/eventCreation/failed").access("hasRole('ROLE_SELLER')");
	}
	private void setUpRegisteredUsersURLs(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/user/myTickets").access("hasRole('ROLE_USER')");
		http.authorizeRequests().antMatchers("/user/refundTicket").access("hasRole('ROLE_USER')");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider);
	}


}
