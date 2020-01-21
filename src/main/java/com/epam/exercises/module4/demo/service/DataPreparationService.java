package com.epam.exercises.module4.demo.service;

import com.epam.exercises.module4.demo.domain.bet.Bet;
import com.epam.exercises.module4.demo.domain.outcome.Outcome;
import com.epam.exercises.module4.demo.domain.outcome.OutcomeOdd;
import com.epam.exercises.module4.demo.domain.sportevent.FootballSportEvent;
import com.epam.exercises.module4.demo.domain.sportevent.SportEvent;
import com.epam.exercises.module4.demo.domain.user.Player;
import com.epam.exercises.module4.demo.enums.Currency;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public interface DataPreparationService {

  OutcomeOdd prepareOutcomeOdd(Double value, Date fromdate, Date toDate);

  Outcome prepareOutcome(String value, OutcomeOdd outcomeOdd);

  Bet prepareBet(SportEvent sportEvent, String betType, Outcome outcome);

  FootballSportEvent prepareFootballSportEvent(String title);

  Player createPlayer(String name, String accountNumber, BigDecimal balance, Currency currency,
                      LocalDate birth);
}
