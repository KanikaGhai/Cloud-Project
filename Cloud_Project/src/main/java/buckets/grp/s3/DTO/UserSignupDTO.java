package buckets.grp.s3.DTO;
import java.io.Serializable;

public class UserSignupDTO implements Serializable {
	
	
	String FirstName;
	String LastName;
	String EmailId;
	String Password;
	
	public UserSignupDTO() {	
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getEmailId() {
		return EmailId;
	}

	public void setEmailId(String emailId) {
		EmailId = emailId;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

}
