package com.autumncode.books.hibernate.chapter04;

import org.testng.annotations.Test;
import static org.testng.AssertJUnit.assertEquals;

public class BrokenInversionTest {

  @Test
  public void testBrokenInversionCode() {
    assertEquals(2, 2);
  }
}
