package com.autumncode.books.hibernate.chapter04;


import com.autumncode.books.hibernate.chapter04.model.Book;
import com.autumncode.books.hibernate.chapter04.model.Library;
import com.autumncode.books.hibernate.util.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNull;

@Slf4j
public class OrphanRemovalTest {

  @Test
  public void orphanRemovalTest() {
    long id = this.createLibrary();
    //
    try (var session = SessionUtil.getSession()) {
      var tx = session.beginTransaction();
      var library = session.find(Library.class, id);
      assertEquals(library.getBooks().size(), 3);
      //
      library.getBooks().remove(0);
      assertEquals(library.getBooks().size(), 2);

      tx.commit();
    }

    try (var session = SessionUtil.getSession()) {
      var tx = session.beginTransaction();
      //
      var l2 = session.find(Library.class, id);
      assertEquals(l2.getBooks().size(), 2);
      //
      var books = session.createQuery("from Book b", Book.class).list();
      assertEquals(books.size(), 2);

      tx.commit();
    }
  }

  @Test
  public void deleteLibrary() {
    long id = createLibrary();
    try (var session = SessionUtil.getSession()) {
      var tx = session.beginTransaction();
      var library = session.find(Library.class, id);
      assertEquals(library.getBooks().size(), 3);
      session.remove(library);
      tx.commit();
    }

    try (var session = SessionUtil.getSession()) {
      var tx = session.beginTransaction();
      var library = session.get(Library.class, id);
      assertNull(library);
      //
      var books = session.createQuery("from Book b", Book.class).list();
      assertEquals(books.size(), 0);
    }
  }

  private long createLibrary() {
    Library library = null;

    try (var session = SessionUtil.getSession()) {

      var tx = session.beginTransaction();

      library = new Library();
      library.setName("orphanLib");
      session.persist(library);
      //
      var book = new Book();
      book.setLibrary(library);
      book.setTitle("Apache Camel in Action 3rd");
      session.persist(book);
      library.getBooks().add(book);
      //
      book = new Book();
      book.setLibrary(library);
      book.setTitle("Reactive System with Java and Quarkus");
      session.persist(book);
      library.getBooks().add(book);
      //
      book = new Book();
      book.setLibrary(library);
      book.setTitle("ReactJs v17: Complete Reference");
      session.persist(book);
      library.getBooks().add(book);

      tx.commit();

      return library.getId();
    }
  }
}
