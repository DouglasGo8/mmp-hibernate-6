package com.autumncode.books.hibernate.chapter03.application;

import com.autumncode.books.hibernate.chapter03.service.HibernateRankingService;
import com.autumncode.books.hibernate.chapter03.service.RankingService;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

@Slf4j
public class FindAverageRankingTest {

  private final RankingService service = new HibernateRankingService();

  @Test
  public void validateRankingAverage() {
    //
    service.addRanking("A", "B", "C", 4);
    service.addRanking("A", "B", "C", 5);
    service.addRanking("A", "B", "C", 6);
    //
    assertEquals(service.getRankingFor("A", "C"), 5);
    service.addRanking("A", "B", "C", 7);
    service.addRanking("A", "B", "C", 8);
    //
    assertEquals(service.getRankingFor("A", "C"), 6);
  }

}
