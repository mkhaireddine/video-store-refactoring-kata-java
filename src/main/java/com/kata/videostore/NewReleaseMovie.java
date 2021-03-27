package com.kata.videostore;

public class NewReleaseMovie extends Movie {

  public NewReleaseMovie(String title) {
    super(title, NEW_RELEASE);
  }

  @Override
  double getAmount(int daysRented) {
    return daysRented * 3;
  }

  @Override
  int getFrequentRenterPoints(int daysRented) {
    if (daysRented > 1) {
      return 2;
    }
    return 1;
  }
}
