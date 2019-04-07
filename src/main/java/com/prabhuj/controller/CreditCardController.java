package com.prabhuj.controller;

import com.prabhuj.domain.Card;
import com.prabhuj.service.CardProcessor;
import com.prabhuj.validator.CreditCardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class CreditCardController {


    private CardProcessor cardProcessor;

    @Autowired
    public CreditCardController(CardProcessor cardProcessor) {
        this.cardProcessor = cardProcessor;
    }

    /**
     *  Create a new credit card
     * @param card
     * @param errors
     */
    @PostMapping("/cards")
    public String addCard(@ModelAttribute("card") @Valid Card card, BindingResult errors){
        CreditCardValidator creditCardValidator = new CreditCardValidator();
        creditCardValidator.validate(card,errors);
        if (!errors.hasErrors()) {
            cardProcessor.add(card);
        }
        else{
            return "card-view";
        }
        return "redirect:/cards";
    }
    /**
     *  Return list of call cards in the system
     * @param model
     */
    @GetMapping("/cards")
    public String getAllCards(Model model){
        model.addAttribute("card", new Card());
        model.addAttribute("cards", cardProcessor.getAllCards());
        return "card-view";
    }
}
