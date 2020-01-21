package com.epam.exercises.module4.demo.domain.outcome;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
@ConfigurationProperties(prefix="outcome-odd")
@Data
public class OutcomeOdd {

  private Outcome outcome;
  private Double value;
  private Date fromDate;
  private Date toDate;
}
