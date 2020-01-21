package com.epam.exercises.module4.demo.enums;

public enum YesNo {
  YES("Y"), NO("N");
  private String value;

  YesNo(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
