package com.autumncode.books.hibernate.util.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Thing {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Integer id;
  //
  @Column
  String name;
}
