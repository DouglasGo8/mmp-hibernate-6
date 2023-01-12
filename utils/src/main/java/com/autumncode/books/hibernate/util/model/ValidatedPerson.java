package com.autumncode.books.hibernate.util.model;


import jakarta.persistence.*;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class ValidatedPerson {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Column
  @NotNull
  @Size(min = 2, max = 60)
  String fname;

  @Column
  @NotNull
  @Size(min = 2, max = 60)
  String lname;

  @Column
  @Min(value = 13)
  Integer age;
}
