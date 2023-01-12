package com.autumncode.books.hibernate.chapter04;

import com.autumncode.books.hibernate.chapter04.model.Email;
import com.autumncode.books.hibernate.chapter04.model.Message;
import com.autumncode.books.hibernate.util.session.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertNull;

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
      // Have on record on the Email table with message_id bind the relationship
      //session.save(email);

      session.persist(email);

      // Have on record on the Message table without email_id relationship
      // session.save(message);
      session.persist(message);
      //

      emailId = email.getId();
      messageId = message.getId();

      tx.commit();
    }

    assertNotNull(email.getMessage()); // message_id has a value

    // In this scenario we can't create a SELECT clause in Message table and
    // try join with Email Table
    // To get the desired effect (both table with relationship), both entities must be updated
    // There is no implicit call of message.setEmail(email)
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
