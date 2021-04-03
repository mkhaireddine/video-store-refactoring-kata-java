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
    return generateStatement(new ConsoleRenderer());
  }

  public String statementHTML() {
    return generateStatement(new HtmlRenderer());
  }

  private String generateStatement(Renderer renderer) {
    return renderer.header(getName())
        + renderer.rentalLines(rentals)
        + renderer.footer(getTotalAmount(), getFrequentRenterPoints());
  }

  private double getTotalAmount() {
    return rentals.stream().mapToDouble(Rental::getAmount).sum();
  }

  private int getFrequentRenterPoints() {
    return rentals.stream().mapToInt(Rental::getFrequentRenterPoints).sum();
  }

}
