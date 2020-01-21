package com.epam.exercises.module4.demo.service.impl;

import com.epam.exercises.module4.demo.domain.OutputBetObject;
import com.epam.exercises.module4.demo.domain.outcome.Outcome;
import com.epam.exercises.module4.demo.domain.outcome.OutcomeOdd;
import com.epam.exercises.module4.demo.domain.sportevent.FootballSportEvent;
import com.epam.exercises.module4.demo.domain.sportevent.SportEvent;
import com.epam.exercises.module4.demo.service.DataPreparationService;
import com.epam.exercises.module4.demo.service.DataProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DataProcessorServiceImpl implements DataProcessorService {

  @Value("#{new java.text.SimpleDateFormat(\"yyyyMMdd\").parse(\"${to-date}\")}")
  private Date toDate;
  @Value("#{new java.text.SimpleDateFormat(\"yyyyMMdd\").parse(\"${from-date}\")}")
  private Date fromDate;
  @Value("${sportEvent.name}")
  private String sportEventTitle;
  @Value("${outcome-player-value}")
  private String playerValue;
  @Value("${outcome-goal-value}")
  private String goalValue;
  @Value("${outcome-winner-value}")
  private String winnerValue;
  @Value("${outcome-odd-value-10-0}")
  private String outcomeOddValue10_0;
  @Value("${outcome-odd-value-1-3}")
  private String outcomeOddValue1_3;
  @Value("${outcome-odd-value-4-0}")
  private String outcomeOddValue4_0;
  @Value("${bet.bet-type-betting-for-goals}")
  private String bettingForGoals;
  @Value("${bet.bet-type-players-score}")
  private String playersScore;
  @Value("${bet.bet-type-winner}")
  private String winner;

  @Autowired
  private DataPreparationService dataPreparationService;

  @Override
  public List<OutputBetObject> createOutputBetObjects() {
    FootballSportEvent footballSportEvent = dataPreparationService
              .prepareFootballSportEvent(sportEventTitle);
    List<OutputBetObject> outputBetObjects = new ArrayList<>();
    outputBetObjects.add(
              createOutputBetObject(footballSportEvent, playerValue,
                        Double.parseDouble(outcomeOddValue10_0), playersScore));
    outputBetObjects.add(
              createOutputBetObject(footballSportEvent, goalValue, Double.parseDouble(outcomeOddValue1_3),
                        bettingForGoals));
    outputBetObjects.add(
              createOutputBetObject(footballSportEvent, winnerValue, Double.parseDouble(outcomeOddValue4_0),
                        winner));

    return outputBetObjects;
  }

  private OutputBetObject createOutputBetObject(SportEvent sportEvent, String outcomeValue,
                                                Double outcomeOddValue, String betType) {

    OutcomeOdd outcomeOdd = dataPreparationService
              .prepareOutcomeOdd(outcomeOddValue, fromDate, toDate);

    Outcome outcome = dataPreparationService.prepareOutcome(outcomeValue, outcomeOdd);

    outcomeOdd.setOutcome(outcome);

    dataPreparationService.prepareBet(sportEvent, betType, outcome);

    return new OutputBetObject(sportEvent.getTitle(), outcome, outcomeOdd, fromDate, toDate);
  }
}
