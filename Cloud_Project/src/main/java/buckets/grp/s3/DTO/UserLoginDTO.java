package buckets.grp.s3.DTO;
import java.io.Serializable;

public class UserLoginDTO implements Serializable{
	
	String EmailId;
	String Password;
	
	public UserLoginDTO() {
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
