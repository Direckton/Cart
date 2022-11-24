package Controller;
import Model.*;
import View.*;

import java.io.File;
import java.net.Inet4Address;
import java.util.Scanner;

public class Controller {

    private Cart cart;
    private Inventory inventory;
    private View view;
    private  Database database;
    public Controller()
    {
        this.view = new View();
        this.inventory = new Inventory();
        this.cart = new Cart();
        this.database = new Database();
        this.inventory = this.database.readDbToInventory(this.database.createFile("test.txt"));
        this.view.showOptions();
    }
    public Product insertProduct()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Insert id");
        int id = Integer.parseInt(input.nextLine());
        System.out.println("Insert name");
        String name = input.nextLine();
        System.out.println("Insert price");
        float price = Float.parseFloat(input.nextLine());

        return new Product (id,name,price);
    }

    public void showInventory()
    {
        this.view.showInventory(this.inventory.returnList());
    }
    public void showCart()
    {
        this.view.showCart(this.cart.returnList());
    }

    public boolean userInput()
    {

        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        try
        {
            choice = Integer.parseInt(scanner.nextLine());
        }
        catch (NumberFormatException e)
        {
            System.out.println(e);
        }

        switch (choice)
        {
            case 1:
                view.showInventory(this.inventory.returnList());
                break;
            case 2:
                cart.addById(this.inventory.returnList());
                break;
            case 3:
                view.showCart(this.cart.returnList());
                break;
            case 4:
                System.out.println("Insert Id of the product you want to remove");
                int id = 0;
                try
                {
                    id = Integer.parseInt(scanner.nextLine());
                }
                catch (NumberFormatException e)
                {
                    System.out.println(e);
                }
                this.cart.removeFromCart(id);
                break;
            case 5:
                this.cart.clearCart();
                break;
            case 9:
                return false;
            default:
                System.out.println("Wrong argument, chose form one below:");
                view.showOptions();
        }
        return true;
    }
}
