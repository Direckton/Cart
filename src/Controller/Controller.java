package Controller;
import Model.*;
import View.View;

import java.util.Scanner;

public class Controller {
    Product insertProduct()
    {
        System.out.println("Insert id");
        Scanner input = new Scanner(System.in);
        int id = Integer.parseInt(input.nextLine());
        System.out.println("Insert name");
        String name = input.nextLine();
        System.out.println("Insert price");
        float price = Float.parseFloat(input.nextLine());

        return new Product (id,name,price);

    }
}
