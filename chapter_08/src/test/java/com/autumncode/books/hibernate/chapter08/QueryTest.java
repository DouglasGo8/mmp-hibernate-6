package com.autumncode.books.hibernate.chapter08;

import com.autumncode.books.hibernate.chapter08.model.Supplier;
import com.autumncode.books.hibernate.util.session.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class QueryTest {
  final List<Integer> keys = new ArrayList();

  @BeforeMethod
  public void populateData() {
    log.info("populateData");
    this.clearSuppliers();
    try (var session = SessionUtil.getSession()) {
      var tx = session.beginTransaction();
      for (int i = 0; i < 10; i++) {
        var supplier = new Supplier("Supplier " + (i + 1));
        session.persist(supplier);
        keys.add(supplier.getId());
      }
      tx.commit();
    }
  }

  @AfterMethod
  public void clearSuppliers() {
    log.info("clearSuppliers");
    try (var session = SessionUtil.getSession()) {
      var tx = session.beginTransaction();
      session.createQuery("delete from Supplier").executeUpdate();
      tx.commit();
    }
  }

  @Test
  public void testSuppliers() {
    log.info("testSuppliers");
    for (int i = 0; i < 100; i++) {
      try (var session = SessionUtil.getSession()) {
        var tx = session.beginTransaction();
        // keeps the same key for all interactions
        // 03:27:42.228 [main] INFO  c.a.b.hibernate.chapter08.QueryTest - Supplier 1
        int key = keys.get((int) Math.random() * keys.size());
        var supplier = session.get(Supplier.class, key);
        log.info(supplier.getName());
        //
        tx.commit();
      }
    }
  }

}
