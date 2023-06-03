package com.autumncode.books.hibernate.chapter09;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

@Slf4j
public class TestNativeQuery extends TestBase {

  @Test
  public void testNativeQuery() {
    var query = session.createNamedQuery("supplier.findAverage");
    List<Object[]> suppliers = query.list();
    suppliers.forEach(o -> log.info("{}", Arrays.toString(o)));
    assertEquals(suppliers.size(), 2);
  }

  @Test
  public void testHSQLAggregate() {
    var query = session.createNamedQuery("supplier.averagePrice");
    List<Object[]> suppliers = query.list();
    suppliers.forEach(o -> log.info("{}", Arrays.toString(o)));
    assertEquals(suppliers.size(), 2);
  }
}
