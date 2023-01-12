package com.autumncode.books.hibernate.util.model;

import com.autumncode.books.hibernate.util.listener.UserAccountListener;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@EntityListeners({UserAccountListener.class})
public class UserAccount {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Integer id;
  String name;
  @Transient
  String password;

  Integer salt;
  Integer passwordHash;

  public boolean validPassword(String newPass) {
    return newPass.hashCode() * salt == getPasswordHash();
  }
}
