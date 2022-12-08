package Controller;
import Model.*;
import View.*;

import java.util.Scanner;


public class Controller {
    private Cart cart;
    private Inventory inventory;
    private View view;
    private  Database database;
    private UserInput userInput;

    public Controller()
    {
        this.view = new View();
        this.inventory = new Inventory();
        this.cart = new Cart();
        this.database = new Database();
        this.inventory = this.database.readDbToInventory(this.database.createFile("test3.txt"));
        this.userInput = new UserInput();
    }
    public Product insertProduct()
    {
        Scanner input = new Scanner(System.in);
        int id = 0;
        String name = "";
        float price = 0;
        try
        {
            view.printMessage("Insert id");
            id = Integer.parseInt(input.nextLine());
            view.printMessage("Insert name");
            name = input.nextLine();
            view.printMessage("Insert price");
            price = Float.parseFloat(input.nextLine());

        }
        catch (NumberFormatException e)
        {
            view.printNumFormatExc(e);
        }

        while(true)
        {
            if (this.inventory.checkId(id))
            {
                return new Product(id, name, price);
            }

            view.printMessage("Invalid Id, or the Id is already taken, please insert new one");
            try
            {
                id = Integer.parseInt(input.nextLine());
            }
            catch (NumberFormatException e)
            {
                id = 0;
                view.printNumFormatExc(e);
            }
        }
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
        this.view.showOptions();
        int choice = 0;
        try{
            choice = userInput.UIGetChoice();
        }
        catch (Exception e)
        {
            view.printException(e);
        }

        switch (choice)
        {
            case 1:
                view.showInventory(this.inventory.returnList());
                break;
            case 2:
                view.printMessage("Insert Id of preferred item");
                cart.addById(this.inventory.returnList());
                break;
            case 3:
                view.showCart(this.cart.returnList());
                break;
            case 4:
                view.printMessage("Insert Id of the product you want to remove");
                int id = 0;
                id = userInput.UIGetId(inventory);
                try
                {
                    this.cart.removeFromCart(id);
                }
                catch (Exception e)
                {
                    view.printException(e);
                }
                break;
            case 5:
                try
                {
                    this.cart.clearCart();
                }
                catch (Exception e)
                {
                    view.printException(e);
                }
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
                //view.printMessage("Wrong argument, chose form one below:");
                //view.showOptions();
        }
        return true;
    }
    public boolean adminInput()
    {
        view.printMessage("Admin console");
        this.view.showAdminOptions();

        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        try
        {
            choice = Integer.parseInt(scanner.nextLine());
        }
        catch (NumberFormatException e)
        {
            view.printNumFormatExc(e);
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
                    view.printMessage("Insert Id of the item you want to remove");
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
                        view.printNumFormatExc(e);
                    }
                    if (!inventory.checkId(id))
                    {
                        this.inventory.removeFromInventory(id);
                        loop = false;
                    }
                    else
                    {
                        view.printMessage("Wrong ID");
                        view.printMessage("Insert Id again or press X+Enter to exit admin mode");
                    }
                }
                break;
            case 3:
                view.printMessage("Insert Id of the item");
                id = userInput.UIGetId(inventory);
                {
                    //change item
                }
                break;
            case 9:
                return false;
            default:
                break;

        }
        return true;
    }
}
