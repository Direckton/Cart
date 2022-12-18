package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Object used to store collection of data representing product available for sale.
 */
public class Product {
    /**Integer used to identify product both in cart and inventory.
     *This value cannot be 0 and will be ignored as such.
     *Is picked by user and doesn't have to be following, but never the same as existing one.
     */
    private int id;
    /**Name of the product both in cart and inventory.
     *Is picked by user.
     *No special checking done.
     */
    private String name;
    /**
     * Price of the product ad float value.
     * Picked by the user, checked to be correct float
     * Only 2 numbers after decimal point are shown.
     */
    private float price;

    /**
     * Main object used in the program. It represents certain product being sold in certain online shop.
     * Subset type for Inventory and Cart objects.
     * @param id Identifying integer (cannot be 0)
     * @param name Name of the product
     * @param price Price of the product
     */

    public Product(int id, String name, float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
    /**
     * Default constructor
     */
    public Product()
    {

    }

    public void setId(int id)
    {
       this.id = id;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public void setPrice(float price)
    {
        this.price = price;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public float getPrice()
    {
        return price;
    }

    /**
     * Returns new object of the existing product
     * @return Product
     */
    public Product getProduct()
    {
        return new Product(this.id,this.name,this.price);
    }




}
