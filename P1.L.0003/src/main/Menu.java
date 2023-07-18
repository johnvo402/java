package main;

import program.IO;

/**
 *
 * @author Vo Thanh Thu - CE170522
 */
public class Menu {

    /**
     * Method to bring up the menu and select the execution option
     *
     * @return option
     */
    public int menu() {
        System.out.println("1. Create fruit ");
        System.out.println("2. View order");
        System.out.println("3. Shopping ");
        System.out.println("4. Exit");
        int n = IO.getInteger("Please choose: ", "Number must be from 1 to 4", 1, 4);
        return n;
    }
}
