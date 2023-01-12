package com.autumncode.books.hibernate.chapter04;

import com.autumncode.books.hibernate.chapter04.model.SimpleObject;
import com.autumncode.books.hibernate.util.session.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.AssertJUnit.assertNotNull;

@Slf4j
public class DuplicateSaveTest {

  @Test
  public void duplicateSaveTest() {

    Long id;
    SimpleObject obj;

    try (var session = SessionUtil.getSession()) {
      var tx = session.beginTransaction();


      // Transient State
      obj = new SimpleObject();
      obj.setKey("Open Source and Standards");
      obj.setValue(10L);

      session.save(obj);
      assertNotNull(obj.getId());

      id = obj.getId();

      tx.commit();
    }

    try (var session = SessionUtil.getSession()) {
      var tx = session.beginTransaction();
      obj.setValue(12L);
      // this is not good behavior!
      // !!!!AVOID THIS!!!!
      session.save(obj);

      tx.commit();
    }

    // note that save() creates a new row in the database!
    // this is wrong behavior. Don't do this!
    assertNotEquals(id, obj.getId());

    try (var session = SessionUtil.getSession()) {
      var objs = session.createQuery("from SimpleObject", SimpleObject.class).getResultList();
      assertEquals(objs.size(), 2); // wrong behavior
    }
  }

}
