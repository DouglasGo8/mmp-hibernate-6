package com.autumncode.books.hibernate.chapter04;

import com.autumncode.books.hibernate.chapter04.model.SimpleObject;
import com.autumncode.books.hibernate.util.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

@Slf4j
public class MergeTest {

  @Test
  public void testMerge() {
    Long id;

    try (var session = SessionUtil.getSession()) {
      var tx = session.beginTransaction();
      var so = new SimpleObject();
      so.setKey("testMerge");
      so.setValue(1L);
      //
      session.save(so);
      //
      id = so.getId();
      //
      tx.commit();
    }

    var so = ValidateSimpleObject.validate(id, 1L, "testMerge");

    // the 'so' object is detached here
    so.setValue(2L);

    try (var session = SessionUtil.getSession()) {
      // merge is potentially an update, so we need a TX
      var tx = session.beginTransaction();
      //
      session.merge(so);
      tx.commit();
    }

    ValidateSimpleObject.validate(id, 2L, "testMerge");
  }

}
