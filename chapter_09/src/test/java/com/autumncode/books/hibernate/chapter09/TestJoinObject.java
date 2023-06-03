package com.autumncode.books.hibernate.chapter09;

import com.autumncode.books.hibernate.chapter09.dto.ProductAndSupplier;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Slf4j
public class TestJoinObject extends TestBase {
  @Test
  public void testJoinObject() {
    var sql = """
            select new %s(p,s)
            from Product p, Supplier s where p.supplier=s
            """.formatted(ProductAndSupplier.class.getCanonicalName());
    var query = session.createQuery(sql, ProductAndSupplier.class);
    //log.info(sql);
    var suppliers = query.list();
    //
    suppliers.forEach(ps -> log.info(ps.toString()));
    assertEquals(suppliers.size(), 5);

  }
}
