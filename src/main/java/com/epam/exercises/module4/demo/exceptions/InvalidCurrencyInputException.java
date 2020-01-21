package com.epam.exercises.module4.demo.exceptions;

public class InvalidCurrencyInputException extends RuntimeException {

  public InvalidCurrencyInputException(String message) {
    super(message);
  }
}
