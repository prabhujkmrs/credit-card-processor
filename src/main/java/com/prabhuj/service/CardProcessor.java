package com.prabhuj.service;

import com.prabhuj.domain.Card;
import java.util.List;


public interface CardProcessor {

    List getAllCards();
    void add(Card card);
}
