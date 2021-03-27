package com.kata.videostore;

import java.util.List;

public interface Renderer {

  String rentalLines(List<Rental> rentals);

  String rentalLine(String title, double amount);

  String footer(double totalAmount, int frequentRenterPoints);

  String header(String name);
}
