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

    /**
     * Default constructor initializes all private items and parses necessary arguments
     */
    public Controller()
    {
        this.view = new View();
        this.inventory = new Inventory();
        this.cart = new Cart();
        this.database = new Database();
        try {
            this.inventory = this.database.readDbToInventory(this.database.createFile("inventory.txt"));
        }
        catch (IOException e)
        {
            view.printException(e);
        }
        this.userInput = new UserInput(this.view);
    }

    /**
     * Provides initial data of the 'Product' object that will be processed further.
     * Id is checked for an integer and price for float
     * @return new object of 'Product' type
     * @throws Exception wrong numeric format for id and price
     */
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

    /**
     * Performs necessary checks for object to be added to inventory
     * @return valid object
     * @throws Exception initial product creation was invalid
     */
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
                //get correct id
                 product.setId(userInput.UIGetNewId(inventory));
            }
            catch (NumberFormatException e)
            {
                //in case id is invalid set it to 0, will be ignored
                id = 0;
                view.printNumFormatExc(e);
            }
        }
    }

    /**
     * Performs checks necessary for changing product data, existing id is allowed
     * @param oldId existing id
     * @return 'Product' object
     */
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

    /**
     * Main methode switching logic of different program functions.
     * Uses switch statement for controlling user selection and calls corresponding methods.
     * @return boolean value used to control while loop
     */
    public boolean userInput()
    {
        this.view.showOptions(); //show available choices to the user
        int choice = 0;
        try{
            choice = userInput.UIGetChoice(); //gets user choice from io stream
        }
        catch (Exception e)
        {
            view.printException(e);
        }

        switch (choice)
        {
            case 1: //show inventory
                view.showInventory(this.inventory.returnList());
                break;
            case 2: //add product to cart
                view.showInventory(this.inventory.returnList());
                view.printMessage("Insert Id of preferred item");
                cart.addById(this.inventory.returnList(), userInput.UIGetExistingId(this.inventory));
                break;
            case 3: //show cart
                view.showCart(this.cart.returnList());
                break;
            case 4: //remove item from cart (by id)
                view.printMessage("Insert Id of the product you want to remove");
                int id = 0;
                id = userInput.UIGetExistingId(inventory);
                try {
                    this.cart.removeFromCart(id);
                }
                catch (Exception e) {
                    view.printException(e);
                }
                break;
            case 5: //remove everything from the cart
                try {
                    this.cart.clearCart();
                }
                catch (Exception e) {
                    view.printException(e);
                }
                break;
            case 8: //enter admin mode
                boolean exitAdmin = true; //controls admin loop
                while (exitAdmin)
                {
                    exitAdmin = adminInput();
                }
                break;
            case 9: // save changes to the inventory and exit the program
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

    /**
     * Method for switching admin controls and responsible for calling more advanced methods
     * @return boolean value used to control while loop
     */
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
