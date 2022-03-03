package com.autumncode.books.hibernate.chapter04;

import com.autumncode.books.hibernate.chapter04.model.Email;
import com.autumncode.books.hibernate.chapter04.model.Message;
import com.autumncode.books.hibernate.util.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.*;

/**
 * @author dougdb
 */
@Slf4j
public class BrokenInversionTest {

  @Test
  public void testBrokenInversionCode() {
    Long emailId;
    Long messageId;
    Email email;
    Message message;

    try (var session = SessionUtil.getSession()) {
      var tx = session.beginTransaction();

      email = new Email("Broken");
      message = new Message("Broken");
      // mappedBy attr must be commented
      email.setMessage(message);
      //
      session.save(email);
      session.save(message);
      //

      emailId = email.getId();
      messageId = message.getId();

      tx.commit();
    }
    assertNotNull(email.getMessage());
    assertNull(message.getEmail());

    try (var session = SessionUtil.getSession()) {
      email = session.get(Email.class, emailId);
      log.info("{}", email);
      message = session.get(Message.class, messageId);
      log.info("{}", message);
    }

    assertNotNull(email.getMessage()); // join with Message Table exists
    assertNull(message.getEmail()); // join with Email Table not exists
  }
}
