package com.autumncode.books.hibernate.chapter09;

import com.autumncode.books.hibernate.chapter09.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Slf4j
public class TestNamedParams extends TestBase {

  @Test
  public void testNamedParams() {
    var query = session.createQuery(
            "from Product where price >= :price", Product.class);
    query.setParameter("price", 25.0);
    var products = query.list();
    assertEquals(products.size(), 1);
  }

}
