package Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import PasswordValidationService.ValidateService;
import PasswordValidationService.ValidationRule;

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
