package com.autumncode.books.hibernate.chapter06.perclass;

import jakarta.persistence.Entity;

@Entity(name = "PerClassCBook")
public class ComputerBook extends Book {
  String primaryLanguage;
}
