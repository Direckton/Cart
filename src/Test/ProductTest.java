package Test;

import Model.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void ConstructorTest()
    {
        Product product = new Product(1,"test",1);
        assertEquals(1,product.getId());
        assertEquals("test",product.getName());
        assertEquals(1,product.getPrice());
    }
}