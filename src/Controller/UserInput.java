package Controller;

import Model.Inventory;
import jdk.jshell.spi.ExecutionControl;


import java.net.InterfaceAddress;
import java.util.Scanner;

public class UserInput {
    Scanner scanner = new Scanner(System.in);

    public int UIGetChoice() throws Exception
    {
        int choice = 0;
        try {
             choice = Integer.parseInt(scanner.nextLine());
        }
        catch (Exception e)
        {
            throw new Exception("Wrong input, please refer to choice list");
        }
        return choice;
    }

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
