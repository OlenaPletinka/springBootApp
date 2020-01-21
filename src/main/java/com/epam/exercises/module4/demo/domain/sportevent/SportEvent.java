package com.epam.exercises.module4.demo.domain.sportevent;

import com.epam.exercises.module4.demo.domain.bet.Bet;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix="sportEvent")
@Data
public abstract class SportEvent {

  private Result result;
  private List<Bet> bets;

  private String title;
  private LocalDate startDate;
  private LocalDate endDate;
}
