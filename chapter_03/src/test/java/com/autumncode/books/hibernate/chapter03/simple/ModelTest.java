package com.autumncode.books.hibernate.chapter03.simple;

import com.autumncode.books.hibernate.chapter03.model.Person;
import com.autumncode.books.hibernate.chapter03.model.Ranking;
import com.autumncode.books.hibernate.chapter03.model.Skill;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

@Slf4j
public class ModelTest {
  @Test
  public void testModelCreation() {


    var skill = new Skill();
    var subject = new Person();
    var observer = new Person();
    var ranking = new Ranking();

    subject.setName("J.C Smell");

    observer.setName("Drew Lombardo");

    skill.setName("Java");

    ranking.setSubject(subject);
    ranking.setObserver(observer);
    ranking.setSkill(skill);
    ranking.setRanking(8);

    log.info("{}", ranking);


  }
}
