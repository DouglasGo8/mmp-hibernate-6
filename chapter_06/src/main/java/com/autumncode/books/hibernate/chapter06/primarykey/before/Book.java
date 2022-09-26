package com.autumncode.books.hibernate.chapter06.primarykey.before;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class Book {
  int id;
  int pages;
  String title;
}
