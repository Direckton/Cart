package Model;
import Model.Product;
import Model.Inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.StreamSupport;

/**
 * Class stores user's cart items
 */
public class Cart {
    private List<Product> cart;

    public Cart() {
        cart = new ArrayList<>();
    }

    /**
     * Adds product to the cart array
     * @param product object of a product
     */
    public void addToCart(Product product) {
        this.cart.add(product);
    }
    /**
     * Adds product to the cart array by the product's id
     * @param inventory inventory list
     * @param id id of the inventory product
     */
    public void addById(List<Product> inventory, int id) {
        for (Product i : inventory) {
            if (i.getId() == id) {
                this.addToCart(i);
                break;
            }
        }
    }

    /**
     * Removes product of given id from cart
     * @param id id of desired product to be removed
     * @throws Exception product of given id is not in cart
     */
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

    /**
     * Removes all products from the cart
     * @throws Exception cart is empty
     */
    public void clearCart() throws Exception {
        if (cart.isEmpty()) {
            throw new Exception("The cart already is empty!");
        }
        this.cart.clear();
    }

    /**
     * Gets list of the products
     * @return array of products in the cart
     */
    public List<Product> returnList() {
        return this.cart;
    }

}