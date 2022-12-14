import Controller.Controller;
import Model.Database;
import Model.Inventory;
import Model.Product;
import View.View;
import com.sun.source.doctree.InlineTagTree;

import java.io.File;
import java.io.IOException;
import java.net.InterfaceAddress;
import java.util.Scanner;

/**
 * This is an E-cart, program for shopping online.
 * @author Michał Buczak
 * @version 1.0
 *
 */
public class Main {
    public static void main(String[] args) {

        Controller controller = new Controller();
        boolean exit = true;
        while (exit) //main program loop
        {
            exit = controller.userInput();
        }
    }
}