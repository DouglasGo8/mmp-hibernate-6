import com.autumncode.books.hibernate.chapter09.model.Supplier;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class TestNamedQuery extends TestBase {

  @Test
  public void testNamedQuery() {
    var query = super.session.createNamedQuery("supplier.findAll", Supplier.class);

    var supplier = query.list();
    assertEquals(supplier.size(), 2);
  }
}
