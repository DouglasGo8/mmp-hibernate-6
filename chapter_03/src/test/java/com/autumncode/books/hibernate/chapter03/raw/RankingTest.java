package com.autumncode.books.hibernate.chapter03.raw;

import com.autumncode.books.hibernate.chapter03.model.Person;
import com.autumncode.books.hibernate.chapter03.model.Ranking;
import com.autumncode.books.hibernate.chapter03.model.Skill;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

@Slf4j
public class RankingTest {

  private SessionFactory factory;

  @BeforeMethod
  public void setUp() {

    final var registry = new StandardServiceRegistryBuilder().configure().build();
    factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
  }

  @AfterMethod
  public void shutdown() {
    factory.close();
  }

  @Test
  public void testSaveRanking() {
    try (var session = factory.openSession()) {
      final var tx = session.beginTransaction();
      final var subject = savePerson(session, "J. C. Smell");
      final var observer = savePerson(session, "Drew Lombardo");
      final var skill = saveSkill(session, "Java");

      final var ranking = new Ranking();

      ranking.setSkill(skill);
      ranking.setSubject(subject);
      ranking.setObserver(observer);
      ranking.setRanking(8);
      session.save(ranking);

      tx.commit();
    }
  }

  @Test
  public void testRankings() {
    this.populateRankingData();

    try (var session = factory.openSession()) {
      final var tx = session.beginTransaction();
      final var sql = "from Ranking r where r.subject.name =:name and r.skill.name=:skill";
      final var query = session.createQuery(sql, Ranking.class);

      query.setParameter("name", "J. C. Smell");
      query.setParameter("skill", "Java");

      var stats = query.list()
              .stream()
              .collect(Collectors.summarizingInt(Ranking::getRanking));
      //
      long count = stats.getCount();
      int average = (int) stats.getAverage();

      assertEquals(count, 3);
      assertEquals(average, 7);
      //
      tx.commit();
      session.close();
    }

  }

  @Test
  public void changeRanking() {
    this.populateRankingData();
    try (var session = factory.openSession()) {
      var tx = session.beginTransaction();
      var query = session.createQuery("from Ranking r where r.subject.name=:subject " +
              "and r.observer.name=:observer and r.skill.name=:skill", Ranking.class);
      query.setParameter("subject", "J. C. Smell");
      query.setParameter("observer", "Gene Showrama");
      query.setParameter("skill", "Java");

      var ranking = query.uniqueResult();

      assertNotNull(ranking, "Could not find matching ranking");
      ranking.setRanking(9);
      //
      tx.commit();
    }
    assertEquals(getAverage("J. C. Smell", "Java"), 8);
  }

  @Test
  public void removeRanking() {
    this.populateRankingData();
    try (var session = factory.openSession()) {
      var tx = session.beginTransaction();
      var ranking = findRanking(session, "J. C. Smell", "Gene Showrama", "Java");
      assertNotNull(ranking, "Ranking not Found");
      session.delete(ranking);
      tx.commit();
    }
    assertEquals(getAverage("J. C. Smell", "Java"), 7);
  }

  private Ranking findRanking(Session session, String subject, String observer, String skill) {
    var query = session.createQuery("from Ranking r where r.subject.name=:subject " +
            "and r.observer.name=:observer and r.skill.name=:skill", Ranking.class);
    query.setParameter("subject", subject);
    query.setParameter("observer", observer);
    query.setParameter("skill", skill);
    return query.uniqueResult();
  }

  private int getAverage(String subject, String skill) {
    try (var session = factory.openSession()) {
      var tx = session.beginTransaction();
      var query = session.createQuery("from Ranking r where r.subject.name=:name " +
              "and r.skill.name=:skill", Ranking.class);
      query.setParameter("name", subject);
      query.setParameter("skill", skill);
      //
      var stats = query.list()
              .stream()
              .collect(Collectors.summarizingInt(Ranking::getRanking));

      int average = (int) stats.getAverage();
      log.info("Average is...{}", average);
      //
      tx.commit();
      //
      return average;
    }
  }

  private void populateRankingData() {
    try (final var session = factory.openSession()) {
      final var tx = session.beginTransaction();
      createData(session, "J. C. Smell", "Gene Showrama", "Java", 6);
      createData(session, "J. C. Smell", "Scottball Most", "Java", 7);
      createData(session, "J. C. Smell", "Drew Lombardo", "Java", 8);
      tx.commit();
    }
  }

  private void createData(Session session, String subjectName, String observerName, String skillName, int rank) {
    final var subject = savePerson(session, subjectName);
    final var observer = savePerson(session, observerName);
    final var skill = saveSkill(session, skillName);
    final var ranking = new Ranking();

    ranking.setSkill(skill);
    ranking.setSubject(subject);
    ranking.setObserver(observer);
    ranking.setRanking(rank);
    session.save(ranking);
  }

  private Person savePerson(Session session, String name) {
    var person = findPerson(session, name);
    if (null == person) {
      person = new Person();
      person.setName(name);
      session.save(person);
    }

    return person;
  }

  private Person findPerson(Session session, String name) {
    final var query = session.createQuery("from Person p where p.name=:name", Person.class);
    query.setMaxResults(1);
    query.setParameter("name", name);
    return query.uniqueResult();
  }

  private Skill saveSkill(Session session, String skillName) {
    var skill = findSkill(session, skillName);
    if (null == skill) {
      skill = new Skill();
      skill.setName(skillName);
      session.save(skill);
    }
    return skill;
  }

  private Skill findSkill(Session session, String skillName) {
    final var query = session.createQuery("from Skill s where s.name=:name", Skill.class);
    query.setMaxResults(1);
    query.setParameter("name", skillName);
    return query.uniqueResult();
  }
}
