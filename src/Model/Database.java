package Model;


import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Database {

    public Database(){}

    public File createFile(String path) {
        try {
            File file = new File(path);
            if (file.createNewFile()) {
                System.out.println("Created new file " + file.getName());
            } else {
                System.out.println("File " + file.getName() + " already exists, opening:");
                file = openFile(path);
            }
            return file;
        } catch (IOException e) {
            throw new RuntimeException(e);

        }
    }

    public File openFile(String path)
    {

        File file = new File(path);
        if(file.isFile())
        {
            return file;
        }
        else{
            return null;
        }

    }



    public Inventory readDbToInventory(File file)
    {
        Inventory inventory = new Inventory();
        String[] products = {""};
        try {
            Scanner fileReader = new Scanner(file);
            while(fileReader.hasNextLine())
            {
                String data = fileReader.nextLine();
                //System.out.println(data);
                products = data.split(" ");
                int id = Integer.parseInt(products[0]);
                String name = products[1];
                Float price = Float.parseFloat(products[2]);
                inventory.addToInventory(new Product(id,name,price));
            }
            return inventory;
        }
        catch (IOException e)
        {
            return null;
        }
    }
}
