package com.autumncode.books.hibernate.chapter06.relations;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Getter
@Entity
public class Tasks {

  @Id
  public Long id;
  public String taskName;

  @ManyToOne
  @JoinColumn(name = "family_id")
  public Family family;


}
