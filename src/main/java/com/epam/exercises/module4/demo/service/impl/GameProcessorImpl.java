package com.epam.exercises.module4.demo.service.impl;

import com.epam.exercises.module4.demo.domain.user.Player;
import com.epam.exercises.module4.demo.enums.Currency;
import com.epam.exercises.module4.demo.service.DataPreparationService;
import com.epam.exercises.module4.demo.service.EventLoger;
import com.epam.exercises.module4.demo.service.GameProcessor;
import com.epam.exercises.module4.demo.ui.OutputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class GameProcessorImpl implements GameProcessor {
  @Autowired
  private EventLoger eventLoger;
  @Autowired
  private DataPreparationService dataPreparationService;
  @Autowired
  private OutputService outputService;

  @Override
  public void processGame() {
    try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
      String language = outputService.getLanguage(bufferedReader);

      String name = outputService.getPlayerName(bufferedReader, language);

      String accountNumber = outputService.getAccountNumber(bufferedReader, language);

      String balanceInput = outputService.getBalanceInput(bufferedReader, language);

      String currencyInput = outputService.getCurrencyInput(bufferedReader, language);
      Currency currency = Currency.valueOf(currencyInput);

      String birthInput = outputService.getBirthInput(bufferedReader, language);
      LocalDate birth = LocalDate.parse(birthInput);

      BigDecimal balance = new BigDecimal(balanceInput);
      Player player = dataPreparationService
                .createPlayer(name, accountNumber, balance, currency, birth);

      eventLoger.logEvent(
                "Welcome " + player.getName() + "!\n" + "Your balance is "
                          + player.getBalance() + " " + player.getCurrency());
      outputService.makeBet(bufferedReader, player, language);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
