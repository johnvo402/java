/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package L03;

import java.util.Scanner;

/**
 *
 * @author Tran Tri Tin - CE172062
 */
public class L03_Shopping {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Manager manager = new Manager();
        ValidCheck validation = new ValidCheck();
        manager.fruitInStock();//calls the method on the object
        while (true) {//check if an integer value is valid
            displayMenu();
            int choice = validation.getInteger("Enter choice: ", "~~~~~Enter 1 to 4");
            switch (choice) {
                case 1: //input fruit
                    manager.createFruit();//require user enter new fruit
                    break;
                case 2: //view.viewOrder(listOrder);
                    manager.viewOrder();//give user see ordered, total
                    break;
                case 3: //view.shopping(listOrder, listFruit);
                    manager.shopping();//user will enter fruit that they want to buy
                    break;
                case 4: //Exit
                    System.out.println("----------------***-----------------");
                    System.out.println("____Thanks for using our service____");
                    System.out.println("__Contact to get more information __");
                    System.out.println("          --0913241410--");
                    System.out.println("----------------***-----------------");
                    return;//back out program
                default:
                    System.out.println("Please enter 1 to 4 ! ");

            }
        }

    }

    private static void displayMenu() {
        System.out.println("FRUIT SHOP SYSTEM\n"
                + "1.	Create Fruit\n"
                + "2.	View orders\n"
                + "3.	Shopping (for buyer)\n"
                + "4.	Exit");
        System.out.println("================================");
    }

}
