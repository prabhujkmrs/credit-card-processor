package com.prabhuj.controller;

import com.prabhuj.domain.Card;
import com.prabhuj.service.CardProcessor;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.View;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class CreditCardControllerTest {

    @Mock
    private CardProcessor cardProcessor;

    @InjectMocks
    private CreditCardController creditCardController;

    @Mock
    private Model model;

    @Mock
    private BindingResult errors;

    private MockMvc mockMvc;

    @Mock
    private View mockView;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(creditCardController).setSingleView(mockView).build();

    }

    @Test
    public void getAllCardsTest(){
        when(cardProcessor.getAllCards()).thenReturn(cardList());
        String result = creditCardController.getAllCards(model);
        assertEquals(result, "card-view");
    }

    @Test
    public void addCardTest_Success(){
        when(errors.hasErrors()).thenReturn(false);
        String result = creditCardController.addCard(cardDTO(),errors);
        assertEquals(result, "redirect:/cards");
    }

    @Test
    public void addCardTest_Failure(){
        when(errors.hasErrors()).thenReturn(true);
        String result = creditCardController.addCard(cardDTO(),errors);
        assertEquals(result, "card-view");
    }

    @Test
    public void testGetAllCards() throws Exception {
        List cardList = cardList();
        when(cardProcessor.getAllCards()).thenReturn(cardList);
        mockMvc.perform(get("/cards"))
                .andExpect(model().attribute("cards", cardList))
                .andExpect(view().name("card-view"));
    }

    @Test
    public void testAddCard() throws Exception {
        when(errors.hasErrors()).thenReturn(true);
        mockMvc.perform(post("/cards")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(model().attributeExists("card"))
                .andExpect(view().name("card-view"));
    }


    private List<Card> cardList(){
        return getCards();
    }

    private Card cardDTO(){
        Card cObj = new Card();
        cObj.setName("NAME");
        cObj.setLimit(2000);
        cObj.setBalance(0.0);
        cObj.setCardNumber(new BigInteger("1234567890"));
        return cObj;
    }

    public static List<Card> getCards() {
        List<Card> obj = new ArrayList<Card>();
        Card cObj = new Card();
        cObj.setName("NAME");
        cObj.setLimit(2000);
        cObj.setBalance(0.0);
        cObj.setCardNumber(new BigInteger("1234567890"));
        obj.add(cObj);
        return obj;
    }
}
