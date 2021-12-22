package com.autumncode.books.hibernate.chapter01.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Message {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String text;

  public Message(String text) {
    this.text = text;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Message)) return false;
    Message message = (Message) o;
    return Objects.equals(getId(), message.getId())
            && Objects.equals(getText(), message.getText());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getText());
  }

  @Override
  public String toString() {
    return String.format("Message{id=%d,text='%s'}", getId(), getText());
  }
}
