package buckets.grp.s3.DTO;
import java.io.Serializable;

public class FileDeleteDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String EmailId;
	private String FileName;
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
	
}
	


