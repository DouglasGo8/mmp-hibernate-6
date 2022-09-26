package com.autumncode.books.hibernate.chapter06.embedded;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.List;

@Entity
public class User {
  @Id
  @GeneratedValue
  Long id;
  String name;
  // Not recommended
  String password;
  @ElementCollection
  List<String> passwordHints;
}
