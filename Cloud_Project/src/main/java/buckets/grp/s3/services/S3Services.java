package buckets.grp.s3.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.ValidationException;

import org.springframework.web.multipart.MultipartFile;

import buckets.grp.s3.DTO.FileDeleteDTO;
import buckets.grp.s3.DTO.GenericFileResponse;
import buckets.grp.s3.DTO.UserFileInfoDTO;
import buckets.grp.s3.DTO.UserFileListInfoDTO;
import buckets.grp.s3.DTO.UserInfoDTO;
import buckets.grp.s3.DTO.UserSignupDTO;
import buckets.grp.s3.DTO.UserLoginDTO;

public interface S3Services {
	
	
	public void deleteUserFile(FileDeleteDTO fileDelete);
	
	GenericFileResponse signupNewUser(UserSignupDTO signUp);
	
	GenericFileResponse loginAdmin(UserLoginDTO adminLogin) throws javax.validation.ValidationException, ValidationException;
	
	GenericFileResponse fileUpload(MultipartFile multipartFile, String firstName, String lastName, String emailId, 
    		String fileName, String fileDescription, boolean enablePublicReadAccess) throws FileNotFoundException, IOException;
	
	UserInfoDTO loginUser(UserLoginDTO login) throws ValidationException;
	
	
	List<UserFileInfoDTO> particularUserFiles(String emailId); 
	
	public List<UserFileInfoDTO> allFilesAdmin();
}
