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
		http.authorizeRequests().antMatchers("/eventCreation").hasRole(User.SELLER_ROLE);
		http.authorizeRequests().antMatchers("/eventCreation/sendEventData").hasRole(User.SELLER_ROLE);
		http.authorizeRequests().antMatchers("/eventCreation/showings").hasRole(User.SELLER_ROLE);
		http.authorizeRequests().antMatchers("/eventCreation/createShowing").hasRole(User.SELLER_ROLE);
		http.authorizeRequests().antMatchers("/eventCreation/sendShowingData").hasRole(User.SELLER_ROLE);
		http.authorizeRequests().antMatchers("/eventCreation/createTicket").hasRole(User.SELLER_ROLE);
		http.authorizeRequests().antMatchers("/eventCreation/sendTicketData").hasRole(User.SELLER_ROLE);
		http.authorizeRequests().antMatchers("/eventCreation/checkIfValid").hasRole(User.SELLER_ROLE);
		http.authorizeRequests().antMatchers("/eventCreation/completed").hasRole(User.SELLER_ROLE);
		http.authorizeRequests().antMatchers("/eventCreation/failed").hasRole(User.SELLER_ROLE);
	}
	
	private void setUpRegisteredUsersURLs(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/user/myTickets").hasRole(User.DEFAULT_USER_ROLE);
		http.authorizeRequests().antMatchers("/user/refundTicket").hasRole(User.DEFAULT_USER_ROLE);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider);
	}


}
