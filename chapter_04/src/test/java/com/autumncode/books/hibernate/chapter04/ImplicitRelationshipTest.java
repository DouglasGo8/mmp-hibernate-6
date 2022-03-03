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
public class ImplicitRelationshipTest {

  @Test
  public void testImpliedRelationship() {
    Long emailId;
    Long messageId;
    Email email;
    Message message;

    try (var session = SessionUtil.getSession()) {
      var tx = session.beginTransaction();
      email = new Email("Inverse Email");
      message = new Message("Inverse Message");
      // mappedBy attr must be uncommented
      // email.setMessage(message);
      message.setEmail(email);
      //
      session.save(email);
      session.save(message);
      //
      emailId = email.getId();
      messageId = message.getId();
      tx.commit();
    }

    assertEquals(email.getSubject(), "Inverse Email");
    assertEquals(message.getContent(), "Inverse Message");
    // Session
    assertNull(email.getMessage());
    assertNotNull(message.getEmail());

    try (var session = SessionUtil.getSession()) {
      email = session.get(Email.class, emailId);
      log.info("{}", email);
      message = session.get(Message.class, messageId);
      log.info("{}", message);
    }

    assertNotNull(email.getMessage());
    assertNotNull(message.getEmail());
  }
}
