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
        Product p1 = new Product(1,"test",2.1f);
        inventory.addToInventory(p1);
        cart.addById(inventory.returnList(),1);
        assertEquals(cart.returnList().get(0).getId(),p1.getId());
        assertEquals(cart.returnList().get(0).getName(),p1.getName());
        assertEquals(cart.returnList().get(0).getPrice(),p1.getPrice());
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
        for(int i =0;i< removed.size();i++)
        {
            Product expected = removed.get(i);
            Product actual = cart.returnList().get(i);
            assertEquals(expected.getId(),actual.getId());
            assertEquals(expected.getName(),actual.getName());
            assertEquals(expected.getPrice(),actual.getPrice());
        }

    }

    @Test
    void clearCartTest()
    {
        try{
            cart.clearCart();
        }catch (Exception e)
        {
            System.out.println(e);
        }
        assertTrue(cart.returnList().isEmpty());



    }
}