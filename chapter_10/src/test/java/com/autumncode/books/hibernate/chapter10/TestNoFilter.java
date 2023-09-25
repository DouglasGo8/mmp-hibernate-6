package com.autumncode.books.hibernate.chapter10;

import com.autumncode.books.hibernate.chapter10.model.User;
import com.autumncode.books.hibernate.util.session.SessionUtil;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TestNoFilter extends TestBase {


  @Test
  public void testSimpleQuery() {
    SessionUtil.doWithSession((session) -> {
      var query = session.createQuery("from User", User.class);
      var users = query.list();
      assertEquals(users.size(), 4);
    });
  }
}
