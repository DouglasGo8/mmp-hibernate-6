package com.autumncode.books.hibernate.chapter04;

import com.autumncode.books.hibernate.chapter04.model.SimpleObject;
import com.autumncode.books.hibernate.util.session.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

@Slf4j
public class SaveOrUpdateTest {

  @Test
  public void testSaveOrUpdateEntity() {
    Long id;
    SimpleObject obj;

    try (var session = SessionUtil.getSession()) {
      var tx = session.beginTransaction();
      session.createQuery("delete from SimpleObject").executeUpdate();
      tx.commit();
    }

    try (var session = SessionUtil.getSession()) {
      var tx = session.beginTransaction();
      obj = new SimpleObject();
      //
      obj.setKey("Open Source and Standards");
      obj.setValue(14L);
      session.persist(obj);
      assertNotNull(obj.getId());
      id = obj.getId();
      tx.commit();
    }

    try (var session = SessionUtil.getSession()) {
      var tx = session.beginTransaction();
      obj.setValue(12L);

      session.saveOrUpdate(obj);
      tx.commit();
    }

    assertEquals(id, obj.getId());

    try (var session = SessionUtil.getSession()) {
      var objs = session.createQuery("from SimpleObject", SimpleObject.class).getResultList();
      assertEquals(objs.size(), 1); // wrong behavior
    }


  }
}
