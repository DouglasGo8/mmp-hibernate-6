package com.autumncode.books.hibernate.util.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Thing {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Integer id;
  @Column
  String name;
}
