package com.autumncode.books.hibernate.chapter10;

import com.autumncode.books.hibernate.chapter10.model.User;
import com.autumncode.books.hibernate.util.session.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

@Slf4j
public class TestMultipleFilters extends TestBase {
  @Test
  public void testGroupFilter() {
    SessionUtil.doWithSession((session) -> {
      var query = session.createQuery("from User", User.class);

      session.enableFilter("byGroup").setParameter("group", "group4");

      var users = query.list();

      //log.info("===>{}", users.size());
      assertEquals(users.size(), 2);

      /*session.enableFilter("byGroup").setParameter("group", "group1");

      users = query.list();
      assertEquals(users.size(), 1);

      // should be user 1
      assertEquals(users.get(0).getName(), "user1");*/
    });
  }

  //@Test
  public void testBothFilters() {
    SessionUtil.doWithSession((session) -> {
      var query = session.createQuery("from User", User.class);

      session.enableFilter("byGroup").setParameter("group", "group4");
      session.enableFilter("byStatus").setParameter("status", Boolean.TRUE);

      List<User> users = query.list();

      assertEquals(users.size(), 1);
      assertEquals(users.get(0).getName(), "user4");
    });
  }
}
