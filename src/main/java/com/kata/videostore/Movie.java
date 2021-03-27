package com.kata.videostore;

public abstract class Movie {

  public static final int CHILDRENS = 2;
  public static final int REGULAR = 0;
  public static final int NEW_RELEASE = 1;

  private String title;
  int priceCode;

  public Movie(String title, int priceCode) {
    this.title = title;
    this.priceCode = priceCode;
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

  public String getTitle() {
    return title;
  }

  abstract double getAmount(int daysRented);

  abstract int getFrequentRenterPoints(int daysRented);
}
