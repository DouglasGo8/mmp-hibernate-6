package com.autumncode.books.hibernate.chapter03.service;

import com.autumncode.books.hibernate.chapter03.model.Person;
import com.autumncode.books.hibernate.chapter03.model.Ranking;
import com.autumncode.books.hibernate.chapter03.model.Skill;
import com.autumncode.books.hibernate.util.SessionUtil;
import org.hibernate.Session;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author dougdb
 */
public class HibernateRankingService implements RankingService {

  @Override
  public int getRankingFor(String subject, String skill) {

    try (var session = SessionUtil.getSession()) {
      var tx = session.beginTransaction();
      int average = this.getRankingFor(session, subject, skill);
      tx.commit();
      return average;
    }

  }

  @Override
  public void addRanking(String subjectName, String observerName, String skillName, int rank) {
    try (var session = SessionUtil.getSession()) {
      var tx = session.beginTransaction();
      this.addRanking(session, subjectName, observerName, skillName, rank);
      tx.commit();
    }
  }

  @Override
  public void updateRanking(String subject, String observer, String skill, int rank) {
    try (var session = SessionUtil.getSession()) {
      var tx = session.beginTransaction();
      var ranking = findRanking(session, subject, observer, skill);
      if (null == ranking) {
        addRanking(session, subject, observer, skill, rank);
      } else {
        ranking.setRanking(rank);
      }
      tx.commit();
    }
  }

  @Override
  public void removeRanking(String subject, String observer, String skill) {
    try (var session = SessionUtil.getSession()) {
      var tx = session.beginTransaction();
      this.removeRanking(session, subject, observer, skill);
      tx.commit();
    }
  }

  @Override
  public Map<String, Integer> findRankingsFor(String subject) {

    try (var session = SessionUtil.getSession()) {
      return this.findRankingsFor(session, subject);
    }

  }

  @Override
  public Person findBestPersonFor(String skill) {
    Person person = null;
    try (var session = SessionUtil.getSession()) {
      var tx = session.beginTransaction();
      person = findBestPersonFor(session, skill);
      tx.commit();
    }
    return person;
  }


  private void addRanking(Session session, String subjectName, String observerName, String skillName, int rank) {
    //
    var ranking = new Ranking();
    var subject = savePerson(session, subjectName);
    var observer = savePerson(session, observerName);
    var skill = saveSkill(session, skillName);
    //
    ranking.setSubject(subject);
    ranking.setObserver(observer);
    ranking.setSkill(skill);
    ranking.setRanking(rank);
    //
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

  private Skill saveSkill(Session session, String skillName) {
    var skill = findSkill(session, skillName);
    if (null == skill) {
      skill = new Skill();
      skill.setName(skillName);
      session.save(skill);
    }
    return skill;
  }

  private Person findPerson(Session session, String name) {
    var query = session.createQuery("from Person p where p.name=:name", Person.class);
    query.setParameter("name", name);
    return query.uniqueResult();
  }

  private Skill findSkill(Session session, String name) {
    var query = session.createQuery("from Skill s where s.name=:name", Skill.class);
    query.setParameter("name", name);
    return query.uniqueResult();
  }

  private int getRankingFor(Session session, String subject, String skill) {

    final var sql = "from Ranking r where r.subject.name=:name and r.skill.name=:skill";
    final var query = session.createQuery(sql, Ranking.class);
    //
    query.setParameter("name", subject);
    query.setParameter("skill", skill);

    final var stats = query.list()
            .stream()
            .collect(Collectors.summarizingInt(Ranking::getRanking));
    //
    return (int) stats.getAverage();
  }

  private Map<String, Integer> findRankingsFor(Session session, String subject) {
    Map<String, Integer> results = new HashMap<>();
    var sql = "from Ranking r where r.subject.name=:name order by r.skill.name";
    var query = session.createQuery(sql, Ranking.class);
    query.setParameter("name", subject);
    var rankings = query.list();
    String lastSkillName = "";
    int sum = 0;
    int count = 0;

    for (Ranking r : rankings) {
      if (!lastSkillName.equals(r.getSkill().getName())) {
        sum = 0;
        count = 0;
        lastSkillName = r.getSkill().getName();
      }
      sum += r.getRanking();
      count++;
      results.put(lastSkillName, sum / count);
    }
    return results;
  }

  private Person findBestPersonFor(Session session, String skill) {
    // hbm query
    var sql = "select r.subject.name, avg(r.ranking) " +
            "from Ranking r where r.skill.name=:skill " +
            "group by r.subject.name " +
            "order by avg(r.ranking) desc";
    //
    var query = session.createQuery(sql, Object[].class);
    query.setParameter("skill", skill);
    var result = query.list();
    //
    if (result.size() > 0) {
      var row = result.get(0);
      String personName = row[0].toString();
      return findPerson(session, personName);
    }
    //
    return null;
  }

  private void removeRanking(Session session, String subject, String observer, String skill) {
    var ranking = this.findRanking(session, subject, observer, skill);
    if (null != ranking) {
      session.delete(ranking);
    }
  }

  private Ranking findRanking(Session session, String subject, String observer, String skill) {
    var sql = "from Ranking r where r.subject.name=:subject and r.observer.name=:observer and r.skill.name=:skill";
    var query = session.createQuery(sql, Ranking.class);
    query.setParameter("subject", subject);
    query.setParameter("observer", observer);
    query.setParameter("skill", skill);
    return query.uniqueResult();
  }
}
