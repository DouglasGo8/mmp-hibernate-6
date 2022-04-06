package com.autumncode.books.hibernate.chapter04;

import com.autumncode.books.hibernate.chapter04.model.Email;
import com.autumncode.books.hibernate.chapter04.model.Message;
import com.autumncode.books.hibernate.util.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertNotNull;

/**
 * @author dougdb
 */
@Slf4j
public class ProperSimpleInversionTest {

  @Test
  public void testProperSimpleInversionCode() {
    Long emailId;
    Long messageId;
    Email email;
    Message message;

    try (var session = SessionUtil.getSession()) {
      var tx = session.beginTransaction();

      // mappedBy attr must be commented to simulate the explicit behavior
      email = new Email("Broken");
      message = new Message("Broken");
      //
      email.setMessage(message);
      message.setEmail(email);
      //
      session.save(email);
      session.save(message);
      //

      emailId = email.getId();
      messageId = message.getId();

      tx.commit();
    }
    //
    assertNotNull(email.getMessage());
    assertNotNull(message.getEmail());

    try (var session = SessionUtil.getSession()) {
      email = session.get(Email.class, emailId);
      log.info("{}", email);
      message = session.get(Message.class, messageId);
      log.info("{}", message);
    }

    assertNotNull(email.getMessage()); // join with Message Table exists
    assertNotNull(message.getEmail()); // join with Email Table not exists
  }

}
