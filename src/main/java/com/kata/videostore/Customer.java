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
    double totalAmount = 0;
    int frequentRenterPoints = 0;
    String result = header(getName());

    for (Rental rental : rentals) {
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

      frequentRenterPoints++;

      if (rental.getMovie().getPriceCode() == Movie.NEW_RELEASE
          && rental.getDaysRented() > 1) {
        frequentRenterPoints++;
      }

      result += "\t" + rental.getMovie().getTitle() + "\t"
          + thisAmount + "\n";
      totalAmount += thisAmount;

    }

    result += footer(totalAmount, frequentRenterPoints);

    return result;
  }

  private String footer(double totalAmount, int frequentRenterPoints) {
    return String.format("You owed %s\nYou earned %d frequent renter points\n", totalAmount, frequentRenterPoints);
  }

  private String header(String name) {
    return String.format("Rental Record for %s\n", name);
  }


}
