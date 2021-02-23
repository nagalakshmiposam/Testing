package com.nagalakshmi.democamel.util;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

public class PhNumValidatorTest {
    private static PhNumValidator validator;

    @BeforeAll
    static void setUp() {
        validator = new PhNumValidator();
        System.out.println("executing @BeforeAll method..");
    }
    @ParameterizedTest
    @CsvSource({
            "+911223213167,true",
            "+91212331,false"
    })
    public void itShouldValidatePhNum(String number, boolean isValid) {
        boolean checked = validator.test(number);
        Assertions.assertThat(checked).isEqualTo(isValid);
        System.out.println("executing validator method..");
//        boolean isValid = validator.test("+911223213167");
//        Assertions.assertThat(isValid).isEqualTo(true);
    }

    @AfterAll
    static void TearDown() {
        validator=null;
        System.out.println("executing @AfterAll method..");
    }

}
