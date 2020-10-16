package PasswordValidationService;

import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication

public class PasswordValidationApplication {

	private static String password;
	
	public String getPassword() {
		return password;
	}
	
	
	public static void main(String[] args) {

		SpringApplication.run(PasswordValidationApplication.class, args);
		password = args[0];
	}

}
