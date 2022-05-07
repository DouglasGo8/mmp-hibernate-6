package com.autumncode.books.hibernate.chapter04.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Library {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;
  @Column
  String name;

  @OneToMany(orphanRemoval = true, mappedBy = "library")
  List<Book> books = new ArrayList<>();

}
