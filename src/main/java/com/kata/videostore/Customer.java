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

    double totalAmount = 0;
    int frequentRenterPoints = 0;

    for (Rental rental : rentals) {
      double thisAmount = getAmount(rental);

      frequentRenterPoints++;

      if (rental.getMovie().getPriceCode() == Movie.NEW_RELEASE
          && rental.getDaysRented() > 1) {
        frequentRenterPoints++;
      }

      result += rentalLine(rental.getMovie().getTitle(), thisAmount);
      totalAmount += thisAmount;

    }

    result += footer(totalAmount, frequentRenterPoints);

    return result;
  }

  private String rentalLine(String title, double amount) {
    return String.format("\t%s\t%s\n", title, amount);
  }

  private double getAmount(Rental rental) {
    double thisAmount = 0;
    // determines the amount for each line
    switch (rental.getMovie().getPriceCode()) {
      case Movie.REGULAR:
        thisAmount += 2;
        if (rental.getDaysRented() > 2) {
          thisAmount += (rental.getDaysRented() - 2) * 1.5;
        }
        break;
      case Movie.NEW_RELEASE:
        thisAmount += rental.getDaysRented() * 3;
        break;
      case Movie.CHILDRENS:
        thisAmount += 1.5;
        if (rental.getDaysRented() > 3) {
          thisAmount += (rental.getDaysRented() - 3) * 1.5;
        }
        break;
    }
    return thisAmount;
  }

  private String footer(double totalAmount, int frequentRenterPoints) {
    return String.format("You owed %s\nYou earned %d frequent renter points\n", totalAmount, frequentRenterPoints);
  }

  private String header(String name) {
    return String.format("Rental Record for %s\n", name);
  }


}
