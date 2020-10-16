package PasswordValidationService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	

	@RequestMapping("/")
	public void user() {
		PasswordValidationApplication pv = new PasswordValidationApplication();
		String password = pv.getPassword();
		ValidateService validate = new ValidateService();
		validate.doValidate(password);
	}

}
