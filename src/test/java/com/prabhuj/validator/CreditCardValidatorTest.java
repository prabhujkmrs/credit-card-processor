package com.prabhuj.validator;

import com.prabhuj.domain.Card;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.math.BigInteger;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class CreditCardValidatorTest {

    @InjectMocks
    private CreditCardValidator creditCardValidator;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void validateCardTest_field_has_error(){
        Object object = cardObject_1();
        Errors errors = new BeanPropertyBindingResult(cardObject_1(),"card");
        creditCardValidator.validate(object,errors);
        assertTrue(errors.hasFieldErrors());
    }

    @Test
    public void validateCardTest_field_Has_No_error(){
        Object object = cardObject_2();
        Errors errors = new BeanPropertyBindingResult(cardObject_2(),"card");
        creditCardValidator.validate(object,errors);
        assertFalse(errors.hasFieldErrors());
    }

    private Card cardObject_1(){
        Card cObj = new Card();
        cObj.setName("NAMEs");
        cObj.setLimit(5000);
        cObj.setBalance(0.0);
        cObj.setCardNumber(new BigInteger("1234567894074578458458458945"));
        return cObj;
    }
    private Card cardObject_2(){
        Card cObj = new Card();
        cObj.setName("NAME");
        cObj.setLimit(5000);
        cObj.setBalance(0.0);
        cObj.setCardNumber(new BigInteger("12345678940"));
        return cObj;
    }
}
