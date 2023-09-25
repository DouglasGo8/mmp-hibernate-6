package com.autumncode.books.hibernate.chapter10.userrole;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Role1 {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Integer id;
  @Column(unique = true)
  String name;

  public Role1(String name) {
    this.name = name;
  }
}
