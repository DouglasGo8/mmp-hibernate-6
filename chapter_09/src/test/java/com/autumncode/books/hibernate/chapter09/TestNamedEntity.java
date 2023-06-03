package com.autumncode.books.hibernate.chapter09;

import com.autumncode.books.hibernate.chapter09.model.Product;
import com.autumncode.books.hibernate.chapter09.model.Supplier;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

@Slf4j
public class TestNamedEntity extends TestBase {

  @Test
  public void testNamedEntity() {
    var query1 = session.createQuery("from Supplier where name=:name", Supplier.class);
    query1.setParameter("name", "Supplier 2");
    var supplier = query1.getSingleResult();
    assertNotNull(supplier);
    //
    var query2 = session.createQuery("from Product where supplier =:supplier", Product.class);
    //
    query2.setParameter("supplier", supplier);
    //
    var products = query2.list();
    assertEquals(products.size(), 3);

  }
}
