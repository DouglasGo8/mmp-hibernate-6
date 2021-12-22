package com.autumncode.books.hibernate.util.hibernate;

import com.autumncode.books.hibernate.util.SessionUtil;
import com.autumncode.books.hibernate.util.model.Thing;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

@Slf4j
public class SessionBuilderTest {

  @Test
  public void testSessionFactory() {
    try (var session = SessionUtil.getSession()) {
      assertNotNull(session);
    }
  }


  @Test
  public void testDoWithSession() {
    SessionUtil.doWithSession(session -> {
      session.createQuery("delete from Thing").executeUpdate();
      var t = new Thing();
      t.setName("thingName");
      session.persist(t);
    });

    var thing = SessionUtil.returnFromSession(session -> {
      var query = session.createQuery("from Thing t where t.name=:name", Thing.class);

      query.setParameter("name", "thingName");

      return query.getSingleResult();
    });

    assertNotNull(thing);
    log.info("{}", thing);
    assertEquals(thing.getName(), "thingName");
  }
}
