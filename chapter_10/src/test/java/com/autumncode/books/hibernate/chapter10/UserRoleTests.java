package com.autumncode.books.hibernate.chapter10;

import com.autumncode.books.hibernate.chapter10.userrole.Role1;
import com.autumncode.books.hibernate.chapter10.userrole.User1;
import com.autumncode.books.hibernate.util.session.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@Slf4j
public class UserRoleTests {

  Map<String, Integer> roleMap = new HashMap<>();


  @BeforeTest
  void setupRoles() {
    SessionUtil.doWithSession(session -> {
      String[] names = {"role1", "role2", "role3"};
      for (String name : names) {
        var role1 = new Role1(name);
        session.persist(role1);
        roleMap.put(name, role1.getId());
      }
    });
  }

  @Test
  public void testUserWithRole() {
    SessionUtil.doWithSession(session -> {
      var user = new User1("foo", true);
      var roles = new HashSet<Role1>();
      roles.add(session.byId(Role1.class).load(roleMap.get("role1")));
      user.setRole1s(roles);
      session.persist(user);
    });

    SessionUtil.doWithSession(session -> {
      System.out.println(session.createQuery("from User1 u").list());
    });
  }
}
