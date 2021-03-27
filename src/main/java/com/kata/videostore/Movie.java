package com.kata.videostore;

public class Movie {

  public static final int CHILDRENS = 2;
  public static final int REGULAR = 0;
  public static final int NEW_RELEASE = 1;

  private String title;
  private int priceCode;

  public Movie(String title, int priceCode) {
    this.title = title;
    this.priceCode = priceCode;
  }

  static Movie newRelease(String title) {
    return new Movie(title, NEW_RELEASE);
  }

  static Movie children(String title) {
    return new Movie(title, CHILDRENS);
  }

  static Movie regular(String title) {
    return new Movie(title, REGULAR);
  }

  public int getPriceCode() {
    return priceCode;
  }

  public void setPriceCode(int code) {
    priceCode = code;
  }

  public String getTitle() {
    return title;
  }

  double getAmount(int daysRented) {
    double thisAmount = 0;
    // determines the amount for each line
    switch (priceCode) {
      case REGULAR:
        thisAmount += 2;
        if (daysRented > 2) {
          thisAmount += (daysRented - 2) * 1.5;
        }
        break;
      case NEW_RELEASE:
        thisAmount += daysRented * 3;
        break;
      case CHILDRENS:
        thisAmount += 1.5;
        if (daysRented > 3) {
          thisAmount += (daysRented - 3) * 1.5;
        }
        break;
    }
    return thisAmount;
  }

  int getFrequentRenterPoints(int daysRented) {
    var hasBonus = priceCode == NEW_RELEASE && daysRented > 1;
    if (hasBonus) {
      return 2;
    }
    return 1;
  }
}
