package com.prabhuj.service;

import com.prabhuj.data.HSQLDBClient;
import com.prabhuj.domain.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardProcessorImpl implements CardProcessor {

    private HSQLDBClient hsqldbClient;

    @Autowired
    public CardProcessorImpl(HSQLDBClient hsqldbClient) {
        this.hsqldbClient = hsqldbClient;
    }

    @Override
    public List getAllCards() {
        return hsqldbClient.findAll();
    }

    @Override
    public void add(Card card) {
        hsqldbClient.save(card);
    }
}
