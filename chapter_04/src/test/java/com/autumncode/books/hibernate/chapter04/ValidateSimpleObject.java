package com.autumncode.books.hibernate.chapter04;


import com.autumncode.books.hibernate.chapter04.model.SimpleObject;
import com.autumncode.books.hibernate.util.SessionUtil;

import static org.testng.AssertJUnit.assertEquals;


public class ValidateSimpleObject {

  static SimpleObject validate(Long id, Long expectedValue, String expectedKey) {
    SimpleObject so = null;
    try(var session = SessionUtil.getSession()) {
      so = session.load(SimpleObject.class, id);

      assertEquals(so.getKey(), expectedKey);
      assertEquals(so.getValue(), expectedValue);
    }

    return so;
  }

}
