package com.epam.exercises.module4.demo.exceptions;

public class InvalidAccountNumberException extends RuntimeException {

  public InvalidAccountNumberException(String message) {
    super(message);
  }
}

