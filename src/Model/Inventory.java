package Model;
import Model.Product;


import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;

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
    public void printInventory()
    {
       for (Product i : inventory)
       {
           i.printProduct(i);
       }
    }
    public List<Product> returnList()
    {
        return this.inventory;
    }

}
