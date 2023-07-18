package main;

import program.ShopFruit;

/**
 *
 * @author Vo Thanh Thu - CE170522
 */
public class Main {

    public static void main(String[] args) {
        Menu i = new Menu();
        ShopFruit mg = new ShopFruit();
        int choice = 0;
        do {
            //call menu to get selection
            choice = i.menu();
            switch (choice) {
                case 1:
                    //if it is 1 then call the method that create the fruit
                    mg.createProduct();
                    break;
                case 2:
                    //if it is 2 then call the method that view list order the fruit
                    mg.viewOrders();
                    break;
                case 3:
                    //if it is 3 then call the method that shopping for user the fruit
                    mg.shopping();
                    break;
                case 4:
                    //if it is 4 then end program
                    System.out.println("Thank for using!");
                    break;
            }
        } while (choice < 4);
    }
}
