package com.autumncode.books.hibernate.chapter09;

import com.autumncode.books.hibernate.chapter09.model.Supplier;
import com.autumncode.books.hibernate.util.session.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;

@Slf4j
public class TestPagination {
  @Test
  public void testPagination() {
    try (var session = SessionUtil.getSession()) {
      var tx = session.beginTransaction();
      session.createQuery("delete from Product").executeUpdate();
      session.createQuery("delete from Supplier").executeUpdate();
      //
      for (int i = 0; i < 30; i++) {
        var supplier = new Supplier();
        supplier.setName(String.format("supplier %02d", i));
        session.save(supplier);
      }
      //
      tx.commit();
    }
    //
    try (var session = SessionUtil.getSession()) {
      var sql = """
              select s.name
              from Supplier s
              order by s.name
              """;
      var query = session.createQuery(sql, String.class);
      //
      query.setFirstResult(4);
      query.setMaxResults(4);
      //
      var suppliers = query.list();
      // suppliers.stream().collect(Collectors.joining(","));
      var joinedList = String.join(",", suppliers);
      assertEquals(joinedList, "supplier 04,supplier 05,supplier 06,supplier 07");
    }
  }
}
