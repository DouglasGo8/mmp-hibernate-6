package com.autumncode.books.hibernate.chapter06.mapped;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
public class Book {
  @Id
  @GeneratedValue
  Integer id;
  String name;
}
