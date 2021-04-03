package com.kata.videostore;

public abstract class Movie {

  private String title;

  public Movie(String title) {
    this.title = title;
  }

  static Movie newRelease(String title) {
    return new NewReleaseMovie(title);
  }

  static Movie children(String title) {
    return new ChildrenMovie(title);
  }

  static Movie regular(String title) {
    return new RegularMovie(title);
  }

  public static Movie promo(String title) {
    return new PromoMovie(title);
  }

  public String getTitle() {
    return title;
  }

  abstract double getAmount(int daysRented);

  abstract int getFrequentRenterPoints(int daysRented);
}
