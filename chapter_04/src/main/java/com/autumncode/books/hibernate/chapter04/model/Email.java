package com.autumncode.books.hibernate.chapter04.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class Email {

  @Id
  @Getter
  @Setter
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;

  @Column
  @Getter
  @Setter
  String subject;

  @Getter
  @Setter
  @OneToOne(mappedBy = "email")
  Message message;

  public Email(String subject) {
    this.setSubject(subject);
  }
  //end::preamble[]

  //tag::postlude[]
  @Override
  public String toString() {
    // note use of message.content because otherwise properly constructed
    // relationships would cause an endless loop that never ends
    // and therefore runs endlessly.
    return String.format(
            "Email{id=%s, subject=`%s`, message.content=%s}",
            id,
            subject,
            (message != null ? message.getContent() : "null")
    );
  }

}
