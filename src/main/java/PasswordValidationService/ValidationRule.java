package PasswordValidationService;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationRule {
	
	
    public static final String ERROR_LENGTH = "Password must be between 5 and 12 characters long.";
    public static final String ERROR_CASE = "Password must only contain lowercase letters.";
    public static final String ERROR_COMBINATION = "Password must only lowercase letters and numerical digits.";
    public static final String ERROR_WITH_REPEATITION = "Password must not contain any sequence of characters immediately followed by the same sequence.";
	
    
    public Set<String> validate(String password) {
        Set<String> failures = new HashSet<String>();
    	// \w= [a-zA-Z]
        Pattern SequenceRepetition = Pattern.compile("(\\w{1,})\\1");
        Pattern CasePattern = Pattern.compile("[A-Z]");
        Pattern Combination = Pattern.compile("[a-z]+\\d+|\\d+[a-z]+");
       
        if (password.length() < 5 || password.length() > 12) {
        	failures.add(ERROR_LENGTH);
        }
        
        if(checker(password, CasePattern)) {
        	failures.add(ERROR_CASE);
        }
        
        if(!checker(password, Combination)) {
        	failures.add(ERROR_COMBINATION);
        }
        
        if(checker(password, SequenceRepetition)) {
        	failures.add(ERROR_WITH_REPEATITION);
        }
        
        
        
        return failures;
    }
    
    private boolean checker(String password, Pattern pattern) {
    	Matcher matcher = pattern.matcher(password);
    	if (matcher.find()) {
    		return true;
    	}
    	return false;
    	
    }

}
