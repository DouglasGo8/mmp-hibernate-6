package com.autumncode.books.hibernate.chapter06.single;

import jakarta.persistence.Entity;

@Entity(name = "SingleCBook")
public class ComputerBook extends Book {
  String primaryLanguage;
}
