package com.autumncode.books.hibernate.util.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class UnvalidatedSimplePerson {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;
  @Column
  String fname;
  @Column
  String lname;
  @Column
  Integer age;
}
