package dad.hoottickets.database;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User implements Serializable {

	private static final long serialVersionUID = 644781810055498872L;
	public static final String DEFAULT_USER_ROLE = "USER";
	public static final String SELLER_ROLE = "SELLER";

	@Id
	protected String userUsername;

	@Column(unique = true, nullable = false)
	protected String userEmail;

	@Column(nullable = false)
	protected String userName;

	@Column(nullable = false)
	protected String userSurname;

	@Column(nullable = false)
	protected String userPassword;

	@OneToMany(mappedBy = "ticketPurchaseUniqueID.user")
	protected List<TicketPurchase> userTickets = new ArrayList<>();

	@ElementCollection(fetch = FetchType.EAGER)
	protected List<String> roles = new ArrayList<>();

	public User() {

	}

	public User(String userUsername, String userEmail, String userName, String userSurname, String userPassword) {
		this.userUsername = userUsername;
		this.userEmail = userEmail;
		this.userName = userName;
		this.userSurname = userSurname;
		this.userPassword = userPassword;
		this.roles.add("ROLE_" + DEFAULT_USER_ROLE);
	}

	/*
	 * Getters and setters
	 */

	public String getUserUsername() {
		return userUsername;
	}

	public void setUserUsername(String userUsername) {
		this.userUsername = userUsername;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserSurname() {
		return userSurname;
	}

	public void setUserSurname(String userSurname) {
		this.userSurname = userSurname;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public List<TicketPurchase> getUserTickets() {
		return userTickets;
	}

	public void setUserTickets(List<TicketPurchase> userTickets) {
		this.userTickets = userTickets;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

}
