package com.autumncode.books.hibernate.chapter10;

import com.autumncode.books.hibernate.chapter10.model.User;
import com.autumncode.books.hibernate.util.session.SessionUtil;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {

  @BeforeMethod
  public void setupTest() {
    SessionUtil.doWithSession((session) -> {
      var user = new User("user1", true);
      user.addGroups("group1", "group2");
      session.persist(user);
      user = new User("user2", true);
      user.addGroups("group2", "group3");
      session.persist(user);
      user = new User("user3", false);
      user.addGroups("group3", "group4");
      session.persist(user);
      user = new User("user4", true);
      user.addGroups("group4", "group5");
      session.persist(user);
    });
  }

  @AfterMethod
  public void endTest() {
    SessionUtil.doWithSession((session) -> {
      // need to manually delete all of the Users since
      // HQL delete doesn't cascade over element collections
      var query = session.createQuery("from User", User.class);
      for (User user : query.list()) {
        session.remove(user);
      }
    });
  }
}
