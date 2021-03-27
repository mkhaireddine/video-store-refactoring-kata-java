package com.kata.videostore;

public class Rental {

  public Rental(Movie movie, int daysRented) {
    this.movie = movie;
    this.daysRented = daysRented;
  }

  public int getDaysRented() {
    return daysRented;
  }

  public Movie getMovie() {
    return movie;
  }

  private Movie movie;
  private int daysRented;

  String getTitle() {
    return getMovie().getTitle();
  }

  double getAmount() {
    return getAmount(daysRented);
  }

  double getAmount(int daysRented) {
    double thisAmount = 0;
    // determines the amount for each line
    switch (getMovie().getPriceCode()) {
      case Movie.REGULAR:
        thisAmount += 2;
        if (daysRented > 2) {
          thisAmount += (daysRented - 2) * 1.5;
        }
        break;
      case Movie.NEW_RELEASE:
        thisAmount += daysRented * 3;
        break;
      case Movie.CHILDRENS:
        thisAmount += 1.5;
        if (daysRented > 3) {
          thisAmount += (daysRented - 3) * 1.5;
        }
        break;
    }
    return thisAmount;
  }

  int getFrequentRenterPoints() {
    int frequentRenterPoints =1;
    if (getMovie().getPriceCode() == Movie.NEW_RELEASE
        && daysRented > 1) {
      frequentRenterPoints++;
    }
    return frequentRenterPoints;
  }
}
