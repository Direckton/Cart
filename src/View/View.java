package View;

import Model.Product;
import Model.Cart;
import Model.Inventory;

import java.util.List;

public class View {
    public void printProduct(Product product)
    {

        System.out.print(product.getId()+ " ");
        System.out.print(product.getName()+ " ");
        System.out.println(product.getPrice());

    }
    public void showInventory(Inventory inventory)
    {
        System.out.println("Inventory:");
        for (Product i : inventory.returnList())
        {
            printProduct(i);
        }
    }
    public void showCart(Cart cart)
    {
        System.out.println("Cart:");
        if(cart.returnList().isEmpty())
        {
            System.out.println("The cart is empty");
        }
        else {
            for (Product i : cart.returnList()) {
                printProduct(i);
            }
        }
    }
    public void showOptions()
    {
        System.out.println("Choose one from below:");
        System.out.println("1 - Show inventory");
        System.out.println("2 - Add to cart");
        System.out.println("3 - Show cart");
        System.out.println("4 - remove from cart");
        System.out.println("5 - Clear the cart");
    }
}
