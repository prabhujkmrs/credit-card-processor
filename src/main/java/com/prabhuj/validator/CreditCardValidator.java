package com.prabhuj.validator;


import com.prabhuj.domain.Card;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


public class CreditCardValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object object, Errors errors) {
            Card card = (Card) object;
           /* if(card.getName().equals("")){
                errors.rejectValue("name", "invalid.name.value");
            }*/
            if(validateCreditCardNumber(String.valueOf(card.getCardNumber()))){
               errors.rejectValue("cardNumber","invalid.card.cardNumber");
            }
            if(String.valueOf(card.getCardNumber()).length() >= 19){
                errors.rejectValue("cardNumber","invalid.card.length");
            }
    }

    private boolean validateCreditCardNumber(String cardNo) {

        int nDigits = cardNo.length();

        int nSum = 0;
        boolean isSecond = false;
        for (int i = nDigits - 1; i >= 0; i--)
        {

            int d = cardNo.charAt(i) - '0';

            if (isSecond)
                d = d * 2;

            nSum += d / 10;
            nSum += d % 10;

            isSecond = !isSecond;
        }
        return (nSum % 10 == 0);
    }
}
