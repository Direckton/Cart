package View;

import Model.Product;

import java.util.List;

/**
 * Class used for handling messages shown to user
 */
public class View {
    /**
     * Puts out formatted product data onto console
     * @param product object shown
     */
    public void printProduct(Product product)
    {
        System.out.print(product.getId()+ " ");
        System.out.print(product.getName()+ " ");
        System.out.printf("%.2f \n",product.getPrice());

    }

    /**
     * Shows list of inventory
     * @param inventory shown inventory
     */
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

    /**
     * Shows list of the cart
     * @param cart shown cart
     */
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

    /**
     * Show available options to the user
     */
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

    /**
     * Show available options to the admin
     */
    public void showAdminOptions()
    {
        System.out.println("Choose one from below:");
        System.out.println("1 - Add item to inventory");
        System.out.println("2 - Remove item form inventory");
        System.out.println("3 - Change existing item");
        System.out.println("9 - Exit");
    }

    /**
     * prints simple string message
     * @param message string to be printed
     */
    public void printMessage(String message)
    {
        System.out.println(message);
    }

    /**
     * prints numeric format error
     * @param e error
     */
    public void printNumFormatExc(NumberFormatException e)
    {
        System.err.println(e);
    }

    /**
     * Prints user defined exception
     * @param e exception
     */
    public void printException(Exception e)
    {
        System.err.println(e);
    }
}
