package com.autumncode.books.hibernate.chapter04;


import com.autumncode.books.hibernate.chapter04.model.SimpleObject;
import com.autumncode.books.hibernate.util.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

@Slf4j
public class RefreshTest {

  @Test
  public void testRefresh() {
    long id = 0L;
    try (var session = SessionUtil.getSession()) {

      var tx = session.beginTransaction();
      var so = new SimpleObject();

      so.setKey("testMerge");
      so.setValue(1L);
      //
      session.persist(so);
      //
      id = so.getId();
      //
      tx.commit();
    }
    //
    var so = ValidateSimpleObject.validate(id, 1L, "testMerge");
    // the 'so' object is detached here
    so.setValue(2L);

    try (var session = SessionUtil.getSession()) {
      session.refresh(so); // update the POJO values with DB values
    }

    // Keep going 1L
    ValidateSimpleObject.validate(id, 1L, "testMerge");
  }
}
