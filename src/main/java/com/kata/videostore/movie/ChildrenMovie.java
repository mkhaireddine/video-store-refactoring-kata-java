package com.kata.videostore.movie;

public class ChildrenMovie extends Movie {

  public ChildrenMovie(String title) {
    super(title);
  }

  @Override
  public double getAmount(int daysRented) {
    double thisAmount = 1.5;
    if (daysRented > 3) {
      thisAmount += (daysRented - 3) * 1.5;
    }
    return thisAmount;
  }

  @Override
  public int getFrequentRenterPoints(int daysRented) {
    return 1;
  }
}
