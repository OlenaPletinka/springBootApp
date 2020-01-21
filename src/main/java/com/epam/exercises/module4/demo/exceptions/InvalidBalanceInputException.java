package com.epam.exercises.module4.demo.exceptions;

public class InvalidBalanceInputException extends RuntimeException {

  public InvalidBalanceInputException(String message) {
    super(message);
  }
}
