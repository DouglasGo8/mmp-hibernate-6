package com.autumncode.books.hibernate.util.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity(name = "Thing")
public class Thing {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Integer id;
  //
  @Column
  String name;
}
