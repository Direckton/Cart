package Model;
import Model.Product;
import Model.Inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.StreamSupport;

public class Cart {
    private List<Product> cart = new ArrayList<>();

    public Cart() {
        cart.clear();
    }

    public void addToCart(Product product) {
        this.cart.add(product);
    }

    public void addById(List<Product> inventory, int id) {
        for (Product i : inventory) {
            if (i.getId() == id) {
                this.addToCart(i);
                break;
            }
        }
    }

    public void removeFromCart(int id) throws Exception {
        for (Product i : cart) {

            if (i.getId() == id) {
                cart.remove(i);
                return;
            }
        }
        if(cart.isEmpty()){
            throw new Exception("The cart is empty");
        }
        throw new Exception("There is no such item in a cart!");
    }


    public void clearCart() throws Exception {
        if (cart.isEmpty()) {
            throw new Exception("The cart already is empty!");
        }
        this.cart.clear();
    }

    public List<Product> returnList() {
        return this.cart;
    }

}