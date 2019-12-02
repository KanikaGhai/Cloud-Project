package buckets.grp.s3.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import buckets.grp.s3.dao.Dao;
import buckets.grp.s3.DTO.FileDeleteDTO;
import buckets.grp.s3.DTO.UserFileInfoDTO;
import buckets.grp.s3.DTO.UserInfoDTO;
import buckets.grp.s3.DTO.UserLoginDTO;
import buckets.grp.s3.DTO.UserSignupDTO;

@Repository("mysql")

public class Daoimpl implements Dao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void newUser(UserSignupDTO signUp) {
		
		final String sql = "INSERT INTO Customer_Info (First_Name, Last_Name, Email_Id, Password) VALUES (?, ?, ?, ?)";
		
		jdbcTemplate.update(sql, signUp.getFirstName(), signUp.getLastName(), 
				signUp.getEmailId(), signUp.getPassword());
	}

	@Override
	public UserInfoDTO getDetailsForUser(UserLoginDTO login) {
		
		String sql = "SELECT * FROM Customer_Info WHERE Email_Id = ? AND Password = ?";
		
		UserInfoDTO userInfo = (UserInfoDTO) jdbcTemplate.queryForObject(
				sql, new Object[] { login.getEmailId(), login.getPassword() }, 
				new BeanPropertyRowMapper(UserInfoDTO.class));
		return userInfo;
	}
	
	
	
	
	
	@Override
	public UserInfoDTO getDetailsForAdmin(UserLoginDTO adminLogin) {
		
		String sql = "SELECT * FROM Customer_Info WHERE Email_Id = ? AND Password = ?";
		
		UserInfoDTO userInfo = (UserInfoDTO) jdbcTemplate.queryForObject(
				sql, new Object[] { adminLogin.getEmailId(), adminLogin.getPassword() }, 
				new BeanPropertyRowMapper(UserInfoDTO.class));
		return userInfo;
	}
	
	
	
	
	@Override
	public void deleteUserFile(FileDeleteDTO fileDelete)
	{
		String sql = "DELETE FROM CustomerFileInfo WHERE Email_Id = ? AND File_Name = ?";
		jdbcTemplate.update(sql, fileDelete.getEmailId(), fileDelete.getFileName());
	}


		@Override
	public List<UserFileInfoDTO> UserInformation(String emailId) {
		
		String sql = "SELECT * FROM CustomerFileInfo where Email_Id = ?";
		
		List<UserFileInfoDTO> FilesInfo = new ArrayList<UserFileInfoDTO>();
		
		List<java.util.Map<String, Object>> result = jdbcTemplate.queryForList(sql, emailId);
		
		for(java.util.Map<String, Object> obj : result)
		{
			UserFileInfoDTO userFiles = new UserFileInfoDTO();
			userFiles.setFirstName((String)obj.get("First_Name"));
			userFiles.setLastName((String)obj.get("Last_Name"));
			userFiles.setEmailId((String)obj.get("Email_Id"));
			userFiles.setFileName((String)obj.get("File_Name"));
			userFiles.setFileDescription((String)obj.get("File_Description"));
			userFiles.setCreatedTime((String )obj.get("File_CreatedTime"));
			userFiles.setUpdatedTime((String)obj.get("File_UpdatedTime"));
			FilesInfo.add(userFiles);
		}
		
		return FilesInfo;
	}
		
		
		@Override
		public List<UserFileInfoDTO> AdminFiles() {
			
			String sql = "SELECT * FROM CustomerFileInfo";
			
			List<UserFileInfoDTO> FilesInfo = new ArrayList<UserFileInfoDTO>();
			
			List<java.util.Map<String, Object>> result = jdbcTemplate.queryForList(sql);
			
			for(java.util.Map<String, Object> obj : result)
			{
				UserFileInfoDTO userFiles = new UserFileInfoDTO();
				userFiles.setFirstName((String)obj.get("First_Name"));
				userFiles.setLastName((String)obj.get("Last_Name"));
				userFiles.setEmailId((String)obj.get("Email_Id"));
				userFiles.setFileName((String)obj.get("File_Name"));
				userFiles.setFileDescription((String)obj.get("File_Description"));
				userFiles.setCreatedTime((String)obj.get("File_CreatedTime"));
				userFiles.setUpdatedTime((String)obj.get("File_UpdatedTime"));
				FilesInfo.add(userFiles);
			}
			
			return FilesInfo;
		}
		
		
		
	
		
		@Override
		public void createNewFile(String FirstName, String LastName, String EmailId, String FileName, String FileDescription)
		{
			long fileCreatedTime = System.currentTimeMillis();
			
			String fileCreatedTimeString = String.valueOf(fileCreatedTime);
			
			String sql = "INSERT INTO CustomerFileInfo (First_Name, Last_Name, Email_Id, File_Name, File_Description, File_CreatedTime, File_UpdatedTime)" + " VALUES (?, ?, ?, ?, ?, ?, ?)";
			
			jdbcTemplate.update(sql, FirstName, LastName, EmailId, FileName, FileDescription, fileCreatedTimeString, fileCreatedTimeString); 
					
		}
		
		@Override
		public void existingFileUpdate(String EmailId, String FileName, String FileDescription)
		{
			long updatedTime = System.currentTimeMillis();
			
			String fileUpdatedTimeString = String.valueOf(updatedTime);
			
			String sql = "UPDATE CustomerFileInfo SET  File_Description = ?, File_UpldatedTime = ? WHERE Email_Id = ? AND File_Name = ?";
			
			jdbcTemplate.update(sql, FileDescription, fileUpdatedTimeString, EmailId, FileName ); 
					
		}
		
		
		
		
		

}


