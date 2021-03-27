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
    return movie.getTitle();
  }

  double getAmount() {
    return movie.getAmount(daysRented);
  }

  int getFrequentRenterPoints() {
    return movie.getFrequentRenterPoints(daysRented);
  }

}
