package com.prabhuj.data;

import com.prabhuj.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface HSQLDBClient extends JpaRepository<Card,String> {
}
