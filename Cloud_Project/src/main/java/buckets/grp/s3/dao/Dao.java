package buckets.grp.s3.dao;


import java.util.List;

import javax.xml.bind.ValidationException;

import buckets.grp.s3.DTO.FileDeleteDTO;
import buckets.grp.s3.DTO.UserFileInfoDTO;
import buckets.grp.s3.DTO.UserInfoDTO;
import buckets.grp.s3.DTO.UserLoginDTO;
import buckets.grp.s3.DTO.UserSignupDTO;


public interface Dao {
	
	public void newUser(UserSignupDTO signUp);
	
	public void createNewFile(String FirstName, String LastName, String EmailId, String FileName, String FileDescription);
	
	public void existingFileUpdate(String EmailId, String FileName, String FileDescription);
	
	public void deleteUserFile(FileDeleteDTO fileDelete);
	
	//public void deleteFile(DeleteFileRequest deleteRequest);
	
	public UserInfoDTO getDetailsForUser(UserLoginDTO login) throws ValidationException;
	
	public UserInfoDTO getDetailsForAdmin(UserLoginDTO adminLogin) throws ValidationException;
	
	public List<UserFileInfoDTO> UserInformation(String EmailId);
	
	public List<UserFileInfoDTO> AdminFiles();
}
