package Test;

import Model.Cart;
import Model.Inventory;
import Model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    private Cart cart;

    @BeforeEach
    public void setUp()
    {
        cart = new Cart();
        Product p1 = new Product(1,"test",2.1f);
        Product p2 = new Product(2,"test2",3.1f);
        Product p3 = new Product(3,"test3",4.1f);
        cart.addToCart(p1);
        cart.addToCart(p2);
        cart.addToCart(p3);
    }

    @Test
    void addToCartTest()
    {
        Product p1 = new Product(1,"test",1);
        cart.addToCart(p1);
        List<Product> productList = cart.returnList();
        assertSame(productList.get(3), p1 );
    }

    @Test
    void addByIdTest()
    {
        Inventory inventory = new Inventory();
        Product p1 = new Product(1,"test",2.3f);
        inventory.addToInventory(p1);
        cart.addById(inventory.returnList(),1);
        assertSame(cart.returnList().get(0),p1);
    }

    @Test
    void removeFromCartTest()
    {
        try {
            cart.removeFromCart(2);
        }catch (Exception e)
        {
            System.out.println(e);
        }
        List<Product> removed = new ArrayList<>();
        Product p1 = new Product(1,"test",2.1f);
        Product p3 = new Product(3,"test3",4.1f);
        removed.add(p1);
        removed.add(p3);
        assertEquals(cart.returnList(),removed);

    }

    @Test
    void clearCartTest()
    {

    }
}