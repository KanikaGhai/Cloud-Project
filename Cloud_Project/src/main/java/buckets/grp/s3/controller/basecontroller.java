package buckets.grp.s3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import buckets.grp.s3.DTO.FileDeleteDTO;
import buckets.grp.s3.DTO.GenericFileResponse;
import buckets.grp.s3.DTO.UserFileInfoDTO;
import buckets.grp.s3.DTO.UserFileListInfoDTO;
import buckets.grp.s3.DTO.UserInfoDTO;
import buckets.grp.s3.DTO.UserLoginDTO;
import buckets.grp.s3.DTO.UserSignupDTO;
import buckets.grp.s3.services.S3Services;

@RestController
public class basecontroller {
	
	private static final String String = null;
	
	@Autowired
	S3Services S3Services;
	
	@RequestMapping(value = "/signUp", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseEntity<GenericFileResponse> signupUser(@RequestBody UserSignupDTO signUp) {
		ResponseEntity<GenericFileResponse> responseEntity = null;
		try {
			GenericFileResponse response = this.S3Services.signupNewUser(signUp);
			response.setMessage("User Signup Successful");
			responseEntity = new ResponseEntity<GenericFileResponse>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			responseEntity = new ResponseEntity<GenericFileResponse>(new GenericFileResponse(e.getMessage()), HttpStatus.EXPECTATION_FAILED);
		}
		return responseEntity;
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseEntity<UserInfoDTO> loginUser(@RequestBody UserLoginDTO login) {
		ResponseEntity<UserInfoDTO> responseEntity = null;
		try {
			UserInfoDTO response = this.S3Services.loginUser(login);
			responseEntity = new ResponseEntity<UserInfoDTO>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			responseEntity = new ResponseEntity<UserInfoDTO>(new UserInfoDTO(), HttpStatus.EXPECTATION_FAILED);
		}
		return responseEntity;
	}
	
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseEntity<GenericFileResponse> userupload(
			@RequestParam(value = "file", required = true) MultipartFile multipartfile,
			@RequestParam(value = "FirstName", required = true) String FirstName,
			@RequestParam(value = "LastName", required = true) String LastName,
			@RequestParam(value = "EmailId", required = true) String EmailId,
			@RequestParam(value = "FileName", required = true) String FileName,
			@RequestParam(value = "FileDescription", required = true) String FileDescription) {
		
		    ResponseEntity<GenericFileResponse> responseEntity = null;
		
		try {
			GenericFileResponse response = this.S3Services.fileUpload(multipartfile, FirstName, LastName, EmailId, 
		    		 FileName, FileDescription, true);
			responseEntity = new ResponseEntity<GenericFileResponse>(response, HttpStatus.OK);
	} 
		catch (Exception e) 
		{
		e.printStackTrace();
		responseEntity = new ResponseEntity<GenericFileResponse>(new GenericFileResponse(),
				HttpStatus.EXPECTATION_FAILED);
	}
			
		
		return responseEntity;
	}

	
	
	@RequestMapping(value = "/adminlogin", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseEntity<GenericFileResponse> adminLogIn(@RequestBody UserLoginDTO adminLogin) {
		ResponseEntity<GenericFileResponse> responseEntity = null;
		try {
			GenericFileResponse response = this.S3Services.loginAdmin(adminLogin);
			responseEntity = new ResponseEntity<GenericFileResponse>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			responseEntity = new ResponseEntity<GenericFileResponse>(new GenericFileResponse(), HttpStatus.EXPECTATION_FAILED);
		}
		return responseEntity;
	}
	
	
	
	@RequestMapping(value = "/adminFiles", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<List<UserFileInfoDTO>> adminFiles() {
		ResponseEntity<List<UserFileInfoDTO>> responseEntity = null;
		try {
			List<UserFileInfoDTO> adminfiles = this.S3Services.allFilesAdmin();
			responseEntity = new ResponseEntity<List<UserFileInfoDTO>>(adminfiles, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			responseEntity = new ResponseEntity<List<UserFileInfoDTO>>(HttpStatus.EXPECTATION_FAILED);
		}
		return responseEntity;
	}
	
	
	
	
	@RequestMapping(value = "/userfiles", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<List<UserFileInfoDTO>> userFiles(@RequestParam String emailId) {
		ResponseEntity<List<UserFileInfoDTO>> responseEntity = null;
		try {
			List<UserFileInfoDTO> userfiles = this.S3Services.particularUserFiles(emailId);
			responseEntity = new ResponseEntity<List<UserFileInfoDTO>>(userfiles, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			responseEntity = new ResponseEntity<List<UserFileInfoDTO>>(HttpStatus.EXPECTATION_FAILED);
		}
		return responseEntity;
	}
	
	
	
	
	
	@RequestMapping(value = "/deleteFileAndEntry", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseEntity<GenericFileResponse> deleteFileandEntry(@RequestBody FileDeleteDTO fileDelete) {
		ResponseEntity<GenericFileResponse> responseEntity = null;
		try {
			S3Services.deleteUserFile(fileDelete);
			GenericFileResponse response = new GenericFileResponse();
			responseEntity = new ResponseEntity<GenericFileResponse>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			responseEntity = new ResponseEntity<GenericFileResponse>(new GenericFileResponse(e.getMessage()), HttpStatus.EXPECTATION_FAILED);
		}
		return responseEntity;
	}
	
	
	}

