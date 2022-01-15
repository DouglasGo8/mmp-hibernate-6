package com.autumncode.books.hibernate.chapter03.service;

import com.autumncode.books.hibernate.chapter03.model.Person;
import com.autumncode.books.hibernate.chapter03.model.Ranking;
import com.autumncode.books.hibernate.chapter03.model.Skill;
import com.autumncode.books.hibernate.util.SessionUtil;
import org.hibernate.Session;

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
      addRanking(session, subjectName, observerName, skillName, rank);
      tx.commit();
    }
  }

  @Override
  public void updateRanking(String subject, String observer, String skill, int ranking) {

  }

  @Override
  public void removeRanking(String subject, String observer, String skill) {

  }

  @Override
  public Map<String, Integer> findRankingsFor(String subject) {
    return null;
  }

  @Override
  public Person findBestPersonFor(String skill) {
    return null;
  }


  private void addRanking(Session session, String subjectName, String observerName, String skillName, int rank) {
    //
    var subject = savePerson(session, subjectName);
    var observer = savePerson(session, observerName);
    var skill = saveSkill(session, skillName);

    var ranking = new Ranking();
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

    final var stats = query
            .list()
            .stream()
            .collect(Collectors.summarizingInt(Ranking::getRanking));

    return (int) stats.getAverage();
  }

}
