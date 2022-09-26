package com.autumncode.books.hibernate.chapter06.compoundpk;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.NoArgsConstructor;

import java.io.Serializable;

//The key class’ fields must match the entity to which they apply; you can’t use
//different names or types here. (The order of those fields, however, is not relevant.)
@Entity
@NoArgsConstructor
@IdClass(IdClassBook.EmbeddedISBN.class)
public class IdClassBook {

  @Id
  @Column(name = "group_number")
  int group;
  @Id
  int publisher;
  @Id
  int title;
  @Id
  int checkdigit;
  String name;

  static class EmbeddedISBN implements Serializable {
    //end::preamble[]
    @Column(name = "group_number")
    int group;
    int publisher;
    int title;
    int checkdigit;

    public EmbeddedISBN() {
    }

    public int getGroup() {
      return group;
    }

    public void setGroup(int group) {
      this.group = group;
    }

    public int getPublisher() {
      return publisher;
    }

    public void setPublisher(int publisher) {
      this.publisher = publisher;
    }

    public int getTitle() {
      return title;
    }

    public void setTitle(int title) {
      this.title = title;
    }

    public int getCheckdigit() {
      return checkdigit;
    }

    public void setCheckdigit(int checkdigit) {
      this.checkdigit = checkdigit;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Isbn)) return false;

      Isbn isbn = (Isbn) o;

      if (checkdigit != isbn.checkDigit) return false;
      if (group != isbn.group) return false;
      if (publisher != isbn.publisher) return false;
      if (title != isbn.title) return false;

      return true;
    }

    @Override
    public int hashCode() {
      int result = group;
      result = 31 * result + publisher;
      result = 31 * result + title;
      result = 31 * result + checkdigit;
      return result;
    }
  }
}
