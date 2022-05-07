package com.autumncode.books.hibernate.chapter04.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class SimpleObject {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;

  @Column
  String key;
  @Column
  Long value;

  @Override
  public String toString() {
    return "SimpleObject{" +
            "id=" + id +
            ", key='" + key + '\'' +
            ", value=" + value +
            '}';
  }


  //tag::postlude[]
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof SimpleObject)) return false;

    var that = (SimpleObject) o;

    // we prefer the method versions of accessors, because of Hibernate proxies.
    if (getId() != null
            ? !getId().equals(that.getId())
            : that.getId() != null)
      return false;

    if (getKey() != null
            ? !getKey().equals(that.getKey())
            : that.getKey() != null)
      return false;

    return getValue() != null
            ? getValue().equals(that.getValue())
            : that.getValue() == null;
  }

  @Override
  public int hashCode() {
    int result = getId() != null ? getId().hashCode() : 0;
    result = 31 * result + (getKey() != null ? getKey().hashCode() : 0);
    result = 31 * result + (getValue() != null ? getValue().hashCode() : 0);
    return result;
  }

}
