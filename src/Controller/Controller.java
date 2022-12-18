package Controller;
import Model.*;
import View.*;

import java.io.IOException;
import java.util.Objects;
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
        try {
            this.inventory = this.database.readDbToInventory(this.database.createFile("test.txt"));
        }
        catch (IOException e)
        {
            view.printException(e);
        }
        this.userInput = new UserInput(this.view);
    }
    private Product insertProduct() throws Exception
    {
        Scanner input = new Scanner(System.in);
        int id = 0;
        String name = "";
        float price = 0;
        try {
            view.printMessage("Insert id");
            id = Integer.parseInt(input.nextLine());
        }
        catch (NumberFormatException e) {
            throw new Exception("Wrong ID! Make sure it's a positive integer (not a 0).");
        }
        view.printMessage("Insert name");
        name = input.nextLine();
        String priceString = "";
        try {
            view.printMessage("Insert price");
            priceString = input.nextLine();
            price = Float.parseFloat(priceString);
        }
        catch (NumberFormatException e) {
            throw new Exception("Wrong price \"" + priceString + "\"! Make sure it's a number");
        }
        return new Product(id,name,price);
    }

    public Product validateForInventory() throws Exception
    {
        Product product = new Product();
        try{
            product = insertProduct();
        }
        catch (Exception e) {
            view.printException(e);
            throw new Exception("Try adding product again");
        }
        int id = 0;
        while(true)
        {
            if (this.inventory.checkValidId(product.getId()))
            {
                return new Product(product.getId(), product.getName(), product.getPrice());
            }

            view.printMessage("The Id is already taken, please insert new one");
            try
            {
                 product.setId(userInput.UIGetNewId(inventory));
            }
            catch (NumberFormatException e)
            {
                id = 0;
                view.printNumFormatExc(e);
            }
        }
    }

    public Product validateForChange(int oldId)
    {
        Product product = new Product();
        try{
            product = insertProduct();
        }
        catch (Exception e) {
            view.printException(e);
        }
        if(oldId== product.getId()){
            return new Product(product.getId(), product.getName(), product.getPrice());
        }
        if(inventory.checkValidId(product.getId()))
        {
            return new Product(product.getId(), product.getName(), product.getPrice());
        }
        else {
            return new Product(userInput.UIGetNewId(inventory), product.getName(), product.getPrice());
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
                view.showInventory(this.inventory.returnList());
                view.printMessage("Insert Id of preferred item");
                cart.addById(this.inventory.returnList(), userInput.UIGetExistingId(this.inventory));
                break;
            case 3:
                view.showCart(this.cart.returnList());
                break;
            case 4:
                view.printMessage("Insert Id of the product you want to remove");
                int id = 0;
                id = userInput.UIGetExistingId(inventory);
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
                database.writeInventoryToDb(inventory.returnList(), "test.txt");

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
        int choice = 0;
        try {
            choice = userInput.UIGetChoice();
        }
        catch (Exception e)
        {
            view.printException(e);
        }
        switch(choice)
        {
            case 1: //Add item to inventory
                try{
                    this.inventory.addToInventory(validateForInventory());
                }
                catch (Exception e){
                    view.printException(e);
                }
            break;
            case 2: //Remove item form inventory
                int id=0;
                view.printMessage("Insert Id of the item you want to remove");
                id = 0;
                id = userInput.UIGetExistingId(inventory);
                this.inventory.removeFromInventory(id);
            break;
            case 3: //Change existing item
                view.printMessage("Insert Id of the item, you'd like to change");
                id = userInput.UIGetExistingId(inventory);
                Product oldProd = inventory.getProduct(id);
                //insert new product
                Product newProd = validateForChange(id);
                //compare in change product
                //add to inventory
                //sort by id
                inventory.changeItem(oldProd,newProd);


                break;
            case 9:
                return false;
            default:
                break;

        }
        return true;
    }
}
