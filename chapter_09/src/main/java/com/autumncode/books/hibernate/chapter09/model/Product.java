package com.autumncode.books.hibernate.chapter09.model;

import jakarta.persistence.*;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
        @NamedQuery(name = "product.searchByPhrase",
                query = "from Product p where p.name like :text or p.description like :text"),
        @NamedQuery(name = "product.findProductAndSupplier",
                query = "from Product p, Supplier s where p.supplier=s")
})
public class Product implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Integer id;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  Supplier supplier;

  @Column
  @NotNull
  String name;

  @Column
  @NotNull
  String description;

  @Column
  @NotNull
  Double price;

  public Product(Supplier supplier, String name, String description, Double price) {
    this.name = name;
    this.price = price;
    this.supplier = supplier;
    this.description = description;
  }

  @Override
  public String toString() {
    return "Product{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", supplier='" + getSupplier() + '\'' +
            ", price=" + price +
            '}';
  }


}
