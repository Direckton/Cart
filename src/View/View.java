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
    public void showInventory(List<Product> inventory)
    {
        if(inventory.isEmpty())
        {
            System.out.println("Inventory is empty");
        }
        else {
            System.out.println("Inventory:");
            System.out.println("ID NAME PRICE");
            for (Product i : inventory) {
                printProduct(i);
            }
        }
    }
    public void showCart(List<Product> cart)
    {
        System.out.println("Cart:");
        if(cart.isEmpty())
        {
            System.out.println("The cart is empty");
        }
        else {
            System.out.println("ID NAME PRICE");

            for (Product i : cart) {
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
        System.out.println("4 - Remove from cart");
        System.out.println("5 - Clear the cart");
        System.out.println("8 - Enter admin mode");
        System.out.println("9 - Exit");
    }
    public void showAdminOptions()
    {
        System.out.println("Choose one from below:");
        System.out.println("1 - Add item to inventory");
        System.out.println("2 - Remove item form inventory");
        System.out.println("3 - Change existing item");
        System.out.println("4 - Change amount in inventory");
        System.out.println("9 - Exit");
    }
}
