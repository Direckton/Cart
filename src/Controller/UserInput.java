package Controller;

import Model.Inventory;
import View.View;
import jdk.jshell.spi.ExecutionControl;


import java.net.InterfaceAddress;
import java.util.Scanner;

public class UserInput {
    Scanner scanner = new Scanner(System.in);
    View view;

    UserInput(View parseView){
        view = parseView;
    }

    /**
     * Gets and checks choice selected by user
     * @return chioce int
     * @throws Exception incorrect input error
     */
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

    /**
     * Gets and checks existing id selected by user
     * @param inventory reference inventory of products
     * @return existing id
     */
    public int UIGetExistingId(Inventory inventory)
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
                view.printNumFormatExc(e);
            }
            if (!inventory.checkExistingId(id))
            {
                view.printMessage("Invalid Id");
            }
            else
            {
                return id;
            }
        }
    }
    /**
     * Gets and checks valid id selected by user
     * @param inventory reference inventory of products
     * @return valid id
     */
    public int UIGetNewId(Inventory inventory)
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
                view.printNumFormatExc(e);
            }
            if (inventory.checkExistingId(id))
            {
                view.printMessage("Invalid Id");
            }
            else
            {
                return id;
            }
        }
    }

}
