package com.autumncode.books.hibernate.chapter03.application;

import com.autumncode.books.hibernate.chapter03.service.HibernateRankingService;
import com.autumncode.books.hibernate.chapter03.service.RankingService;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

/**
 * @author dougdb
 */
@Slf4j
public class FindAllRankingsTest {

  private final RankingService service = new HibernateRankingService();


  @Test
  public void findAllRankingsEmptySet() {
    assertEquals(service.getRankingFor("Nobody", "Java"), 0);
    assertEquals(service.getRankingFor("Nobody", "Python"), 0);

    var rankings = service.findRankingsFor("Nobody");

    assertEquals(rankings.size(), 0);
  }

  @Test
  public void findAllRankings() {

    assertEquals(service.getRankingFor("Somebody", "Java"), 0);
    assertEquals(service.getRankingFor("Somebody", "Python"), 0);

    service.addRanking("Somebody", "Nobody", "Java", 9);
    service.addRanking("Somebody", "Nobody", "Java", 7);
    service.addRanking("Somebody", "Nobody", "Python", 7);
    service.addRanking("Somebody", "Nobody", "Python", 5);

    var rankings = service.findRankingsFor("Somebody");

    assertEquals(rankings.size(), 2);
    assertNotNull(rankings.get("Java"));
    assertEquals(rankings.get("Java"), new Integer(8));
    assertNotNull(rankings.get("Python"));
    assertEquals(rankings.get("Python"), new Integer(6));

  }

}
