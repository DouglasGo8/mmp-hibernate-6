package com.autumncode.books.hibernate.chapter06.joined;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity(name="JoinedCBook")
public class ComputerBook extends Book {
  String primaryLanguage;
}
