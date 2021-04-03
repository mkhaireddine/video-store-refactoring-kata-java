package com.kata.videostore;

public class PromoMovie extends Movie {

  public PromoMovie(String title) {
    super(title);
  }

  @Override
  double getAmount(int daysRented) {
    return daysRented * 0.5;
  }

  @Override
  int getFrequentRenterPoints(int daysRented) {
    return 0;
  }
}
