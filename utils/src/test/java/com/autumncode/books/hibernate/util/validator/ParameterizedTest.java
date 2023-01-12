package com.autumncode.books.hibernate.util.validator;

import com.autumncode.books.hibernate.util.session.SessionUtil;
import com.autumncode.books.hibernate.util.model.ValidatedPerson;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.fail;

public class ParameterizedTest {

  @DataProvider
  Object[][] provider() {
    return new Object[][]{
            {"Johnny", "McYoungster", 15, false},
            {"Johnny", "McYoungster", 12, true},
            {"J", "McYoungster", 14, true},
            {"Johnny", "M", 14, true},
            {"Johnny", null, 14, true},
    };
  }

  private ValidatedPerson persist(ValidatedPerson person) {
    try (var session = SessionUtil.getSession()) {
      var tx = session.beginTransaction();
      session.persist(person);
      tx.commit();
    }
    return person;
  }


  @Test(dataProvider = "provider")
  void testValidations(String fname, String lname, Integer age, boolean expectException) {
    try {
      var builder = ValidatedPerson.builder().age(age).fname(fname);
      if (null != lname) builder.lname(lname);
      this.persist(builder.build());
      if (expectException) fail("should have caught an exception");
    } catch (Exception e) {
      if (!expectException) fail("expected an exception");
    }
  }
}
