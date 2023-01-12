package com.autumncode.books.hibernate.chapter06.relations;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;

import java.util.Set;

@Getter
@Entity
public class Family {
  @Id
  public Long id;

  public String familyName;

  @OneToMany(mappedBy = "family")
  public Set<Tasks> tasks;

  @OneToMany
  public Set<Parent> employees;



}
