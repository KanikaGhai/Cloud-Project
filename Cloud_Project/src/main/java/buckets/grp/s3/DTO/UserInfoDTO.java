package buckets.grp.s3.DTO;
import java.io.Serializable;


public class UserInfoDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	String FirstName;
	String LastName;
	String EmailId;
	
	public UserInfoDTO() {	
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
}