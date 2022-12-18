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

    /**
     * Creates file of specified name or opens it if it already exists.
     * @param path path of the file
     * @return file
     * @throws IOException
     */
    public File createFile(String path) throws IOException
    {
        try {
            File file = new File(path);
            if (file.createNewFile()) {
                view.printMessage("Created new file " + file.getName());
            } else {
                view.printMessage("File " + file.getName() + " already exists, opening:");
                file = openFile(path);
            }
            return file;
        }
        catch (IOException e) {
            throw new IOException(e);
        }
    }

    /**
     * Opens and returns existing file
     * @param path path to the file
     * @return file or null if the file doesn't exist
     * @throws IOException
     */
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

    /**
     * Loads contents of the file to inventory
     * @param file opened file
     * @return inventory object
     */
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

    /**
     * Writes inventory back to file
     * @param inventory current inventory, edited or not
     * @param path path of the file
     */
    public void writeInventoryToDb(List<Product> inventory, String path)
    {
        try {
            FileWriter writer = new FileWriter(path);

            for (Product i : inventory)
            {
                writer.write(i.getId() + " " + i.getName() + " " + i.getPrice() + "\n");
            }
            writer.close();
            view.printMessage("Successfully wrote to the file.");
        } catch (IOException e) {
            view.printMessage("An error occurred.");
            e.printStackTrace();
        }
    }
}
