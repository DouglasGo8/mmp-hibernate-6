package com.autumncode.books.hibernate.chapter06.mapped;


import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
public class ComputerBook extends Book {
  @Getter @Setter
  private String language;
}
