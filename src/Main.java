import Model.Product;
import Model.Inventory;
import Model.Cart;
import View.View;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Product prod = new Product(1,"Test",1.69f);
        Inventory inventory = new Inventory();
        inventory.addToInventory(new Product(2,"test2",2.69f));
        View view = new View();
        Cart cart = new Cart();
        view.showOptions();
        Scanner myScan = new Scanner(System.in);
        String selection = myScan.nextLine();
        System.out.println(selection);

    }
}