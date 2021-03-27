package com.kata.videostore;

public class ChildrenMovie extends Movie {

  public ChildrenMovie(String title) {
    super(title, CHILDRENS);
  }

  @Override
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

  @Override
  int getFrequentRenterPoints(int daysRented) {
    var hasBonus = priceCode == NEW_RELEASE && daysRented > 1;
    if (hasBonus) {
      return 2;
    }
    return 1;
  }
}
