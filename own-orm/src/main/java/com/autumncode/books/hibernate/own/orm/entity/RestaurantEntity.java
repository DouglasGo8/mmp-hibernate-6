package com.autumncode.books.hibernate.own.orm.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Immutable;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@Builder
@Entity
@Immutable
@NoArgsConstructor
@AllArgsConstructor
@IdClass(RestaurantEntityId.class)
@Table(name="order_restaurant_mview")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class RestaurantEntity {

  @Id
  @Column(name = "restaurant_id")
  private String restaurantId;
  @Id
  @Column(name = "product_id")
  private String productId;
  @Column(name = "restaurant_name")
  private String restaurantName;
  @Column(name = "restaurant_active")
  private Boolean restaurantActive;
  @Column(name = "product_name")
  private String productName;
  @Column(name = "product_price")
  private BigDecimal productPrice;
  @Column(name = "product_available")
  private Boolean productAvailable;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    RestaurantEntity that = (RestaurantEntity) o;
    return restaurantId.equals(that.restaurantId) && productId.equals(that.productId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(restaurantId, productId);
  }
}