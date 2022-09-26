package com.autumncode.books.hibernate.chapter06.compoundpk;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class CPKBook {

  /*create table CPKBook
  (
    checkDigit integer not null,
    group_number integer not null,
    publisher integer not null,
    title integer not null,
    name varchar(255),
    primary key (checkDigit, group_number, publisher, title)
 );*/

  @Id
  Isbn id;

  @Column
  String name;
}
