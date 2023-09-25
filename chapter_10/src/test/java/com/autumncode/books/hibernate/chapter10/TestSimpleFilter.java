package com.autumncode.books.hibernate.chapter10;

import com.autumncode.books.hibernate.chapter10.model.User;
import com.autumncode.books.hibernate.util.session.SessionUtil;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TestSimpleFilter extends TestBase {

  @Test
  public void testNoParameterFilter() {
    SessionUtil.doWithSession((session) -> {
      var query = session.createQuery("from User", User.class);

      session.enableFilter("userEndsWith1");
      var users = query.list();
      assertEquals(users.size(), 1);
      assertEquals(users.get(0).getName(), "user1");
    });
  }
}
