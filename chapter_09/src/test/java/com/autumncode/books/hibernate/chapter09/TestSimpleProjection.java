package com.autumncode.books.hibernate.chapter09;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Slf4j
public class TestSimpleProjection extends TestBase {
  @Test
  public void testSimpleProjection() {
    var query = session.createQuery("select p.name from Product p", String.class);
    var suppliers = query.list();
    suppliers.forEach(log::info);
    assertEquals(suppliers.size(), 5);
  }
}
