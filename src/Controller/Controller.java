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
        int id =0;
        String name = "";
        float price = 0;
        try
        {
            System.out.println("Insert id");
            id = Integer.parseInt(input.nextLine());
            System.out.println("Insert name");
            name = input.nextLine();
            System.out.println("Insert price");
            price = Float.parseFloat(input.nextLine());

        }
        catch (NumberFormatException e)
        {
            System.out.println(e);
        }
        boolean idCheck= true;
        while (idCheck)
        {
            if(this.inventory.checkId(id))
            {
                idCheck = false;
                return new Product (id,name,price);

            }
            else
            {
                System.out.println("Id already taken, please insert new one");
                try
                {
                    id = Integer.parseInt(input.nextLine());
                }
                catch (NumberFormatException e)
                {
                    id = 0;
                    System.out.println(e);
                }
            }

        }
        return null;
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
            case 8:
                boolean exitAdmin = true; //controls admin loop
                while (exitAdmin)
                {
                    exitAdmin = adminInput();
                }
                break;
            case 9:
                //TODO
                //add saving inventory to file
                //?add saving cart?
                return false;
            default:
                System.out.println("Wrong argument, chose form one below:");
                view.showOptions();
        }
        return true;
    }
    public boolean adminInput()
    {
        System.out.println("Admin console");
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
        switch(choice)
        {
            case 1:
            this.inventory.addToInventory(insertProduct());
            break;
            case 2:
                boolean loop = true;
                String input="";
                int id=0;

                while (loop)
                {
                    id = 0;
                    input = "";
                    input = scanner.nextLine();
                    if(input.equals("x"))
                    {
                        loop = false;
                        return false;
                    }
                    try
                    {
                        id = Integer.parseInt(input);
                    }
                    catch(NumberFormatException e)
                    {
                        id = 0;
                        System.out.println(e);
                    }
                    if(inventory.checkId(id))
                    {
                        this.inventory.removeFromInventory(id);
                    }
                    else
                    {
                        System.out.println("Wrong Id!");
                        System.out.println("Insert Id again or press X+Enter to exit admin mode");
                    }
                }
            default:
                break;



        }
        return true;
    }
}
