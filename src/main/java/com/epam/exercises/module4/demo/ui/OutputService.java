package com.epam.exercises.module4.demo.ui;

import com.epam.exercises.module4.demo.domain.OutputBetObject;
import com.epam.exercises.module4.demo.domain.outcome.Outcome;
import com.epam.exercises.module4.demo.domain.user.Player;
import com.epam.exercises.module4.demo.domain.wager.Wager;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public interface OutputService {

  List<OutputBetObject> getBetsOutput(String language);

  void getResultOutput(Outcome winOutcome, Wager wager, String language);

  void makeBet(BufferedReader bufferedReader, Player player, String language) throws IOException;

  String getPlayerName(BufferedReader bufferedReader, String language) throws IOException;

  String getAccountNumber(BufferedReader bufferedReader, String language) throws IOException;

  String getBalanceInput(BufferedReader bufferedReader, String language) throws IOException;

  String getCurrencyInput(BufferedReader bufferedReader, String language) throws IOException;

  String getBirthInput(BufferedReader bufferedReader, String language) throws IOException;

  String getLanguage(BufferedReader bufferedReader) throws IOException;
}
