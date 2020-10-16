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
        checkLength(password, failures);
        checkCase(password, failures);
        checkCombination(password, failures);
        checkSequenceRepetition(password, failures);
        return failures;
    }
    
    
	// \w= [a-zA-Z]
    private Pattern SequenceRepetition = Pattern.compile("(\\w{1,})\\1");
    
    private Pattern CasePattern = Pattern.compile("[A-Z]");
    
    private Pattern Combination = Pattern.compile("[a-z]+\\d+|\\d+[a-z]+");
    
    //check 5< password length<12
    private void checkLength(String string, Set<String> failures) {
        if (string.length() < 5 || string.length() > 12) {
            failures.add(ERROR_LENGTH);
        }
    }

    //check password only contains lowercase
    private void checkCase(String password, Set<String> failures) {
        Matcher matcher = CasePattern.matcher(password);
        if (matcher.find()) {
            failures.add(ERROR_CASE);
        }

    }

    //check password contains lowercase letters and numerical digits
    private void checkCombination(String password, Set<String> failures) {
        Matcher matcher = Combination.matcher(password);
        if (!matcher.find()) {
            failures.add(ERROR_COMBINATION);
        }

    }
    
    //check password not contains any sequence of characters immediately followed by the same sequence
    private void checkSequenceRepetition(String password, Set<String> failures) {
        Matcher matcher = SequenceRepetition.matcher(password);
        if (matcher.find()) {
            failures.add(ERROR_WITH_REPEATITION);
        }
    }

}
