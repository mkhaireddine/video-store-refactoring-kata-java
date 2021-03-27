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
    ConsoleRenderer consoleRenderer = new ConsoleRenderer();
    String result = consoleRenderer.header(getName());
    result += consoleRenderer.rentalLines(rentals);
    result += consoleRenderer.footer(getTotalAmount(), getFrequentRenterPoints());
    return result;
  }

  private double getTotalAmount() {
    return rentals.stream().mapToDouble(Rental::getAmount).sum();
  }

  private int getFrequentRenterPoints() {
    return rentals.stream().mapToInt(Rental::getFrequentRenterPoints).sum();
  }


}
