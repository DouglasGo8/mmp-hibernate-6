package com.autumncode.books.hibernate.chapter03.service;

import com.autumncode.books.hibernate.chapter03.model.Person;

import java.util.Map;

/**
 * @author dougdb
 */
public interface RankingService {

  int getRankingFor(String subject, String skill);

  void addRanking(String subject, String observer, String skill, int ranking);

  //end::preamble[]
  void updateRanking(String subject, String observer, String skill, int ranking);

  void removeRanking(String subject, String observer, String skill);

  Map<String, Integer> findRankingsFor(String subject);

  Person findBestPersonFor(String skill);
}
