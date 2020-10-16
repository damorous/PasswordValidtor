package PasswordValidationService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

	@Bean
    public ValidationRule ValidationRule() {
        return new ValidationRule();
    }
	
	@Bean
    public ValidateService validate() {
        return new ValidateService();
    }
	
}
