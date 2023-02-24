package com.example.postgres.springbootpostgresdocker.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MoneyUtil {

  /**
   *
   */

  public static String format(double value) {
    return format(value, "$");
  }

  public static String format(double value, String symbol) throws IllegalArgumentException{
    
    if(symbol == null) throw new IllegalArgumentException();

    //move minus sign to the left
    if (value < 0) {
      symbol = "-" + symbol;
      value = -value;
    }

    BigDecimal rounded = BigDecimal
      .valueOf(value)
      .setScale(2, RoundingMode.HALF_UP);
    return symbol + rounded;
  }
}
