package com.prabhuj.service;

import com.prabhuj.data.HSQLDBClient;
import com.prabhuj.domain.Card;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigInteger;
import java.util.List;

import static com.prabhuj.controller.CreditCardControllerTest.getCards;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class CardProcessorTest {

    @Mock
    private HSQLDBClient hsqldbClient;

    @InjectMocks
    private CardProcessorImpl cardProcessor;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllCardsTest(){
        when(hsqldbClient.findAll()).thenReturn(cardList());
        List result = cardProcessor.getAllCards();
        assertEquals(1,result.size());
        verify(hsqldbClient, times(1)).findAll();
    }

    @Test
    public void addNewCardTest(){
        Card card = new Card();
        card.setName("NAME");
        card.setLimit(2000);
        card.setBalance(0.0);
        card.setCardNumber(new BigInteger("1234567890"));
        cardProcessor.add(card);
        verify(hsqldbClient, times(1)).save(card);
    }

    private List<Card> cardList(){
        return getCards();
    }
}
