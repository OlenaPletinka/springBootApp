package com.epam.exercises.module4.demo.domain.wager;

import com.epam.exercises.module4.demo.domain.outcome.OutcomeOdd;
import com.epam.exercises.module4.demo.domain.user.Player;
import com.epam.exercises.module4.demo.enums.Currency;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Wager {
  private OutcomeOdd outcomeOdd;
  private Player player;
  private BigDecimal amount;
  private Currency currency;
  private Integer timestamp;
  private boolean isProcessed;
  private boolean isWin;

  /**
   * constructor for the wager.
   *
   * @param outcomeOdd  given argument.
   * @param player      given argument.
   * @param amount      given argument.
   * @param currency    given argument.
   * @param timestamp   given argument.
   * @param isProcessed given argument.
   * @param isWin       given argument.
   */
  public Wager(OutcomeOdd outcomeOdd, Player player, BigDecimal amount, Currency currency,
               Integer timestamp, boolean isProcessed, boolean isWin) {
    this.outcomeOdd = outcomeOdd;
    this.player = player;
    this.amount = amount;
    this.currency = currency;
    this.timestamp = timestamp;
    this.isProcessed = isProcessed;
    this.isWin = isWin;
  }
}
