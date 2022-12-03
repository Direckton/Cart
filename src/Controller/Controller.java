package Controller;
import Model.*;
import View.*;
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
    }
    public Product insertProduct()
    {
        Scanner input = new Scanner(System.in);
        int id = 0;
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

        while(true)
        {
            if (this.inventory.checkId(id))
            {
                return new Product(id, name, price);
            }

            System.out.println("Invalid Id, or the Id is already taken, please insert new one");
            try
            {
                id = Integer.parseInt(input.nextLine());
            }
            catch (NumberFormatException e)
            {
                id = 0;
                System.err.println(e);
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
                System.out.println("Insert Id of preferred item");
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
        this.view.showAdminOptions();

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
                    System.out.println("Insert Id of the item you want to remove");
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
                    if (!inventory.checkId(id))
                    {
                        this.inventory.removeFromInventory(id);
                        loop = false;
                    }
                    else
                    {
                        System.out.println("Wrong Id!");
                        System.out.println("Insert Id again or press X+Enter to exit admin mode");
                    }
                }
                break;
            case 3:
                System.out.println("Insert Id of the item");
                id = 0;
                try
                {
                    id = Integer.parseInt(scanner.nextLine());
                }
                catch (NumberFormatException e)
                {
                    System.out.println(e);
                }
                if(inventory.checkId(id))
                {
                    //change item
                }
                else {
                    System.out.println("Invalid Id");
                }

                break;
            default:
                break;

        }
        return true;
    }
}
