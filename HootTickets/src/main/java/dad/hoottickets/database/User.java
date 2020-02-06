package dad.hoottickets.database;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected String userUsername;

	@Column(unique = true, nullable = false)
	protected String userEmail;

	@Column(nullable = false)
	protected String userName;

	@Column(nullable = false)
	protected String userSurname;

	@Column(nullable = false)
	protected String userPassword;

	// TODO: Produce error
	/*
	 * @ManyToMany(mappedBy = "ticketID") private List<Ticket> userTickets;
	 */

	public User() {

	}

	public User(String userUsername, String userEmail, String userName, String userSurname, String userPassword) {
		this.userUsername = userUsername;
		this.userEmail = userEmail;
		this.userName = userName;
		this.userSurname = userSurname;
		this.userPassword = userPassword;
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

}
