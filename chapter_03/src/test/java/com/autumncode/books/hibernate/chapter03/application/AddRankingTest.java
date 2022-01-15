package com.autumncode.books.hibernate.chapter03.application;

import com.autumncode.books.hibernate.chapter03.service.HibernateRankingService;
import com.autumncode.books.hibernate.chapter03.service.RankingService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

/**
 * @author dougdb
 */
public class AddRankingTest {

  private RankingService service;

  @BeforeClass
  public void setUp() {
    this.service = new HibernateRankingService();
  }

  @Test
  public void addRanking() {
    service.addRanking("J. C. Smell", "Drew Lombardo", "Mule", 8);
    assertEquals(service.getRankingFor("J. C. Smell", "Mule"), 8);
  }
}
