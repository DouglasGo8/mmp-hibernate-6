package com.autumncode.books.hibernate.util.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.BitSet;

@Data
@Slf4j
@Entity(name = "LifecycleThing")
public class LifecycleThing {
  public static BitSet lifecycleCalls = new BitSet();
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Integer id;
  @Column
  String name;

  @PostLoad
  public void postLoad() {
    this.logger("postLoad", 0);
  }

  @PrePersist
  public void prePersist() {
    this.logger("prePersist", 1);
  }

  @PostPersist
  public void postPersist() {
    this.logger("postPersist", 2);
  }

  @PreUpdate
  public void preUpdate() {
    this.logger("preUpdate", 3);
  }

  @PostUpdate
  public void postUpdate() {
    this.logger("postUpdate", 4);
  }

  @PreRemove
  public void preRemove() {
    this.logger("preRemov", 5);
  }

  @PostRemove
  public void postRemote() {
    this.logger("postRemote", 6);
  }

  private void logger(String method, int index) {
    lifecycleCalls.set(index, true);
    log.info("{}: {} {}", method, this.getClass().getSimpleName(), this);
  }


}
