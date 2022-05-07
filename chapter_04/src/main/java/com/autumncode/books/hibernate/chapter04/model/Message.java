package com.autumncode.books.hibernate.chapter04.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Message {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;

  @Column
  String content;

  @OneToOne
  Email email;

  public Message(String content) {
    this.setContent(content);
  }
  //end::preamble[]

  //tag::postlude[]
  @Override
  public String toString() {
    // note use of email.subject because otherwise properly constructed
    // relationships would cause an endless loop that never ends
    // and therefore runs endlessly.
    return String.format("Message{id=%d, content='%s', email.subject='%s'}",
            id, content, (email != null ? email.getSubject() : "null"));
  }

}
