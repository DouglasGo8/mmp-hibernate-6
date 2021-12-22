package com.autumncode.books.hibernate.chapter03.service;

import com.autumncode.books.hibernate.chapter03.model.Person;

import java.util.Map;

/**
 *
 */
public class HibernateRankingService implements RankingService {
  @Override
  public int getRankingFor(String subject, String skill) {
    return 0;
  }

  @Override
  public void addRanking(String subject, String observer, String skill, int ranking) {

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
}
