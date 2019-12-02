package buckets.grp.s3.DTO;
import java.io.Serializable;

public class UserFileInfoDTO implements Serializable {
	
	String FirstName;
	String LastName;
	String EmailId;
	String FileName;
	String FileDescription;
	String CreatedTime;
	String UpdatedTime;

	
	public UserFileInfoDTO() {

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


	public String getFileName() {
		return FileName;
	}


	public void setFileName(String fileName) {
		FileName = fileName;
	}



	public String getFileDescription() {
		return FileDescription;
	}


	public void setFileDescription(String fileDescription) {
		FileDescription = fileDescription;
	}


	public String getCreatedTime() {
		return CreatedTime;
	}


	public void setCreatedTime(String createdTime) {
		CreatedTime = createdTime;
	}


	public String getUpdatedTime() {
		return UpdatedTime;
	}


	public void setUpdatedTime(String updatedTime) {
		UpdatedTime = updatedTime;
	}

}