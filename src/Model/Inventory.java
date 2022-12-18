package Model;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Stores all available products and manages them.
 */
public class Inventory {
    /**
     * Storage container for all objects of Product type
     */
    private List<Product> inventory;

    /**
     * Default constructor. Initializes storage container ad ArrayList.
     */
    public Inventory()
    {
        inventory = new ArrayList<>();
    }

    /**
     * Adds 'Product' object to 'inventory' list
     * @param product Object used to store collection of data representing product available for sale.
     */
    public void addToInventory(Product product)
    {
        this.inventory.add(product);
    }

    /**
     * Iterates through all the elements of the list in search of object with correct id.
     * @param Id Integer used to identify product both in cart and inventory.
     * @return 'Product' object or null in case object doesn't exist.
     */
    public Product getProduct(int Id)
    {
        for(Product i:inventory)
        {
            if(Id == i.getId()) {
                return i;
            }
        }
        return null;
    }
    public List<Product> returnList()
    {
        return this.inventory;
    }

    public boolean checkExistingId(int id)
    {
        if(id==0)
        {
            //Id cannot be 0
            return false;
        }
        for (Product i : inventory)
        {
            if(i.getId() == id)
            {
                //Id cannot repeat
                return true;
            }

        }
        return false;
    }

    public boolean checkValidId(int id)
    {
        if(id==0)
        {
            //Id cannot be 0
            return false;
        }
        for (Product i : inventory)
        {
            if(i.getId() == id)
            {
                //Id cannot repeat
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
        catch (IOException e) {

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
    public void removeFromInventory(int id)
    {
        for(Product i : inventory)
        {
            if(i.getId()==id)
            {
                inventory.remove(i);
                break;
            }
        }
    }

    public Product returnItem(int id)
    {
        for (Product i : inventory)
        {
            if (i.getId()==id){
                return i;
            }
        }
        return null;
    }
    public void changeItem(Product oldProd, Product newProd)
    {
        this.removeFromInventory(oldProd.getId());
        this.addToInventory(newProd);
        Collections.sort(this.inventory, new SortById());
    }


}

class SortById implements Comparator<Product>
{
    public int compare(Product p1, Product p2)
    {
        if(p1.getId()>p2.getId()){
            return 1;
        }
        return -1;
    }
}