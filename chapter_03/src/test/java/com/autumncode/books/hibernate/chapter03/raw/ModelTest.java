package com.autumncode.books.hibernate.chapter03.raw;

import com.autumncode.books.hibernate.chapter03.model.Person;
import com.autumncode.books.hibernate.chapter03.model.Ranking;
import com.autumncode.books.hibernate.chapter03.model.Skill;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

@Slf4j
public class ModelTest {

  @Test
  public void testModelCreation() {

    var subject = new Person();
    subject.setName("J. C. Smell");

    var observer = new Person();
    observer.setName("Drew Lombardo");

    var skill = new Skill();
    skill.setName("Java");

    var ranking = new Ranking();

    ranking.setSubject(subject);
    ranking.setObserver(observer);
    ranking.setSkill(skill);

    ranking.setRanking(8);

    log.info("{}", ranking);

  }
}
