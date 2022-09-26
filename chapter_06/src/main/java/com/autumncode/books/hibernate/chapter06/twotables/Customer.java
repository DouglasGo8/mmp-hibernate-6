package com.autumncode.books.hibernate.chapter06.twotables;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@SecondaryTable(name = "customer_details")
@Table(name = "customer", uniqueConstraints = {@UniqueConstraint(columnNames = "name")})
public class Customer {
  @Id public int id;
  public String name;
  @Column(table = "customer_details")
  public String address;
}
