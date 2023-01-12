package com.autumncode.books.hibernate.util.lifecycle;

import com.autumncode.books.hibernate.util.session.JPASessionUtil;
import com.autumncode.books.hibernate.util.model.Thing;
import jakarta.persistence.PersistenceException;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.*;

public class JPASessionUtilTest {

  @Test
  public void getEntityManager() {
    var em = JPASessionUtil.getEntityManager("utiljpa");
    //
    em.close();
  }

  @Test(expectedExceptions = {PersistenceException.class})
  public void nonexistentEntityManagerName() {
    JPASessionUtil.getEntityManager("nonexistent");
    fail("We shouldn't be able to acquire an EntityManager here");
  }

  @Test
  public void getSession() {
    var session = JPASessionUtil.getSession("utiljpa");
    session.close();
  }

  @Test(expectedExceptions = {PersistenceException.class})
  public void nonexistentSessionName() {
    JPASessionUtil.getSession("nonexistent");
    fail("We shouldn't be able to acquire a Session here");
  }

  @Test
  public void testEntityManager() {
    var em = JPASessionUtil.getEntityManager("utiljpa");
    em.getTransaction().begin();
    var thing = new Thing();
    thing.setName("Thing 1");
    em.persist(thing);
    //
    em.getTransaction().commit();
    em.close();
    //
    em = JPASessionUtil.getEntityManager("utiljpa");
    em.getTransaction().begin();
    //
    var typedQuery = em.createQuery("from Thing t where t.name=:name", Thing.class);
    typedQuery.setParameter("name", "Thing 1");
    var thingQ1 = typedQuery.getSingleResult();
    //
    assertNotNull(thingQ1);
    assertEquals(thingQ1, thing);
    //
    em.remove(thingQ1);
    em.getTransaction().commit();
    em.close();
  }

  @Test
  public void testSession() {
    Thing t = null;
    try (var session = JPASessionUtil.getSession("utiljpa")) {
      var tx = session.beginTransaction();
      t = new Thing();
      t.setName("Thing 2");
      session.persist(t);
      tx.commit();
    }
    //

    try (var session = JPASessionUtil.getSession("utiljpa")) {
      var tx = session.beginTransaction();
      var q = session.createQuery("from Thing t where t.name=:name", Thing.class);
      //
      q.setParameter("name", "Thing 2");
      var result = q.uniqueResult();
      assertNotNull(result);
      assertEquals(result, t);
      session.detach(result);
      tx.commit();
    }

    try (var session = JPASessionUtil.getSession("utiljpa")) {
      var tx = session.beginTransaction();
      var q = session.createQuery("from Thing t where t.name=:name", Thing.class);
      //
      q.setParameter("name", "Thing 2");
      var result = q.uniqueResult();
      session.remove(result);
      tx.commit();
    }
  }
}
