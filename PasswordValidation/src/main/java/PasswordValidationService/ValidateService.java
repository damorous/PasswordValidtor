package PasswordValidationService;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class ValidateService {

    private ValidationRule ValidationRule;

    @Autowired
    public void setPasswordService(ValidationRule validationRule) {
        this.ValidationRule = validationRule;
    }



    public Set<String> validatePassword(String password) {
        return ValidationRule.validate(password);
    }
	


	public void doValidate(String password) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        ValidateService validate = ctx.getBean(ValidateService.class);
        
        Set<String> errors = validate.validatePassword(password);
        for (String errorMesg : errors) {
            System.out.println(errorMesg);
        }
        if (errors.isEmpty()) {
        	System.out.println("Pass the validation");
        }
	}
	
}
