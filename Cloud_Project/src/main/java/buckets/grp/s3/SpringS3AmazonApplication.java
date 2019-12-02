package buckets.grp.s3;
import org.springframework.boot.SpringApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import buckets.grp.s3.services.S3Services;

@SpringBootApplication
public class SpringS3AmazonApplication{

	
	@Autowired
	S3Services s3Services;
	
	
	public static void main(String[] args) {
		
		SpringApplication.run(SpringS3AmazonApplication.class, args);
	}

	
}
