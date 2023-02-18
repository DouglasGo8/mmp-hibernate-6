import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class TestNamedQuery extends TestBase {

  @Test
  public void testNamedQuery() {
    var query = super.session.createNamedQuery("supplier.findAll");
    var supplier = query.list();
    assertEquals(supplier.size(), 2);
  }
}
