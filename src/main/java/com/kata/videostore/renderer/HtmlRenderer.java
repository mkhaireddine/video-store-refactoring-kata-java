package com.kata.videostore.renderer;

import static java.util.stream.Collectors.joining;

import com.kata.videostore.Rental;
import java.util.List;

public class HtmlRenderer implements Renderer {


  @Override
  public String rentalLines(List<Rental> rentals) {
    return rentals.stream()
        .map(rental -> rentalLine(rental.getTitle(), rental.getAmount()))
        .collect(joining("\n", "<table>\n", "\n</table>\n"));
  }

  @Override
  public String header(String name) {
    return String.format(
        "<h1>Rental Record for <em>%s</em></h1>\n", name);
  }

  @Override
  public String rentalLine(String title, double amount) {
    return String.format("  <tr><td>%s</td><td>%s</td></tr>", title, amount);
  }

  @Override
  public String footer(double totalAmount, int frequentRenterPoints) {
    return String.format("""
        <p>Amount owed is <em>%s</em></p>
        <p>You earned <em>%d</em> frequent renter points</p>
        """, totalAmount, frequentRenterPoints);
  }
}