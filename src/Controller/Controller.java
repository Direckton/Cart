package Controller;
import Model.*;
import View.View;

import java.io.File;
import java.net.Inet4Address;
import java.util.Scanner;

public class Controller {

    private Cart cart;
    private Inventory inventory;
    private View view;
    public Controller()
    {
        this.view = new View();
        this.inventory = new Inventory();
        this.cart = new Cart();
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

    public void importDb()
    {
        File database = this.inventory.loadDBFile("Database.csv");

    }
}
