package com.autumncode.books.hibernate.chapter08;

import com.autumncode.books.hibernate.chapter08.model.Publisher;
import com.autumncode.books.hibernate.util.session.SessionUtil;
import jakarta.persistence.OptimisticLockException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.testng.annotations.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;

@Slf4j
public class DeadlockTest {

  @Test
  @SneakyThrows
  public void showDeadlock() {
    Long publisherAId;
    Long publisherBId;
    //
    try (var session = SessionUtil.getSession()) {
      var tx = session.beginTransaction();
      session.createQuery("delete from Publisher").executeUpdate();
      publisherAId = createPublisher(session, "A");
      publisherBId = createPublisher(session, "B");
      tx.commit();
    }

    var workers = Executors.newFixedThreadPool(2);

    workers.submit(() -> this.updatePublishers("session1", publisherAId, publisherBId));
    workers.submit(() -> this.updatePublishers("session2", publisherAId, publisherBId));

    if (workers.awaitTermination(60, TimeUnit.SECONDS)) {
      workers.shutdownNow();
      if (!workers.awaitTermination(60, TimeUnit.SECONDS)) {
        System.out.println("Executor did not terminate");
      }
    }

    try (var session = SessionUtil.getSession()) {
      var query = session.createQuery("from Publisher p order by p.name", Publisher.class);

      var result = query.list().stream().map(Publisher::getName).collect(Collectors.joining(","));
      assertEquals(result, "session2 A,session2 B");
    }
  }

  private Long createPublisher(Session session, String name) {
    var publisher = new Publisher();
    publisher.setName(name);
    session.persist(publisher);
    return publisher.getId();
  }


  private void updatePublishers(String prefix, Long... ids) {
    try (var session = SessionUtil.getSession()) {
      var tx = session.beginTransaction();
      for (long id : ids) {
        //
        Thread.sleep(300);
        //
        var publisher = session.byId(Publisher.class).load(id);
        publisher.setName(prefix + " " + publisher.getName());
      }
      tx.commit();
    } catch (OptimisticLockException e) {
      log.error("lock exception with prefix {} ", prefix);
    } catch (InterruptedException e) {
    }
  }
}
