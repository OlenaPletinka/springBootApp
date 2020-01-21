package com.epam.exercises.module4.demo.service.impl;

import com.epam.exercises.module4.demo.domain.bet.Bet;
import com.epam.exercises.module4.demo.domain.outcome.Outcome;
import com.epam.exercises.module4.demo.domain.outcome.OutcomeOdd;
import com.epam.exercises.module4.demo.domain.sportevent.FootballSportEvent;
import com.epam.exercises.module4.demo.domain.sportevent.SportEvent;
import com.epam.exercises.module4.demo.domain.user.Player;
import com.epam.exercises.module4.demo.enums.Currency;
import com.epam.exercises.module4.demo.service.DataPreparationService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Service
public class DataPreparationServiceImpl implements DataPreparationService {

  @Override
  public OutcomeOdd prepareOutcomeOdd(Double value, Date fromdate, Date toDate) {
    OutcomeOdd outcomeOddForFirstOutcome = new OutcomeOdd();
    outcomeOddForFirstOutcome.setValue(value);
    outcomeOddForFirstOutcome.setFromDate(fromdate);
    outcomeOddForFirstOutcome.setToDate(toDate);
    return outcomeOddForFirstOutcome;
  }

  @Override
  public Outcome prepareOutcome(String value, OutcomeOdd outcomeOdd) {
    Outcome outcome = new Outcome();
    outcome.setOutcomeValue(value);
    outcome.addOutcomeOdd(outcomeOdd);
    return outcome;
  }

  @Override
  public Bet prepareBet(SportEvent sportEvent, String betType, Outcome outcome) {
    Bet bet = new Bet();
    bet.setSportEvent(sportEvent);
    bet.setBetType(betType);
    bet.addOutcome(outcome);
    return bet;
  }

  @Override
  public FootballSportEvent prepareFootballSportEvent(String title) {
    FootballSportEvent footballSportEvent = new FootballSportEvent();
    footballSportEvent.setTitle(title);
    return footballSportEvent;
  }

  @Override
  public Player createPlayer(String name, String accountNumber, BigDecimal balance,
                             Currency currency, LocalDate birth) {

    return Player.builder().name(name).accountNumber(accountNumber).balance(balance)
      .currency(currency).birth(birth).build();
  }

}
