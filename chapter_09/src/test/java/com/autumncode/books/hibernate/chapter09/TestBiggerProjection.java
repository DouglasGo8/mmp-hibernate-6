package com.autumncode.books.hibernate.chapter09;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

@Slf4j
public class TestBiggerProjection extends TestBase {
  @Test
  public void testBiggerProjection() {
    var sql = """
            select p.name, p.price from Product p
            """;
    var query = session.createQuery(sql);
    List<Object[]> products = query.list();
    products.forEach(data -> log.info("{}", Arrays.toString(data)));
    assertEquals(products.size(), 5);
  }
}
