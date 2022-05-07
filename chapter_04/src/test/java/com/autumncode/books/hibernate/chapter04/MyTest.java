package com.autumncode.books.hibernate.chapter04;

import com.autumncode.books.hibernate.chapter04.model.Email;
import com.autumncode.books.hibernate.chapter04.model.Message;
import com.autumncode.books.hibernate.util.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;


@Slf4j
public class MyTest {

  @Test
  public void myTest() {

    try (var session = SessionUtil.getSession()) {
      var tx = session.beginTransaction();

      var email = new Email();

      email.setSubject("My Email Test");
      session.persist(email);

      //
      var message = new Message();
      message.setEmail(email);
      message.setContent("My Content");
      session.persist(message);

      //
      log.info("The EMAIL id {}", message.getEmail().getId());


      tx.commit();
    }
  }
}

