package com.kata.videostore;

import com.kata.videostore.movie.Movie;

public class Rental {

  private Movie movie;
  private int daysRented;

  public Rental(Movie movie, int daysRented) {
    this.movie = movie;
    this.daysRented = daysRented;
  }

  public String getTitle() {
    return movie.getTitle();
  }

  public double getAmount() {
    return movie.getAmount(daysRented);
  }

  int getFrequentRenterPoints() {
    return movie.getFrequentRenterPoints(daysRented);
  }

}
