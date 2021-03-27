package com.kata.videostore;

import java.util.ArrayList;
import java.util.List;

public class Customer {

  private String name;
  private List<Rental> rentals = new ArrayList<>();

  public Customer(String name) {
    this.name = name;
  }

  public void addRental(Rental rental) {
    rentals.add(rental);
  }

  public String getName() {
    return name;
  }

  public String statement() {
    String result = header(getName());
    result += rentalLines();
    result += footer(getTotalAmount(), getFrequentRenterPoints());
    return result;
  }

  private double getTotalAmount() {
    return rentals.stream().mapToDouble(Rental::getAmount).sum();
  }

  private String rentalLines() {
    String result = "";
    for (Rental rental : rentals) {
      result += rentalLine(rental.getTitle(), rental.getAmount());
    }
    return result;
  }

  private int getFrequentRenterPoints() {
    int frequentRenterPoints = 0;
    for (Rental rental : rentals) {
      frequentRenterPoints += getFrequentRenterPoints(rental);
    }
    return frequentRenterPoints;
  }

  private int getFrequentRenterPoints(Rental rental) {
    int frequentRenterPoints =1;
    if (rental.getMovie().getPriceCode() == Movie.NEW_RELEASE
        && rental.getDaysRented() > 1) {
      frequentRenterPoints++;
    }
    return frequentRenterPoints;
  }

  private String rentalLine(String title, double amount) {
    return String.format("\t%s\t%s\n", title, amount);
  }

  private String footer(double totalAmount, int frequentRenterPoints) {
    return String.format("You owed %s\nYou earned %d frequent renter points\n", totalAmount, frequentRenterPoints);
  }

  private String header(String name) {
    return String.format("Rental Record for %s\n", name);
  }


}
