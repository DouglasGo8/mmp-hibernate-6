package com.autumncode.books.hibernate.util.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Coordinate {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Integer id;
  @NotNull
  Integer x;
  @NotNull
  Integer y;
}
