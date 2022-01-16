package com.autumncode.books.hibernate.chapter03.application;

import com.autumncode.books.hibernate.chapter03.service.HibernateRankingService;
import com.autumncode.books.hibernate.chapter03.service.RankingService;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

@Slf4j
public class RemoveRankingTest {

  private final RankingService service = new HibernateRankingService();

  @Test
  public void removeRanking() {
    service.addRanking("R1", "R2", "RS1", 8);
    assertEquals(service.getRankingFor("R1", "RS1"), 8);
    service.removeRanking("R1", "R2", "RS1");
    assertEquals(service.getRankingFor("R1", "RS1"), 0);
  }

  @Test
  public void removeNonexistentRanking() {
    service.removeRanking("R3", "R4", "RS2");
  }
}
