package Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import PasswordValidationService.PasswordValidationApplication;
import PasswordValidationService.ValidateService;

@RestController
public class Controller {
	

	@RequestMapping("/password")
	public void user() {
		PasswordValidationApplication pv = new PasswordValidationApplication();
		String password = pv.getPassword();
		ValidateService validate = new ValidateService();
		validate.doValidate(password);
	}

}
