package com.epam.exercises.module4.demo.enums;

public enum Currency {
  EUR("EUR"), USD("USD"), UAH("UAH");
  private String value;

  Currency(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
