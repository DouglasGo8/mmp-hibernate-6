package com.autumncode.books.hibernate.chapter03.application;

import com.autumncode.books.hibernate.chapter03.service.HibernateRankingService;
import com.autumncode.books.hibernate.chapter03.service.RankingService;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

@Slf4j
public class UpdateRankingTest {

  private final RankingService service = new HibernateRankingService();

  private static final String SCOTT = "Scotball Most";
  private static final String GENE = "Gene Showrama";
  private static final String CEYLON = "Ceylon";

  @Test
  public void updateExistingRanking() {
    service.addRanking(GENE, SCOTT, CEYLON, 6);
    assertEquals(service.getRankingFor(GENE, CEYLON), 6);
    service.updateRanking(GENE, SCOTT, CEYLON, 7);
    assertEquals(service.getRankingFor(GENE, CEYLON), 7);
  }

  @Test
  public void updateNonexistentRanking() {
    assertEquals(service.getRankingFor(SCOTT, CEYLON), 0);
    service.updateRanking(SCOTT, GENE, CEYLON, 7);
    assertEquals(service.getRankingFor(SCOTT, CEYLON), 7);
  }
}
