package com.autumncode.books.hibernate.chapter10.userrole;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class User1 {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Integer id;
  @Column(unique = true)
  String name;
  boolean active;
  @ManyToMany
  Set<Role1> role1s;

  public User1(String name, boolean active) {
    this.name = name;
    this.active = active;
  }
}
