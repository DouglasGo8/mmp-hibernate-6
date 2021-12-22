package com.autumncode.books.hibernate.chapter03.raw;


import com.autumncode.books.hibernate.chapter03.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PersonTest {

  private SessionFactory factory;


  @BeforeClass
  public void setUp() {

    var registry =
            new StandardServiceRegistryBuilder()
                    .configure()
                    .build();

    factory = new MetadataSources(registry)
            .buildMetadata()
            .buildSessionFactory();
  }

  @AfterMethod
  public void shutdown() {
    factory.close();
  }

  @Test
  public void testSavePerson() {

    try (var session = factory.openSession()) {

      var tx = session.beginTransaction();
      var person = new Person();

      person.setName("J. C. Smell");
      session.save(person);
      tx.commit();
    }
  }

  private Person findPerson(Session session, String name) {
    var query = session.createQuery("from Person p where p.name=:name", Person.class);

    query.setMaxResults(1);
    query.setParameter("name", name);

    return query.uniqueResult();
  }

  private Person savePerson(Session session, String name) {
    var person = this.findPerson(session, name);
    if (null == person) {
      person = new Person();
      person.setName(name);
      session.save(person);
    }
    return person;
  }
}
