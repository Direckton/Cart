import Controller.Controller;
import Model.Product;
import View.View;

import java.io.File;
import java.io.IOException;
import java.net.InterfaceAddress;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Controller controller = new Controller();
        File file = new File("test.txt");
        String[] products = {""};
        Product product = new Product();


        try {
            Scanner fileReader = new Scanner(file);
            while(fileReader.hasNextLine())
            {
                String data = fileReader.nextLine();
                System.out.println(data);
                products = data.split(" ");
                int id = Integer.parseInt(products[0]);
                String name = products[1];
                Float price = Float.parseFloat(products[2]);
                product = new Product(id,name,price);


            }
        }
        catch (IOException e)
        {

        }



    }
}