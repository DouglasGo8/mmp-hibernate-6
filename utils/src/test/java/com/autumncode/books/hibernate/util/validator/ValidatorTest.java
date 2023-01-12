package com.autumncode.books.hibernate.util.validator;

import com.autumncode.books.hibernate.util.session.SessionUtil;
import com.autumncode.books.hibernate.util.model.UnvalidatedSimplePerson;
import com.autumncode.books.hibernate.util.model.ValidatedPerson;
import jakarta.validation.ConstraintViolationException;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.fail;

public class ValidatorTest {

  private ValidatedPerson persist(ValidatedPerson person) {
    try (var session = SessionUtil.getSession()) {
      var tx = session.beginTransaction();
      session.persist(person);
      tx.commit();
    }
    return person;
  }

  @Test
  public void createUnvalidatedUnderagePerson() {
    Long id = null;
    try (var session = SessionUtil.getSession()) {
      var transaction = session.beginTransaction();
      var person = new UnvalidatedSimplePerson();
      person.setAge(12); // underage for system
      person.setFname("Johnny");
      person.setLname("McYoungster");
// this succeeds because the UnvalidatedSimplePerson
// has no validation in place.
      session.persist(person);
      id = person.getId();
      transaction.commit();
    }
  }

  @Test
  public void createValidPerson() {
    this.persist(ValidatedPerson.builder().age(15).fname("Johnny").lname("McYoungster").build());
  }

  @Test(expectedExceptions = ConstraintViolationException.class)
  public void createValidatedUnderagePerson() {
    persist(ValidatedPerson.builder()
            .age(12)
            .fname("Johnny")
            .lname("McYoungster").build());
    fail("Should have failed validation");
  }

  @Test(expectedExceptions = ConstraintViolationException.class)
  public void createValidatedPoorFNamePerson2() {
    persist(ValidatedPerson.builder()
            .age(14)
            .fname("J")
            .lname("McYoungster2").build());
    fail("Should have failed validation");
  }

  @Test(expectedExceptions = ConstraintViolationException.class)
  public void createValidatedNoFNamePerson() {
    persist(ValidatedPerson.builder()
            .age(14)
            .lname("McYoungster2").build());
    fail("Should have failed validation");
  }
}
