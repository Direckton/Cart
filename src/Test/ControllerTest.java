package Test;

import Model.Inventory;
import Model.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    private Inventory inventory;

    ControllerTest()
    {
        inventory = new Inventory();
        inventory.addToInventory(new Product(1,"test1",1.2f));
        inventory.addToInventory(new Product(2,"test2",2.2f));
        inventory.addToInventory(new Product(3,"test3",3.2f));

    }

    @Test
    void validateForInventory() {

    }

    @Test
    void validateForChange() {
    }

    @org.junit.jupiter.api.Test
    void userInput() {
    }

    @org.junit.jupiter.api.Test
    void adminInput() {
    }
}