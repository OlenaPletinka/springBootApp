package com.epam.exercises.module4.demo.ui.impl;

import com.epam.exercises.module4.demo.domain.OutputBetObject;
import com.epam.exercises.module4.demo.domain.outcome.Outcome;
import com.epam.exercises.module4.demo.domain.sportevent.Result;
import com.epam.exercises.module4.demo.domain.user.Player;
import com.epam.exercises.module4.demo.domain.wager.Wager;
import com.epam.exercises.module4.demo.enums.Currency;
import com.epam.exercises.module4.demo.service.DataProcessorService;
import com.epam.exercises.module4.demo.service.EventLoger;
import com.epam.exercises.module4.demo.service.TranslationProcessor;
import com.epam.exercises.module4.demo.ui.OutputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class OutputServiceImpl implements OutputService {

  @Value("${chose-language}")
  private String chooseLanguage;
  private static List<Wager> wagers = new ArrayList<>();


  @Autowired
  private InputValidatorImpl inputValidator;
  @Autowired
  private DataProcessorService dataProcessorService;
  @Autowired
  private EventLoger eventLoger;
  @Autowired
  private TranslationProcessor translationProcessor;

  @Override
  public List<OutputBetObject> getBetsOutput(String language) {
    List<OutputBetObject> outputBetObjects = dataProcessorService.createOutputBetObjects();
    eventLoger.logEvent(translationProcessor.translate(language, "bet-outcome"));
    for (int i = 1; i <= outputBetObjects.size(); i++) {
      OutputBetObject outputBetObject = outputBetObjects.get(i - 1);
      eventLoger.logEvent(String.format(
                translationProcessor.translate(language, "bet-message"), i,
                outputBetObject.getSportEventTitle(), outputBetObject.getOutcomeValue(),
                outputBetObject.getOutcomeOddValue(), outputBetObject.getFromDate(),
                outputBetObject.getToDate()));
    }
    return outputBetObjects;
  }

  @Override
  public void getResultOutput(Outcome winOutcome, Wager wager, String language) {
    eventLoger.logEvent(translationProcessor.translate(language, "result"));
    eventLoger.logEvent(String
              .format(translationProcessor.translate(language, "winner-message"),
                        wager.getTimestamp(), wager.getOutcomeOdd().getValue(),
                        wager.getOutcomeOdd().getFromDate(), wager.getOutcomeOdd().getToDate()));
    eventLoger.logEvent(String.format(translationProcessor.translate(language,"you-won"),
              wager.getAmount().multiply(BigDecimal.valueOf(wager.getOutcomeOdd().getValue())),
              wager.getCurrency()));

  }

  @Override
  public void makeBet(BufferedReader bufferedReader, Player player, String language) {
    List<OutputBetObject> betsOutput = getBetsOutput(language);

    try {
     String firstBetOn = bufferedReader.readLine();

      inputValidator.validateFirstBetOn(firstBetOn);

      if (firstBetOn.equals("q")) {
        getResults(wagers, language);
      } else {
        int choice = Integer.parseInt(firstBetOn);
        eventLoger.logEvent(translationProcessor.translate(language,"bet-size"));
        BigDecimal firstBet = BigDecimal.valueOf(Long.parseLong(bufferedReader.readLine()));
        discardPlayer(player, firstBet, language);
        wagers.add(createWagger(player, firstBet, betsOutput.get(choice - 1), choice));
        makeBet(bufferedReader, player, language);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public String getPlayerName(BufferedReader bufferedReader, String language) throws IOException {
    eventLoger.logEvent(translationProcessor.translate(language,"what-your-name"));
    String name = bufferedReader.readLine();
    inputValidator.validateName(name);
    return name;
  }

  @Override
  public String getAccountNumber(BufferedReader bufferedReader, String language) throws IOException {
    eventLoger.logEvent(translationProcessor.translate(language,"account-number"));
    String accountNumber = bufferedReader.readLine();
    inputValidator.validateAccountNumber(accountNumber);
    return accountNumber;
  }

  @Override
  public String getBalanceInput(BufferedReader bufferedReader, String language) throws IOException {
    eventLoger.logEvent(translationProcessor.translate(language,"money-amount"));
    String balanceInput = bufferedReader.readLine();
    inputValidator.validateBalanceInput(balanceInput);
    return balanceInput;
  }

  @Override
  public String getCurrencyInput(BufferedReader bufferedReader, String language) throws IOException {
    eventLoger.logEvent(translationProcessor.translate(language,"what-currency"));
    String currencyInput = bufferedReader.readLine();
    inputValidator.validateCurrencyInput(currencyInput);
    return currencyInput;
  }

  @Override
  public String getBirthInput(BufferedReader bufferedReader, String language) throws IOException {
    eventLoger.logEvent(translationProcessor.translate(language,"birth-date"));
    String birthInput = bufferedReader.readLine();
    inputValidator.validateBirthInput(birthInput);
    return birthInput;
  }

  @Override
  public String getLanguage(BufferedReader bufferedReader) throws IOException {
    eventLoger.logEvent(chooseLanguage);
    return bufferedReader.readLine();
  }

  private void getResults(List<Wager> wagers, String language) {
    Random rand = new Random();
    Wager winWager = wagers.get(rand.nextInt(wagers.size()));
    wagers.forEach(wager -> wager.setProcessed(true));
    winWager.setWin(true);

    Result result = new Result();
    Outcome winOutcome = winWager.getOutcomeOdd().getOutcome();
    result.addOutcomes(winOutcome);

    getResultOutput(winOutcome, winWager, language);
  }

  private Wager createWagger(Player player, BigDecimal firstBet, OutputBetObject outputBetObject,
                             Integer choice) {

    return Wager.builder().player(player).amount(firstBet).currency(player.getCurrency())
              .timestamp(choice).outcomeOdd(outputBetObject.getOutcomeOdd()).build();
  }

  private void discardPlayer(Player player, BigDecimal bet, String language) {
    BigDecimal balance = player.getBalance();
    Currency currency = player.getCurrency();
    if (!inputValidator.isValidBet(balance, bet)) {
      eventLoger.logEvent(translationProcessor.translate(language,"not-enought-money") + balance
                + " " + currency);
    } else {
      BigDecimal currentBalance = balance.subtract(bet);
      player.setBalance(currentBalance);
      eventLoger.logEvent(translationProcessor.translate(language, "new-balance")
                + currentBalance + " " + currency);
    }
  }
}
