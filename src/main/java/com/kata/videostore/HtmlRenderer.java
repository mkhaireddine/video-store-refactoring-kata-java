package com.kata.videostore;

import static java.util.stream.Collectors.joining;

import java.util.List;

public class HtmlRenderer implements Renderer {


  @Override
  public String rentalLines(List<Rental> rentals) {
    return rentals.stream()
        .map(rental -> rentalLine(rental.getTitle(), rental.getAmount()))
        .collect(joining());
  }

  @Override
  public String header(String name) {
    return String.format(
        "<h1>Rental Record for <em>%s</em></h1>\n", name);
  }

  @Override
  public String rentalLine(String title, double amount) {
    return String.format("\t%s\t%s\n", title, amount);
  }

  @Override
  public String footer(double totalAmount, int frequentRenterPoints) {
    return String.format("You owed %s\nYou earned %d frequent renter points\n", totalAmount, frequentRenterPoints);
  }
}
