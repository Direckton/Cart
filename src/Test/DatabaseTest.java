package Test;

import Model.Database;
import Model.Inventory;
import Model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {

    private Database database;
    private String path;
    private Inventory inventory;

    @BeforeEach
    void setUp()
    {
        database = new Database();
        path = "testFile.txt";
        inventory = new Inventory();
        inventory.addToInventory(new Product(1, "test", 1.1f));
        inventory.addToInventory(new Product(2, "test2", 2.2f));
        inventory.addToInventory(new Product(3, "test3", 3.3f));

    }

    @Test
    void createFileTest() {
        File file = null;
        try
        {
            file = database.createFile(path);
        }catch (IOException e)
        {
            System.out.println(e);
        }
        file.deleteOnExit();
        assertNotNull(file);

    }

    @Test
    void openFileTest() {
        File file = null;
        try
        {
            file = database.openFile("test.txt");
        }catch (IOException e)
        {
            System.out.println(e);
        }
        assertNotNull(file);
    }

    @Test
    void readDbToInventoryTest() {
        Inventory actualInv = new Inventory();
        try {
            actualInv = database.readDbToInventory(database.openFile("test.txt"));
        } catch (IOException e) {
            System.out.println(e);
        }
        for (int i = 0; i < inventory.returnList().size(); i++) {
            Product expected = inventory.returnList().get(i);
            Product actual = actualInv.returnList().get(i);
            assertEquals(expected.getId(), actual.getId());
            assertEquals(expected.getName(), actual.getName());
            assertEquals(expected.getPrice(), actual.getPrice());
        }
    }

    @Test
    void writeInventoryToDbTest() {
        Inventory actualInv = new Inventory();
        actualInv.addToInventory(new Product(4, "test4", 4.4f));
        inventory.addToInventory(new Product(4, "test4", 4.4f));
        Collections.sort(this.inventory.returnList(), new SortById());
        database.writeInventoryToDb(inventory.returnList(), "test.txt");
        Inventory tested = new Inventory();
        try {
            tested = database.readDbToInventory(database.openFile("test.txt"));
        } catch (IOException e) {
            System.err.println(e);
        }
        for (int i = 0; i < inventory.returnList().size(); i++) {
            Product expected = inventory.returnList().get(i);
            Product actual = tested.returnList().get(i);
            assertEquals(expected.getId(), actual.getId());
            assertEquals(expected.getName(), actual.getName());
            assertEquals(expected.getPrice(), actual.getPrice());

        }
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