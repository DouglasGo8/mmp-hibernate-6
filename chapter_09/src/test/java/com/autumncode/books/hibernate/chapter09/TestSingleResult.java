package com.autumncode.books.hibernate.chapter09;

import com.autumncode.books.hibernate.chapter09.model.Product;
import jakarta.persistence.NonUniqueResultException;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

@Slf4j
public class TestSingleResult extends TestBase {

  /*
  [ERROR] Failures:
  [ERROR] TestSingleResult.testGetSingleResultBad:14 » NonUniqueResult query did not return a unique result: 5
   */
  @Test(expectedExceptions = NonUniqueResultException.class)
  public void testGetSingleResultBad() {
    var query = session.createQuery("from Product", Product.class);
    assertNotNull(query.getSingleResult());
  }

  @Test
  public void testGetSingleResultGood() {
    var query = session.createQuery("from Product", Product.class);
    query.setMaxResults(1);
    assertNotNull(query.getSingleResult());
  }
}
