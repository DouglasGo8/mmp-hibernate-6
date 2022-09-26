package com.autumncode.books.hibernate.chapter06.compoundpk;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
public class Isbn implements Serializable {
  @Column(name="group_number")
  int title;
  int group;
  int publisher;
  int checkDigit;
}
