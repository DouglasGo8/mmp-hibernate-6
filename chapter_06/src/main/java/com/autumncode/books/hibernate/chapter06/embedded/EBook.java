package com.autumncode.books.hibernate.chapter06.embedded;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Set;

@Entity
public class EBook {
  @Id
  @GeneratedValue
  Long id;
  String name;
  @ElementCollection
  Set<Author> authors;
}
