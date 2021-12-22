package com.autumncode.books.hibernate.chapter03.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Ranking {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne
  private Skill skill;

  @ManyToOne
  private Person subject;
  @ManyToOne
  private Person observer;

  @Column
  private Integer ranking;


  @Override
  public String toString() {
    return "Ranking{" +
            "subject=" + subject +
            ", observer=" + observer +
            ", skill=" + skill +
            ", ranking=" + ranking +
            '}';
  }

}
