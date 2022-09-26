package com.autumncode.books.hibernate.chapter06.naturalid;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Employee {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Integer id;

  @NaturalId
  Integer section;

  @NaturalId
  Integer department;

  String name;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Employee employee)) return false;

    if (department != null ? !department.equals(employee.getDepartment()) : employee.getDepartment() != null)
      return false;
    if (id != null ? !id.equals(employee.getId()) : employee.getId() != null) return false;
    if (name != null ? !name.equals(employee.getName()) : employee.getName() != null) return false;
    if (section != null ? !section.equals(employee.getSection()) : employee.getSection() != null) return false;
    System.out.println("returning true for equals()");
    return true;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (section != null ? section.hashCode() : 0);
    result = 31 * result + (department != null ? department.hashCode() : 0);
    result = 31 * result + (name != null ? name.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Employee{");
    sb.append("id=").append(id);
    sb.append(", section=").append(section);
    sb.append(", department=").append(department);
    sb.append(", name='").append(name).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
