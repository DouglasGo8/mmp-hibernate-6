package com.autumncode.books.hibernate.chapter01;

import com.autumncode.books.hibernate.chapter01.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Slf4j
public class TestMain {


  private SessionFactory factory = null;

  @BeforeClass
  public void setup() {
    var registry = new StandardServiceRegistryBuilder().configure().build();
    factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
  }

  private Message saveMessage(String text) {
    var message = new Message(text);
    try (var session = factory.openSession()) {
      var tx = session.beginTransaction();
      session.persist(message);
      tx.commit();
    }

    return message;
  }

  @Test
  public void readMessage() {
    var savedMessage = saveMessage("My Message");
    try (var session = factory.openSession()) {
      var list = session.createQuery("from Message", Message.class).list();
      assertEquals(list.size(), 1);
      list.forEach(m -> log.info("{}", m));
      assertEquals(list.get(0), savedMessage);
    }
  }
}
