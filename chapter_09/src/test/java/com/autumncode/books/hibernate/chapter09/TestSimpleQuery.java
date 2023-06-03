package com.autumncode.books.hibernate.chapter09;

import com.autumncode.books.hibernate.chapter09.model.Product;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class TestSimpleQuery extends TestBase {

  @Test
  public void testSimpleQuery() {
    var query = super.session.createQuery("from Product", Product.class);
    query.setComment("This is only a query for product");
    var products = query.list();
    assertEquals(products.size(), 5);
  }
}
