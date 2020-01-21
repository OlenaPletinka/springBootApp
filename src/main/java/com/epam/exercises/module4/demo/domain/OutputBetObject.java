package com.epam.exercises.module4.demo.domain;

import com.epam.exercises.module4.demo.domain.outcome.Outcome;
import com.epam.exercises.module4.demo.domain.outcome.OutcomeOdd;
import lombok.Data;

import java.util.Date;

@Data
public class OutputBetObject {

   private String sportEventTitle;
   private Outcome outcome;
   private String outcomeValue;
   private Double outcomeOddValue;
   private OutcomeOdd outcomeOdd;
   private Date fromDate;
   private Date toDate;

   public OutputBetObject(String sportEventTitle, Outcome outcome, OutcomeOdd outcomeOdd,
                          Date fromDate, Date toDate) {
      this.sportEventTitle = sportEventTitle;
      this.outcome = outcome;
      this.outcomeValue = outcome.getOutcomeValue();
      this.outcomeOdd = outcomeOdd;
      this.outcomeOddValue = outcomeOdd.getValue();
      this.fromDate = fromDate;
      this.toDate = toDate;
   }
}
