package dad.hoottickets.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import dad.hoottickets.database.User;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	// TODO: Deshacerse de esto
	public static final boolean PERMIT_ALL = true;

	@Autowired
	private UserRepositoryAuthenticationProvider authenticationProvider;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider);

		// auth.inMemoryAuthentication().withUser("user").password("password").roles(User.DEFAULT_USER_ROLE);
		// auth.inMemoryAuthentication().withUser("user").password("password").roles(User.DEFAULT_USER_ROLE,
		// User.SELLER_ROLE);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		if (PERMIT_ALL) {
			http.authorizeRequests().anyRequest().permitAll();
		} else {
			setUpPublicURLs(http);
			setUpRegisteredUsersURLs(http);
			setUpSellersURLs(http);
		}

		// Login y logout
		http.formLogin().loginPage("/loginUser");
		http.formLogin().usernameParameter("username");
		http.formLogin().passwordParameter("password");
		http.formLogin().defaultSuccessUrl("/");
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
		http.authorizeRequests().antMatchers("/loginUser/sendData").permitAll();
		http.authorizeRequests().antMatchers("/logoutUser").permitAll();
		http.authorizeRequests().antMatchers("/registerUser").permitAll();
		http.authorizeRequests().antMatchers("/registerUser/sendData").permitAll();
	}

	private void setUpRegisteredUsersURLs(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/user/myTickets").hasAnyRole(User.DEFAULT_USER_ROLE);
		http.authorizeRequests().antMatchers("/user/refundTicket").hasAnyRole(User.DEFAULT_USER_ROLE);
	}

	private void setUpSellersURLs(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/eventCreation").hasAnyRole(User.SELLER_ROLE);
		http.authorizeRequests().antMatchers("/eventCreation/sendEventData").hasAnyRole(User.SELLER_ROLE);
		http.authorizeRequests().antMatchers("/eventCreation/showings").hasAnyRole(User.SELLER_ROLE);
		http.authorizeRequests().antMatchers("/eventCreation/createShowing").hasAnyRole(User.SELLER_ROLE);
		http.authorizeRequests().antMatchers("/eventCreation/sendShowingData").hasAnyRole(User.SELLER_ROLE);
		http.authorizeRequests().antMatchers("/eventCreation/createTicket").hasAnyRole(User.SELLER_ROLE);
		http.authorizeRequests().antMatchers("/eventCreation/sendTicketData").hasAnyRole(User.SELLER_ROLE);
		http.authorizeRequests().antMatchers("/eventCreation/checkIfValid").hasAnyRole(User.SELLER_ROLE);
		http.authorizeRequests().antMatchers("/eventCreation/completed").hasAnyRole(User.SELLER_ROLE);
		http.authorizeRequests().antMatchers("/eventCreation/failed").hasAnyRole(User.SELLER_ROLE);
	}

}
