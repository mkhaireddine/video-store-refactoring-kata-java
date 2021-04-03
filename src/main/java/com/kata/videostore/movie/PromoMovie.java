package com.kata.videostore.movie;

public class PromoMovie extends Movie {

  public PromoMovie(String title) {
    super(title);
  }

  @Override
  public double getAmount(int daysRented) {
    return daysRented * 0.5;
  }

  @Override
  public int getFrequentRenterPoints(int daysRented) {
    return 0;
  }
}
