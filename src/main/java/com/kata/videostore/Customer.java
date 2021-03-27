package com.kata.videostore;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    return rentals.stream()
        .map(rental -> rentalLine(rental.getTitle(), rental.getAmount()))
        .collect(Collectors.joining());
  }

  private int getFrequentRenterPoints() {
    return rentals.stream().mapToInt(Rental::getFrequentRenterPoints).sum();
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
