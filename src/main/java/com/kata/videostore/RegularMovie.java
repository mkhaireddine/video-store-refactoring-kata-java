package com.kata.videostore;

public class RegularMovie extends Movie {

  public RegularMovie(String title) {
    super(title, REGULAR);
  }

  @Override
  double getAmount(int daysRented) {
    double thisAmount = 2;
    if (daysRented > 2) {
      thisAmount += (daysRented - 2) * 1.5;
    }
    return thisAmount;
  }

  @Override
  int getFrequentRenterPoints(int daysRented) {
    return 1;
  }
}
