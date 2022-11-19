package Model;
import Model.Product;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;
import java.util.Scanner;

public class Inventory {
    private List<Product> inventory;

    public Inventory()
    {
        inventory = new ArrayList<>();
    }
    public void addToInventory(Product product)
    {
        this.inventory.add(product);
    }
    public Product getProduct(int index)
    {
        return inventory.get(index);

    }
    public Product getProductById(int id)
    {
        for(Product i : inventory)
        {
            if(i.getId() == id)
            {
                return i;
            }
        }
        return null;

    }

    public List<Product> returnList()
    {
        return this.inventory;
    }

    public boolean checkId(int id)
    {
        for (Product i : inventory)
        {
            if(i.getId() == id)
            {
                return false;
            }

        }
        return true;
    }

    public File loadDBFile(String path)
    {
        try {
            File db = new File(path);
            if(db.createNewFile()){
                System.out.println("File created " + db.getName());
            }
            else{
                System.out.println("File " + db.getName() + " already exists!");
            }
            return db;

        }
        catch(IOException e){

    }
        return null;
    }

    public void convertToCsv(File file)
    {
        try{
            if(file.isFile())
            {
                Scanner reader = new Scanner(file);
                while(reader.hasNextLine())
                {
                    String data = reader.nextLine();
                    data.split(" ");
                }
            }

        }
        catch(IOException e)
        {

        }

    }


}
