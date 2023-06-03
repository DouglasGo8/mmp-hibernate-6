package com.autumncode.books.hibernate.chapter09;


import com.autumncode.books.hibernate.chapter09.model.Product;
import com.autumncode.books.hibernate.chapter09.model.Supplier;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Slf4j
public class TestJoinArray extends TestBase {
  @Test
  public void testJoinArray() {

    var query = session.createNamedQuery("product.findProductAndSupplier");
    List<Object[]> suppliers = query.list();
    //
    for (var o : suppliers) {
      assertTrue(o[0] instanceof Product);
      assertTrue(o[1] instanceof Supplier);
    }
    assertEquals(suppliers.size(), 5);
  }
}
