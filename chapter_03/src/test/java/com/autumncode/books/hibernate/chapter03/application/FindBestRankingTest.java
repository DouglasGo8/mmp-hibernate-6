package com.autumncode.books.hibernate.chapter03.application;

import com.autumncode.books.hibernate.chapter03.service.HibernateRankingService;
import com.autumncode.books.hibernate.chapter03.service.RankingService;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNull;

@Slf4j
public class FindBestRankingTest {
  private final RankingService service = new HibernateRankingService();

  @Test
  public void findBestForNonexistentSkill() {
    var p = service.findBestPersonFor("no skill");
    assertNull(p);
  }

  @Test
  public void findBestForSkill() {
    service.addRanking("S1", "O1", "Sk1", 6);
    service.addRanking("S1", "O2", "Sk1", 8);
    service.addRanking("S2", "O1", "Sk1", 5);
    service.addRanking("S2", "O2", "Sk1", 7);
    service.addRanking("S3", "O1", "Sk1", 7);
    service.addRanking("S3", "O2", "Sk1", 9);
    // data that should not factor in!
    service.addRanking("S1", "O2", "Sk2", 2);

    var p = service.findBestPersonFor("Sk1");
    assertEquals(p.getName(), "S3");
  }
}
