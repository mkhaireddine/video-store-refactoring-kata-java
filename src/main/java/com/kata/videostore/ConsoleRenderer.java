package com.kata.videostore;

import java.util.List;
import java.util.stream.Collectors;

public class ConsoleRenderer implements Renderer {

  public ConsoleRenderer() {
  }

  @Override
  public String rentalLines(List<Rental> rentals) {
    return rentals.stream()
        .map(rental -> rentalLine(rental.getTitle(), rental.getAmount()))
        .collect(Collectors.joining());
  }

  @Override
  public String rentalLine(String title, double amount) {
    return String.format("\t%s\t%s\n", title, amount);
  }

  @Override
  public String footer(double totalAmount, int frequentRenterPoints) {
    return String.format("You owed %s\nYou earned %d frequent renter points\n", totalAmount, frequentRenterPoints);
  }

  @Override
  public String header(String name) {
    return String.format("Rental Record for %s\n", name);
  }
}
