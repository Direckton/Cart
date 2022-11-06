package Model;
import Model.Product;
import Model.Inventory;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> cart = new ArrayList<>();

    public Cart()
    {
        cart.clear();
    }
    public void addToCart(Product product)
    {
        this.cart.add(product);
    }

    public void removeFromCart(int id)
    {
       for(Product i : cart)
       {
           if(i.getId()==id)
           {
               cart.remove(i);
           }
       }
    }
    public void showCart()
    {
        if(cart.isEmpty())
        {
            System.out.println("The cart is empty");
        }
        else {
            for (Product i : this.cart) {
                i.printProduct(i);
            }
        }
    }
    public void clearCart()
    {
        this.cart.clear();
    }
}
