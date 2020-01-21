package com.epam.exercises.module4.demo.domain.bet;

import com.epam.exercises.module4.demo.domain.outcome.Outcome;
import com.epam.exercises.module4.demo.domain.sportevent.SportEvent;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix="bet")
@Data
public class Bet {

  private SportEvent sportEvent;
  private List<Outcome> outcomes = new ArrayList<>();
  private String description;
  private String betType;

  public void addOutcome(Outcome outcome) {
    outcomes.add(outcome);
  }
}
