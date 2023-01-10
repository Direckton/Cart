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
    public Inventory() {
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

    /**
     * Used to get whole list of inventory
     * @return Inventory List
     */
    public List<Product> returnList()
    {
        return this.inventory;
    }

    /**
     * Checks if given id already exist in the system
     * @param id id being checked
     * @return boolean representing if such id was found (true == found , else == false)
     */
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

    /**
     * Checks whether given id is valid (not occupied or 0)
     * @param id id being checked
     * @return boolean (true == id is free to use, else == false)
     */
    public boolean checkValidId(int... id)
    {
        for(int z : id)
        {
                if(z==0) {
                //Id cannot be 0
                return false;
            }

            for (Product i : inventory) {
                if(i.getId() == z) {
                    //Id cannot repeat
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Iterates through inventory in search of given id and deletes whole object
     * @param id id of the product being deleted
     */
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

    /**
     * Returns whole object of given id
     * @param id id of the object
     * @return 'Product' object
     */
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

    /**
     * Implements possibility to change existing item by replacing it with new one.
     * After the operation array is sorted
     * @param oldProd Product that will be removed
     * @param newProd Product that will replace it
     */
    public void changeItem(Product oldProd, Product newProd)
    {
        this.removeFromInventory(oldProd.getId());
        this.addToInventory(newProd);
        Collections.sort(this.inventory, new SortById());
    }


}

class SortById implements Comparator<Product>
{
    /**
     * sorting algorithm used to determine whether id is greater than the next one.
     * @param p1 the first object to be compared.
     * @param p2 the second object to be compared.
     * @return input of sort algorithm.
     */
    public int compare(Product p1, Product p2)
    {
        if(p1.getId()>p2.getId()){
            return 1;
        }
        return -1;
    }
}