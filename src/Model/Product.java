package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Product {
    private int id;
    private String name;
    private float price;

    public Product(int id, String name, float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
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
    public Product getProduct()
    {
        return new Product(this.id,this.name,this.price);
    }
    public void printProduct(Product product)
    {
        Product product1;
        product1 = product;

        System.out.print(product1.id + " ");
        System.out.print(product1.name + " ");
        System.out.println(product1.price);

    }
}
