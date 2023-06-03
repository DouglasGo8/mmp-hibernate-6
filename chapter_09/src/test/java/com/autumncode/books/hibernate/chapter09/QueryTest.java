package com.autumncode.books.hibernate.chapter09;

import com.autumncode.books.hibernate.chapter09.model.Product;
import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

@Slf4j
public class QueryTest extends TestBase {

  @DataProvider
  final Object[][] queryTypesProvider() {
    return new Object[][]{
            {"Supplier", 2},
            {"Product", 5},
            {"Software", 2},
    };
  }

  @Test
  @Ignore
  public void testProjection() {
    Query query = session.createNamedQuery("supplier.averagePrice");
    List<Object[]> suppliers = query.getResultList();

    for (Object[] o : suppliers) {
      log.info("{}", Arrays.toString(o));
    }

    assertEquals(suppliers.size(), 2);

  }

  @Test
  @Ignore
  public void testLikedQuery() {
    var sql = """
            from Product p
            where p.price > :minprice
            and p.description like :desc
            """;
    var query = session.createQuery(sql, Product.class);
    query.setParameter("desc", "Mou%");
    query.setParameter("minprice", 15.0);
    //
    var products = query.list();
    products.forEach(p -> log.info("{}-${}", p.getName(), p.getPrice()));
    assertEquals(products.size(), 1);
  }

  @Test(dataProvider = "queryTypesProvider")
  public void testQueryTypes(String type, Integer count) {
    var query = session.createQuery("from ".concat(type));
    var list = query.list();

    list.forEach(projection -> log.info("{}", projection));

    assertEquals(list.size(), count.intValue());
  }

  @Test
  public void searchForProduct() {
    var query = session.createNamedQuery("product.searchByPhrase", Product.class);
    query.setParameter("text", "%Mou%");
    var products = query.list();
    //
    assertEquals(products.size(), 3);
  }
}
