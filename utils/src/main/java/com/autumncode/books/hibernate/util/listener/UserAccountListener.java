package com.autumncode.books.hibernate.util.listener;

import com.autumncode.books.hibernate.util.model.UserAccount;
import jakarta.persistence.PrePersist;

public class UserAccountListener {

  @PrePersist
  void setPasswordHash(Object o) {
    var ua = (UserAccount) o;
    if (ua.getSalt() == null || ua.getSalt() == 0) {
      ua.setSalt((int) (Math.random() * 65535));
    }
    ua.setPasswordHash(
            ua.getPassword().hashCode() * ua.getSalt()
    );
  }

}
