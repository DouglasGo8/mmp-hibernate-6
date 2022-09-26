package com.autumncode.books.hibernate.chapter06.naturalid;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class SimpleNaturalIdEmployee {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Integer id;

  @NaturalId
  Integer badge;

  String name;

  @Column(scale = 2, precision = 5, nullable = false)
  double royalty;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof SimpleNaturalIdEmployee that)) return false;

    if (badge != null ? !badge.equals(that.badge) : that.badge != null) return false;
    if (id != null ? !id.equals(that.id) : that.id != null) return false;
    if (name != null ? !name.equals(that.name) : that.name != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (badge != null ? badge.hashCode() : 0);
    result = 31 * result + (name != null ? name.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "SimpleNaturalIdEmployee{" +
            "id=" + id +
            ", badge=" + badge +
            ", name='" + name + '\'' +
            ", royalty=" + royalty +
            '}';
  }
}
