package com.autumncode.books.hibernate.chapter09.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@EqualsAndHashCode(callSuper = false)
public class Software extends Product implements Serializable {
  @Column
  @NotNull
  String version;

  public Software(Supplier supplier, String name, String description, Double price, String version) {
    super(supplier, name, description, price);
    //
    this.version = version;
  }

  @Override
  public String toString() {
    return "Software{" +
            "id=" + super.id +
            ", supplier=" + super.supplier +
            ", name='" + super.name + '\'' +
            ", description='" + super.description + '\'' +
            ", price=" + super.price +
            ", version='" + version + '\'' +
            '}';
  }

}
