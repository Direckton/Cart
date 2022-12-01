package Model;
import Model.Product;
import Model.Inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.StreamSupport;

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

    public void addById(List<Product> inventory)
    {
        Scanner sc = new Scanner(System.in);
        int id = 0;
        try
        {
            id = Integer.parseInt(sc.nextLine());
        }
        catch (NumberFormatException e)
        {

        }
        for (Product i : inventory)
        {
            if(i.getId()==id)
            {
                this.addToCart(i);
                break;
            }
        }
    }

    public void removeFromCart(int id)
    {
       for(Product i : cart)
       {
           if(i.getId()==id)
           {
               cart.remove(i);
           }
           if(cart.isEmpty())
           {
               break;
           }
       }
    }
    public void clearCart()
    {
        this.cart.clear();
    }

    public List<Product> returnList()
    {
        return this.cart;
    }
}
