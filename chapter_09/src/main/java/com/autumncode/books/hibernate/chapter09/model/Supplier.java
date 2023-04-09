package com.autumncode.books.hibernate.chapter09.model;


import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "supplier.findAll", query = "from Supplier s"),
        @NamedQuery(name = "supplier.findByName", query = "from Supplier s where s.name=:name"),
        @NamedQuery(name = "supplier.averagePrice",
                query = "select p.supplier.id, avg(p.price) " +
                        "from Product p " +
                        "GROUP BY p.supplier.id"),
})
@NamedNativeQueries({@NamedNativeQuery(name = "supplier.findAverage",
        query = "SELECT p.supplier_id, avg(p.price) FROM Product p GROUP BY p.supplier_id"
)
})
public class Supplier implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;



  @NotNull
  @Column(unique = true)
  private String name;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "supplier", targetEntity = Product.class)
  List<Product> products = new ArrayList<>();

  public Supplier(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Supplier{");
    sb.append("id=").append(id);
    sb.append(", name='").append(name).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
