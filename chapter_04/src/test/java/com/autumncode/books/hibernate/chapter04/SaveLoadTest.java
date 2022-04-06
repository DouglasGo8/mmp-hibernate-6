package com.autumncode.books.hibernate.chapter04;

import com.autumncode.books.hibernate.chapter04.model.SimpleObject;
import com.autumncode.books.hibernate.util.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.*;

@Slf4j
public class SaveLoadTest {

  @Test
  public void testSaveLoad() {
    Long id = null;
    SimpleObject obj;

    try (var session = SessionUtil.getSession()) {
      var tx = session.beginTransaction();
      obj = new SimpleObject();
      obj.setKey("s1");
      obj.setValue(10L);
      //
      session.save(obj);
      assertNotNull(obj.getId());

      id = obj.getId();

      tx.commit();

    }

    try (var session = SessionUtil.getSession()) {

      var obj2 = session.load(SimpleObject.class, id);


      //log.info("Result {}", obj2.getId());

      assertEquals(obj2.getKey(), "s1");
      assertNotNull(obj2.getValue());
      assertEquals(obj2.getValue().longValue(), 10L);

      var obj3 = session.load(SimpleObject.class, id);

      // since o3 and o2 were loaded in the same session, they're not only
      // equivalent - as shown by equals() - but equal, as shown by ==.
      // since obj was NOT loaded in this session, it's equivalent but
      // not ==.

      assertEquals(obj2, obj3);
      assertEquals(obj, obj2);
      assertEquals(obj, obj3);
      //
      assertSame(obj2, obj3);// same is equivalent to ==
      assertNotSame(obj2, obj);
      //
      // assertSame(obj, obj3); // is not he same
      assertNotSame(obj, obj3); // sam
    }
  }
}
