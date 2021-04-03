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
    customer.addRental(new Rental(Movie.newRelease("The Cell"), 3));

    assertThat(customer.statement()).isEqualTo(
        """
            Rental Record for Fred
            \tThe Cell\t9.0
            You owed 9.0
            You earned 2 frequent renter points
            """);
  }

  @Test
  public void testDualNewReleaseStatement() {
    customer.addRental(new Rental(Movie.newRelease("The Cell"), 3));
    customer.addRental(new Rental(Movie.newRelease("The Tigger Movie"), 3));

    assertThat(customer.statement()).isEqualTo(
        """
            Rental Record for Fred
            \tThe Cell\t9.0
            \tThe Tigger Movie\t9.0
            You owed 18.0
            You earned 4 frequent renter points
            """);
  }

  @Test
  public void testSingleChildrensStatement() {
    customer.addRental(new Rental(Movie.children("The Tigger Movie"), 3));

    assertThat(customer.statement()).isEqualTo(
        """
            Rental Record for Fred
            \tThe Tigger Movie\t1.5
            You owed 1.5
            You earned 1 frequent renter points
            """);
  }

  @Test
  public void testSingleChildrensStatementRentedMoreThanThreeDaysAgo() {
    customer.addRental(new Rental(Movie.children("The Tigger Movie"), 4));

    assertThat(customer.statement()).isEqualTo(
        """
            Rental Record for Fred
            \tThe Tigger Movie\t3.0
            You owed 3.0
            You earned 1 frequent renter points
            """);
  }

  @Test
  public void testMultipleRegularStatement() {
    customer.addRental(new Rental(Movie.regular("Plan 9 from Outer Space"), 1));
    customer.addRental(new Rental(Movie.regular("8 1/2"), 2));
    customer.addRental(new Rental(Movie.regular("Eraserhead"), 3));

    assertThat(customer.statement()).isEqualTo(
        """
            Rental Record for Fred
            \tPlan 9 from Outer Space\t2.0
            \t8 1/2\t2.0
            \tEraserhead\t3.5
            You owed 7.5
            You earned 3 frequent renter points
            """);
  }

  @Test
  void generateHTML() {

    customer = new Customer("martin");
    customer.addRental(new Rental(Movie.regular("Plan 9 from Outer Space"), 1));
    customer.addRental(new Rental(Movie.regular("8 1/2"), 2));
    customer.addRental(new Rental(Movie.regular("Eraserhead"), 3));

    final String expected = """
        <h1>Rental Record for <em>martin</em></h1>
        <table>
          <tr><td>Plan 9 from Outer Space</td><td>2.0</td></tr>
          <tr><td>8 1/2</td><td>2.0</td></tr>
          <tr><td>Eraserhead</td><td>3.5</td></tr>
        </table>
        <p>Amount owed is <em>7.5</em></p>
        <p>You earned <em>3</em> frequent renter points</p>
        """;

    assertThat(customer.statementHTML()).isEqualTo(expected);
  }


  @Test
  public void testSinglePromoStatement() {
    customer = new Customer("Fred");
    customer.addRental(new Rental(Movie.promo("Promo 3"), 3));

    assertThat(customer.statement()).isEqualTo(
        """
            Rental Record for Fred
            \tPromo 3\t1.5
            You owed 1.5
            You earned 0 frequent renter points
            """);
  }

  @Test
  public void testMultiplePromoStatements() {

    customer = new Customer("Fred");
    customer.addRental(new Rental(Movie.promo("Promo 1"), 1));
    customer.addRental(new Rental(Movie.promo("Promo 2"), 2));
    customer.addRental(new Rental(Movie.promo("Promo 3"), 3));

    assertThat(customer.statement()).isEqualTo(
        """
            Rental Record for Fred
            \tPromo 1\t0.5
            \tPromo 2\t1.0
            \tPromo 3\t1.5
            You owed 3.0
            You earned 0 frequent renter points
            """);
  }

}
