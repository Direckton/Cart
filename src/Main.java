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


public class Main {
    public static void main(String[] args) {


        Controller controller = new Controller();
        boolean exit = true;
        while (exit)
        {
            exit = controller.userInput();

        }

    }
}