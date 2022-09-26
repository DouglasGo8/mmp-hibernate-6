package com.autumncode.books.hibernate.chapter06;

import com.autumncode.books.hibernate.util.SessionUtil;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertNotNull;

public class BookTest {

  @Test
  public void bookTest() {
    try (var session = SessionUtil.getSession()) {
      assertNotNull(session);
    }

  }
}
