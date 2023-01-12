package com.autumncode.books.hibernate.util.session;


import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 *
 */
@Slf4j
public class SessionUtil {

  private SessionFactory factory;
  private static final SessionUtil instance = new SessionUtil();
  private static final String CONFIG_NAME = "/configuration.properties";


  private SessionUtil() {
    initialize();
  }


  public static Session getSession() {
    return getInstance().factory.openSession();
  }

  private void initialize() {
    log.info("reloading Factory");

    var registry = new StandardServiceRegistryBuilder().configure().build();
    factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

  }

  private static SessionUtil getInstance() {
    return instance;
  }

  public static void doWithSession(final Consumer<Session> command) {
    try (var session = getSession()) {
      var tx = session.beginTransaction();
      command.accept(session);
      if (tx.isActive() && !tx.getRollbackOnly()) {
        tx.commit();
      } else {
        tx.rollback();
      }
    }
  }

  public static <T> T returnFromSession(Function<Session, T> command) {
    try (var session = getSession()) {
      Transaction tx = null;
      try {
        tx = session.beginTransaction();
        return command.apply(session);
      } catch (Exception e) {
        throw new RuntimeException();
      } finally {
        if (tx != null) {
          if (tx.isActive() &&
                  !tx.getRollbackOnly()) {
            tx.commit();
          } else {
            tx.rollback();
          }
        }
      }
    }
  }

}
