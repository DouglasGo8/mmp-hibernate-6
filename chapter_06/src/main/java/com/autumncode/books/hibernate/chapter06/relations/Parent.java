package com.autumncode.books.hibernate.chapter06.relations;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class Parent {

  @Id
  public Long id;
  public String employeeName;
}
