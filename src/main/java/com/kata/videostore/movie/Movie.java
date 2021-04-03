package com.kata.videostore.movie;

public abstract class Movie {

  private final String title;

  public Movie(String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }

  public static Movie newRelease(String title) {
    return new NewReleaseMovie(title);
  }

  public static Movie children(String title) {
    return new ChildrenMovie(title);
  }

  public static Movie regular(String title) {
    return new RegularMovie(title);
  }

  public static Movie promo(String title) {
    return new PromoMovie(title);
  }

  public abstract double getAmount(int daysRented);

  public abstract int getFrequentRenterPoints(int daysRented);
}
