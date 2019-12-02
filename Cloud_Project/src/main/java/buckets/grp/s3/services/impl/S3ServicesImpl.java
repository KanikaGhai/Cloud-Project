package buckets.grp.s3.services.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.validation.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.iot.model.CannedAccessControlList;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.CollectionUtils;

import buckets.grp.s3.DTO.FileDeleteDTO;
import buckets.grp.s3.DTO.GenericFileResponse;
import buckets.grp.s3.DTO.UserFileInfoDTO;
import buckets.grp.s3.DTO.UserInfoDTO;
import buckets.grp.s3.DTO.UserLoginDTO;
import buckets.grp.s3.DTO.UserSignupDTO;
import buckets.grp.s3.dao.Dao;
import buckets.grp.s3.services.S3Services;



@Service
public class S3ServicesImpl implements S3Services {
	
	
	
	private Logger logger = LoggerFactory.getLogger(S3ServicesImpl.class);
	
	
	
	@Autowired
	Dao Dao;
	
	
	@Autowired
	private AmazonS3 s3client;
	

	@Value("${user.s3.bucket}")
	private String bucketName;
	
	
	private String filename;
	
	  
	
	
	  @Async
    public GenericFileResponse fileUpload(MultipartFile multipartFile, String FirstName, String LastName, String EmailId, 
    		String FileName, String FileDescription, boolean enablePublicReadAccess) throws IOException
    {
    	// Checking for file size
    			int maxSize = 10485760;
    			if (multipartFile.getSize() > maxSize) {
    				try {
						throw new ValidationException("File Size not Supported. Max Size- 10 MB");
					} catch (ValidationException e) {
						e.printStackTrace();
					}
    			}
        String newFileName = multipartFile.getOriginalFilename();
        
        GenericFileResponse response = null;

        try {
            
            File file = new File(newFileName);
             FileOutputStream fileoutput = new FileOutputStream(file);
            fileoutput.write(multipartFile.getBytes());
			fileoutput.close();
            
            
            String filekey = EmailId + "/" + newFileName;

            PutObjectRequest newFileUpload = new PutObjectRequest(bucketName, filekey, file);

            s3client.putObject(newFileUpload);
           
            file.delete();
            
            
            response = createObjectorUpdate(FirstName, LastName, EmailId, newFileName, FileDescription);
            
        } catch (AmazonServiceException ex) {
            logger.error("error [" + ex.getMessage() + "] occurred while uploading [" + newFileName + "] ");
        }
		return response;
    }
    
    private GenericFileResponse createObjectorUpdate(String FirstName, String LastName, String EmailId, String FileName, String FileDescription) {
    	
    	boolean fileMatch = false;
    	List<UserFileInfoDTO> userFiles = Dao.UserInformation(EmailId);
    	
    	if (!CollectionUtils.isNullOrEmpty(userFiles)) {
    		
    		for(UserFileInfoDTO userFileInfoDTO : userFiles)
        	{
        		fileMatch = userFileInfoDTO.getFileName().equalsIgnoreCase(FileName);
        	}
    		if (fileMatch) {
    			//updating the existing file
    			  Dao.existingFileUpdate(EmailId, FileName, FileDescription);
    		}
    		else {
    			//inserting new file for new user
    			   Dao.createNewFile(FirstName, LastName, EmailId, FileName, FileDescription);
    		}
    	}
    	else{
    		//inserting query for new user and file upload
    		Dao.createNewFile(FirstName, LastName, EmailId, FileName, FileDescription);
    	}
    	
		return new GenericFileResponse("SUCCESS");
    	
    }
	 

	
	
	@Override
	public GenericFileResponse signupNewUser(UserSignupDTO signUp)
	{
		Dao.newUser(signUp);
		GenericFileResponse response= new GenericFileResponse("SUCCESS");
		return response;
		
	}

	
	@Override
	public GenericFileResponse loginAdmin(UserLoginDTO adminLogin) throws ValidationException, javax.xml.bind.ValidationException
	{
		UserInfoDTO  userInfo = Dao.getDetailsForAdmin(adminLogin);
		GenericFileResponse response = null;
		if (null == userInfo) {
			throw new ValidationException("Authorized to Admin Only");
		}
		else { 
			response = new GenericFileResponse("Admin Login Successful");
		}
		return response;
		
	}
	
	
	
	@Override
	public UserInfoDTO loginUser(UserLoginDTO login) throws ValidationException, javax.xml.bind.ValidationException 
	{
		UserInfoDTO  userInfo = Dao.getDetailsForUser(login);
		GenericFileResponse response = null;
		
		if (null == userInfo) {
			throw new ValidationException("Invalid EmailId/Password");
		}
		else { 
			response = new GenericFileResponse("Successful Login");
		}
		return userInfo;
		
	}
	
	
	
	@Async
	public void deleteUserFile(FileDeleteDTO fileDelete)
	{ 
		String Filelocation = fileDelete.getEmailId() + "/" + fileDelete.getFileName();
		try {
			s3client.deleteObject(new DeleteObjectRequest(bucketName, Filelocation));
			Dao.deleteUserFile(fileDelete);
		} catch (AmazonServiceException ex) {
			logger.error("error [" + ex.getMessage() + "] occured ");
		}
	}

	
	
	@Override
	public List<UserFileInfoDTO> allFilesAdmin() 
	{
		List<UserFileInfoDTO>  adminfiles = Dao.AdminFiles();
		
		return adminfiles;
	}
	
	
	
	
	
	@Override
	public List<UserFileInfoDTO> particularUserFiles(String emailId) 
	{
		List<UserFileInfoDTO>  userFileInfoDTO = Dao.UserInformation(emailId) ;
		
		return userFileInfoDTO;
	}
	
	
	
	
	}
	
	
