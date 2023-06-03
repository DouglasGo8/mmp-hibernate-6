package com.autumncode.books.hibernate.chapter09;

import com.autumncode.books.hibernate.chapter09.model.Product;
import com.autumncode.books.hibernate.chapter09.model.Supplier;
import com.autumncode.books.hibernate.util.session.SessionUtil;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;


public class DeleteHQLTest {

  @Test
  public void testDeleteHQL() {
    try (var session = SessionUtil.getSession()) {
      var tx = session.beginTransaction();
      session.createQuery("delete from Supplier ").executeUpdate();
      //session.remove("from Supplier");
      var supplier = new Supplier("DSupplier 1");
      session.persist(supplier);
      var product = new Product(supplier, "supp_name", "supp_desc", 10d);
      supplier.getProducts().add(product);
      session.persist(new Supplier("DSupplier 2"));
      tx.commit();
      //
      tx = session.beginTransaction();
      var query = session.createQuery("from Supplier ", Supplier.class);
      query.setReadOnly(true);
      var suppliers = query.list();
      suppliers.forEach(System.out::println);
      //
      assertEquals(suppliers.size(), 2);
      //
      session.createQuery("delete from Product").executeUpdate();
      //
      var deleteAll = session.createQuery("delete from Supplier");
      deleteAll.executeUpdate();
      //session.remove(suppliers);
      tx.commit();
      assertEquals(query.list().size(), 0);
    }
  }

}
