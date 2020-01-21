package com.epam.exercises.module4.demo;

import com.epam.exercises.module4.demo.service.GameProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("application.properties")
@PropertySource("messages-eng.properties")
public class DemoApplication {

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);
    GameProcessor gameProcessor = context.getBean(GameProcessor.class);

    gameProcessor.processGame();
  }
}
