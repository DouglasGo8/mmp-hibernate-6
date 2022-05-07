package com.autumncode.books.hibernate.chapter04.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;
  @Column
  String title;
  @ManyToOne
  Library library;
}
