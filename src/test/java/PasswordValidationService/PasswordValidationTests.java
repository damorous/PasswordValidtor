package PasswordValidationService;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import Config.AppConfig;

import static PasswordValidationService.ValidationRule.ERROR_CASE;
import static PasswordValidationService.ValidationRule.ERROR_COMBINATION;
import static PasswordValidationService.ValidationRule.ERROR_LENGTH;
import static PasswordValidationService.ValidationRule.ERROR_WITH_REPEATITION;
import static org.hamcrest.CoreMatchers.hasItem;
//import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PasswordValidationTests {

    private ValidationRule validationRule;
    private String password;
    
    
    @BeforeEach
    public void getServiceFromIOC() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        validationRule = ctx.getBean(ValidationRule.class);
    }
    
    @Test
    public void testInputFromArguments() {
    	password=System.getProperty("password");
    	Set<String> result = validationRule.validate(password);
    	assertThat(result, hasItem(ERROR_LENGTH));
    }

    @Test
    public void testContainsUppercase() {
        Set<String> result = validationRule.validate("Abcde");
        assertThat(result, hasItem(ERROR_CASE));
    }
    
    @Test
    public void testContainsOnlyLowercaseButNoDigit() {
        Set<String> result = validationRule.validate("abcde");
        assertFalse(result.contains(ERROR_CASE));
        assertThat(result, hasItem(ERROR_COMBINATION));
    }

    @Test
    public void testContainsBothLetterAndDigit() {
        // act
        Set<String> result = validationRule.validate("a0");
        assertFalse(result.contains(ERROR_COMBINATION));
        assertThat(result, hasItem(ERROR_LENGTH));
    }

    @Test
    public void testContainsBothDigitAndLetter() {
        // act
        Set<String> result = validationRule.validate("0a");
        assertFalse(result.contains(ERROR_COMBINATION));
        assertThat(result, hasItem(ERROR_LENGTH));
    }

    @Test
    public void testContainsOnlyLetters() {
        // act
        Set<String> result = validationRule.validate("a");
        assertThat(result, hasItem(ERROR_COMBINATION));
        assertThat(result, hasItem(ERROR_LENGTH));
    }

    @Test
    public void testContainsOnlyDigits() {
        Set<String> result = validationRule.validate("0");
        assertThat(result, hasItem(ERROR_COMBINATION));
    }

    @Test
    public void testSizeLength5() {
        Set<String> result = validationRule.validate("12345");
        assertFalse(result.contains(ERROR_LENGTH));
    }

    @Test
    public void testLengthLessThan5() {
        Set<String> result = validationRule.validate("1234");
        assertThat(result, hasItem(ERROR_COMBINATION));
    }

    @Test
    public void testLength12() {
        Set<String> result = validationRule.validate("123456789abc");
        assertFalse(result.contains(ERROR_LENGTH));
    }

    @Test
    public void testLengthMoreThan12() {
        Set<String> result = validationRule.validate("123456789abcd");
        assertThat(result, hasItem(ERROR_LENGTH));
    }

    @Test
    public void testNotRepeated() {
        Set<String> result = validationRule.validate("abcde12345");
        assertFalse(result.contains(ERROR_WITH_REPEATITION));
    }

    @Test
    public void testRepeated() {
        Set<String> result = validationRule.validate("abab");
        assertThat(result, hasItem(ERROR_WITH_REPEATITION));
    }

    @Test
    public void testRepeated2() {
        Set<String> result = validationRule.validate("aa");
        assertThat(result, hasItem(ERROR_WITH_REPEATITION));
    }

    @Test
    public void testRepeatPettern() {
        Set<String> result = validationRule.validate("ab1ab1");
        assertThat(result, hasItem(ERROR_WITH_REPEATITION));
    }

    @Test
    public void testRepeatPettern2() {
        Set<String> result = validationRule.validate("prefixabab");
        assertThat(result, hasItem(ERROR_WITH_REPEATITION));
    }

    @Test
    public void testRepeatPettern3() {
        Set<String> result = validationRule.validate("ababpostfix");
        assertThat(result, hasItem(ERROR_WITH_REPEATITION));
    }

}
