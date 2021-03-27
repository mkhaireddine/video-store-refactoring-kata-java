package com.kata.videostore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class VideoStoreTest {

  private Customer customer;

  @BeforeEach
  public void setUp() {
    customer = new Customer("Fred");
  }

  @Test
  public void testSingleNewReleaseStatement() {
    customer.addRental(new Rental(newRelease("The Cell"), 3));

    assertThat(customer.statement()).isEqualTo(
        "Rental Record for Fred\n"
            + "\tThe Cell\t9.0\n"
            + "You owed 9.0\n"
            + "You earned 2 frequent renter points\n");
  }

  @Test
  public void testDualNewReleaseStatement() {
    customer.addRental(new Rental(newRelease("The Cell"), 3));
    customer.addRental(new Rental(newRelease("The Tigger Movie"), 3));

    assertThat(customer.statement()).isEqualTo(
        "Rental Record for Fred\n"
            + "\tThe Cell\t9.0\n"
            + "\tThe Tigger Movie\t9.0\n"
            + "You owed 18.0\n"
            + "You earned 4 frequent renter points\n");
  }

  private static Movie newRelease(String title) {
    return new Movie(title, Movie.NEW_RELEASE);
  }

  @Test
  public void testSingleChildrensStatement() {
    customer.addRental(new Rental(children("The Tigger Movie"), 3));

    assertThat(customer.statement()).isEqualTo(
        "Rental Record for Fred\n"
            + "\tThe Tigger Movie\t1.5\n"
            + "You owed 1.5\n"
            + "You earned 1 frequent renter points\n");
  }

  private static Movie children(String title) {
    return new Movie(title, Movie.CHILDRENS);
  }

  @Test
  public void testSingleChildrensStatementRentedMoreThanThreeDaysAgo() {
    customer.addRental(new Rental(children("The Tigger Movie"), 4));

    assertThat(customer.statement()).isEqualTo(
        "Rental Record for Fred\n"
            + "\tThe Tigger Movie\t3.0\n"
            + "You owed 3.0\n"
            + "You earned 1 frequent renter points\n");
  }

  @Test
  public void testMultipleRegularStatement() {
    customer.addRental(new Rental(regular("Plan 9 from Outer Space"), 1));
    customer.addRental(new Rental(regular("8 1/2"), 2));
    customer.addRental(new Rental(regular("Eraserhead"), 3));

    assertThat(customer.statement()).isEqualTo(
        "Rental Record for Fred\n"
            + "\tPlan 9 from Outer Space\t2.0\n"
            + "\t8 1/2\t2.0\n"
            + "\tEraserhead\t3.5\n"
            + "You owed 7.5\n"
            + "You earned 3 frequent renter points\n");
  }

  private static Movie regular(String title) {
    return new Movie(title, Movie.REGULAR);
  }

  @Test
  @Disabled
  void generateHTML() {

    customer = new Customer("martin");
    customer.addRental(new Rental(regular("Plan 9 from Outer Space"), 1));
    customer.addRental(new Rental(regular("8 1/2"), 2));
    customer.addRental(new Rental(regular("Eraserhead"), 3));

    final String expected = "<h1>Rental Record for <em>martin</em></h1>\n"
        + "<table>\n"
        + "  <tr><td>Plan 9 from Outer Space</td><td>2.0</td></tr>\n"
        + "  <tr><td>8 1/2</td><td>2.0</td></tr>\n"
        + "  <tr><td>Eraserhead</td><td>3.5</td></tr>\n"
        + "</table>\n"
        + "<p>Amount owed is <em>7.5</em></p>\n"
        + "<p>You earned <em>3</em> frequent renter points</p>\n";

//    assertThat(customer.statementHTML()).isEqualTo(expected);
  }


  @Test
  @Disabled
  public void testSinglePromoStatement() {
    // Price : 0.5 per day
    // frequent points : always 0
    // customer.addRental(new Rental(new Movie("Promo 3", Movie.PROMO), 3));

    assertThat(customer.statement()).isEqualTo(
        "Rental Record for Fred\n"
            + "\tPromo 3\t1.5\n"
            + "You owed 1.5\n"
            + "You earned 0 frequent renter points\n");
  }

  @Test
  @Disabled
  public void testMultiplePromoStatements() {
//    customer.addRental(new Rental(new Movie("Promo 1", Movie.PROMO), 1));
//    customer.addRental(new Rental(new Movie("Promo 2", Movie.PROMO), 2));
//    customer.addRental(new Rental(new Movie("Promo 3", Movie.PROMO), 3));

    assertThat(customer.statement()).isEqualTo(
        "Rental Record for Fred\n"
            + "\tPromo 1\t0.5\n"
            + "\tPromo 2\t1.0\n"
            + "\tPromo 3\t1.5\n"
            + "You owed 3.0\n"
            + "You earned 0 frequent renter points\n");
  }

}
