package com.epam.exercises.module4.demo.domain.outcome;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Outcome {

  private List<OutcomeOdd> outcomeOdds = new ArrayList<>();
  private String outcomeValue;

  public void addOutcomeOdd(OutcomeOdd outcomeOddForFirstOutcome) {
    outcomeOdds.add(outcomeOddForFirstOutcome);
  }
}
