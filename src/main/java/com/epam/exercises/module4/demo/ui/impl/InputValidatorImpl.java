package com.epam.exercises.module4.demo.ui.impl;

import com.epam.exercises.module4.demo.enums.Currency;
import com.epam.exercises.module4.demo.exceptions.*;
import com.epam.exercises.module4.demo.ui.InputValidator;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.epam.exercises.module4.demo.enums.Regex.*;

@Service
public class InputValidatorImpl implements InputValidator {

  /**
   * validate input for the name.
   * @param name given argument.
   * @throws InvalidNameInputException custom exception.
   */
  @Override
  public void validateName(String name){
    if (!name.matches(NAME_REGEX.getValue())) {
      throw new InvalidNameInputException(
                "Please, enter valid name. Name can contain only letters!");
    }
  }

  /**
   * validate input for the account number.
   * @param accountNumber given argument.
   */
  @Override
  public void validateAccountNumber(String accountNumber) {
    if (!accountNumber.matches(ACCOUNT_NUMBER_REGEX.getValue())) {
      throw new InvalidAccountNumberException(
                "Please, enter valid account number. Account number can contain only numbers!");
    }
  }

  /**
   * validate input for the balance.
   * @param balanceInput given argument.
   */
  @Override
  public void validateBalanceInput(String balanceInput) {
    if (!balanceInput.matches(BALANCE_INPUT_REGEX.getValue())) {
      throw new InvalidBalanceInputException("Please, enter valid balance!");
    }
  }

  /**
   * validate input for the currency.
   * @param currencyInput given argument.
   */
  @Override
  public void validateCurrencyInput(String currencyInput) {
    if (!currencyInput.equals(Currency.EUR.getValue()) && !currencyInput
              .equals(Currency.UAH.getValue()) && !currencyInput.equals(Currency.USD.getValue())) {
      throw new InvalidCurrencyInputException(
                "Please, enter valid currency value"
                          + ". Possible values of the currency are: UAH, EUR or USD!");
    }
  }

  /**
   * validate input fot birth.
   * @param birthInput given argument.
   */
  @Override
  public void validateBirthInput(String birthInput) {
    if (!birthInput.matches(BIRTH_INPUT_REGEX.getValue())) {
      throw new InvalidBirthInputException(
                "Please, enter valid birth value. Correct format of the birth date is YYYY-MM-DD!");
    }
  }

  /**
   * validate input for the first bet.
   * @param firstBetOn given argument.
   */
  @Override
  public void validateFirstBetOn(String firstBetOn) {
    if (!firstBetOn.matches(BET_ON_REGEX.getValue()) && !firstBetOn.equals("q")) {
      throw new InvalidBetInputException("Please, enter valid number or 'q' for quit");
    }
  }

  /**
   * validate bet.
   * @param balance given argument.
   * @param bet given argument.
   * @return isvalid.
   */
  @Override
  public boolean isValidBet(BigDecimal balance, BigDecimal bet) {
    return balance.compareTo(bet) >= 0;
  }
}
