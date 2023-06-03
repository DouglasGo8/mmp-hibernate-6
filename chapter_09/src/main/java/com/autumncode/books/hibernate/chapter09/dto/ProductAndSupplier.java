package com.autumncode.books.hibernate.chapter09.dto;

import com.autumncode.books.hibernate.chapter09.model.Product;
import com.autumncode.books.hibernate.chapter09.model.Supplier;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProductAndSupplier {
  private final Product product;
  private final Supplier supplier;

  @Override
  public String toString() {
    return "ProductAndSupplier{" +
            "product=" + product +
            ",\n  supplier=" + supplier +
            '}';
  }
}
