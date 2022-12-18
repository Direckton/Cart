package Model;


import View.View;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Database {

    private View view;
    public Database(){
        view = new View();
    }

    public File createFile(String path) throws IOException
    {
        try {
            File file = new File(path);
            if (file.createNewFile()) {
                System.out.println("Created new file " + file.getName());
            } else {
                System.out.println("File " + file.getName() + " already exists, opening:");
                file = openFile(path);
            }
            return file;
        }
        catch (IOException e) {
            throw new IOException(e);
        }
    }

    public File openFile(String path) throws IOException
    {
        try {
            File file = new File(path);

            if (file.isFile()) {
                return file;
            } else {
                return null;
            }
        }
        catch (Exception e)
        {
            throw new IOException(e);
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

    public void writeInventoryToDb(List<Product> inventory, String path)
    {
        try {
            FileWriter writer = new FileWriter(path);

            for (Product i : inventory)
            {
                writer.write(i.getId() + " " + i.getName() + " " + i.getPrice() + "\n");
            }
            writer.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            view.printMessage("An error occurred.");
            e.printStackTrace();
        }
    }
}
