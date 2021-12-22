package com.autumncode.books.hibernate.chapter03.app;

import com.autumncode.books.hibernate.chapter03.service.HibernateRankingService;
import com.autumncode.books.hibernate.chapter03.service.RankingService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AddRankingTest {

  private RankingService service;

  @BeforeClass
  public void setUp() {
    this.service = new HibernateRankingService();
  }

  @Test
  public void addRanking() {
    service.addRanking("J. C. Smell", "Drew Lombardo", "Mule", 8);
  }
}
