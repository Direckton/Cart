import Model.Product;
import Model.Inventory;
import Model.Cart;


public class Main {
    public static void main(String[] args) {
        Product prod = new Product(1,"Test",1.69f);
        Inventory inventory = new Inventory();
        inventory.addToInventory(prod);
        inventory.addToInventory(new Product(2,"test2",2.69f));
        //inventory.printInventory();
        Cart cart = new Cart();
        cart.addToCart(prod);
        cart.addToCart(inventory.getProduct(1));
        cart.showCart();
        cart.removeFromCart(1);
        cart.showCart();
        cart.addToCart(inventory.getProductById(2));
        cart.addToCart(inventory.getProductById(2));
        cart.addToCart(inventory.getProductById(2));
        cart.addToCart(inventory.getProductById(2));
        cart.addToCart(inventory.getProductById(2));
        cart.showCart();
        cart.clearCart();
        cart.showCart();

    }
}