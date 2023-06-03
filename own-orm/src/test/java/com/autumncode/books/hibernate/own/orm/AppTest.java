package com.autumncode.books.hibernate.own.orm;


import com.autumncode.books.hibernate.own.orm.entity.RestaurantEntity;
import com.autumncode.books.hibernate.util.session.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.util.List;

@Slf4j
public class AppTest {

  @Test
  public void showQueryComplexity() {
    try (var session = SessionUtil.getSession()) {

      var sql = """
              from RestaurantEntity r
              where r.restaurantId = :restaurantId
              and r.productId in :productList
              """;

      var restaurantId = "d215b5f8-0249-4dc5-89a3-51fd148cfb45";
      var productList = List.of("d215b5f8-0249-4dc5-89a3-51fd148cfb47", "d215b5f8-0249-4dc5-89a3-51fd148cfb48");
      //

      var query = session.createQuery(sql, RestaurantEntity.class);
      query.setParameter("restaurantId", restaurantId);
      query.setParameterList("productList", productList);
      //
      var result = query.getResultList();
      result.forEach(r -> log.info("{}-{}", r.getProductId(), r.getProductName()));

    }
  }
}
