package Controller;

import Model.Inventory;

import java.net.InterfaceAddress;
import java.util.Scanner;

public class UserInput {
    Scanner scanner = new Scanner(System.in);

    public int UIGetId(Inventory inventory)
    {
        int id=0;
        while(true)
        {
            try
            {
                id = Integer.parseInt(scanner.nextLine());
            }
            catch (NumberFormatException e)
            {
                id = 0;
                System.err.println(e);
            }
            if (!inventory.checkId(id))
            {
                System.out.println("Invalid Id, or taken");
            }
            else
            {
                return id;
            }
        }
    }
}
