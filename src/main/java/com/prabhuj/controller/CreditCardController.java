package com.prabhuj.controller;

import com.prabhuj.domain.Card;
import com.prabhuj.service.CardProcessor;
import com.prabhuj.validator.CreditCardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CreditCardController {


    private CardProcessor cardProcessor;

    @Autowired
    public CreditCardController(CardProcessor cardProcessor) {
        this.cardProcessor = cardProcessor;
    }

    @PostMapping("/card")
    public String addCard(@ModelAttribute("card") @Valid Card card, BindingResult errors){
        CreditCardValidator creditCardValidator = new CreditCardValidator();
        creditCardValidator.validate(card,errors);
        if (!errors.hasErrors()) {
            cardProcessor.add(card);
        }
        else{
            return "card-view";
        }
        return "redirect:/card";
    }

    @GetMapping("/card")
    public String getAllCards(Model model){
        model.addAttribute("card", new Card());
        model.addAttribute("cards", cardProcessor.getAllCards());
        return "card-view";
    }
}
