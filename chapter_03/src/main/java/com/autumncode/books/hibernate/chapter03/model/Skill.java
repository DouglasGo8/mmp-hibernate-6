package com.autumncode.books.hibernate.chapter03.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Skill {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column
  private String name;

  @Override
  public String toString() {
    return "Skill{" +
            "name='" + name + '\'' +
            '}';
  }
}
