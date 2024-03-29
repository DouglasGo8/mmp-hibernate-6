package com.autumncode.books.hibernate.util.lifecycle;

import com.autumncode.books.hibernate.util.session.JPASessionUtil;
import com.autumncode.books.hibernate.util.model.LifecycleThing;
import lombok.extern.slf4j.Slf4j;
import org.testng.Reporter;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.*;

@Slf4j
public class FirstLifecycleTest {

  @Test
  public void testLifecycle() {
    Integer id;
    LifecycleThing thing1, thing2, thing3;
    //
    try (var session = JPASessionUtil.getSession("utiljpa")) {
      var tx = session.beginTransaction();
      thing1 = new LifecycleThing();
      thing1.setName("Thing 1");
      session.persist(thing1);
      id = thing1.getId();
      log.info("{}", thing1);
      //
      tx.commit();
    }
    //
    try (var session = JPASessionUtil.getSession("utiljpa")) {
      var tx = session.beginTransaction();
      thing2 = session.byId(LifecycleThing.class).load(-1);
      assertNull(thing2);
      Reporter.log("attempted to load nonexistent reference");
      //
      thing2 = session.byId(LifecycleThing.class).getReference(id);
      assertNotNull(thing2);
      assertEquals(thing1, thing2);
      thing2.setName("Thing 2");
      tx.commit();
    }
    //
    try (var session = JPASessionUtil.getSession("utiljpa")) {
      var tx = session.beginTransaction();
      thing3 = session.byId(LifecycleThing.class).getReference(id);
      assertNotNull(thing3);
      assertEquals(thing2, thing3);
      session.remove(thing3);
      //
      tx.commit();
    }
    assertEquals(LifecycleThing.lifecycleCalls.nextClearBit(0), 7);
  }
}
