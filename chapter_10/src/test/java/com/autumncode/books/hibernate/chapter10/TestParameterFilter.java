package com.autumncode.books.hibernate.chapter10;

import com.autumncode.books.hibernate.chapter10.model.User;
import com.autumncode.books.hibernate.util.session.SessionUtil;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TestParameterFilter extends TestBase {
  @DataProvider
  Object[][] statuses() {
    return new Object[][]{
            {true, 3},
            {false, 1}
    };
  }

  @Test(dataProvider = "statuses")
  public void testFilter(boolean status, int count) {
    SessionUtil.doWithSession((session) -> {
      var query = session.createQuery("from User", User.class);

      session.enableFilter("byStatus").setParameter("status", status);

      var users = query.list();
      assertEquals(users.size(), count);
    });
  }

}
