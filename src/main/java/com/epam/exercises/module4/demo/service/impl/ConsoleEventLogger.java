package com.epam.exercises.module4.demo.service.impl;

import com.epam.exercises.module4.demo.service.EventLoger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ConsoleEventLogger implements EventLoger {
  private Logger logger = LoggerFactory.getLogger(ConsoleEventLogger.class);

  @Override
  public void logEvent(String massage) {
    logger.info(massage);
  }
}
