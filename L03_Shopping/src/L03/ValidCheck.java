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
public class ValidCheck {

    Scanner sc = new Scanner(System.in); //reads input from the user or from other sources.

    /**
     * This function is used to ask users to enter the amount of fruit they want
     * to buy. The largest input required is the quantity currently in the List
     *
     * @param mess message to ask the user to enter information
     * @param min Minimum value that can be entered
     * @param max maximum value that can be entered
     * @return the valid amount of fruit you want to buy
     */
    public int enterShopingQuantity(String mess, int min, int max) {
        System.out.print(mess);//prints the mess parameter to prompt the user.
        while (true) {//while loop that will continue until a valid input is received.
            String input = sc.nextLine();//reads the user's input using the nextLine() of the Scanner
            try {
                int number = Integer.parseInt(input);// parse the input string into an integer
                //if success, it check number that valid 
                if (number < min || number > max) {//if not
                    System.out.println("--------Please enter within the quantity limit from " + min + "to " + max);//print the message
                    System.out.print("--Please re-enter quantity : "); //print the message
                    continue;//next loop step
                }
                return number;//if it in valid range, it return valid number
            } catch (Exception e) {//if enter alphabet, special character,...
                System.out.println("--------Please input an integer number: ");//print the message 
                System.out.print("--Please re-enter quantity : "); //print the message
            }
        }
    }

    /**
     * Print the message to enter an integer and check if it is correct, then
     * continue the program, if it is wrong, an error message
     *
     * @param iMsg message to ask the user to enter information
     * @param iErr Error message if the format is not correct
     * @return a valid integer exactly as required
     */
    public static int getInteger(String iMsg, String iErr) {
        Scanner sc = new Scanner(System.in); // reader from the user.
        while (true) { //loop until user input correct
            try { // try block to catch any exception that may occur during execution
                System.out.print(iMsg); // require user input.
                int n = Integer.parseInt(sc.nextLine()); // it will assign the parsed integer to the variable
                return n; // return integer number
            } catch (NumberFormatException e) { // If an exception occurs during conversion will be caught.
                System.out.println(iErr); // error message to notify the user about incorrect input.
            }
        }
    }

    /**
     * ask the user to enter the code of the fruit they want to buy
     *
     * @param mess message to ask the user to enter information
     * @param min Minimum value that can be entered
     * @param max maximum value that can be entered
     * @return number of fruit want to buy
     */
    public int enterShopingNo(String mess, int min, int max) {
        System.out.print(mess);//prints the mess parameter to prompt the user.
        while (true) {//while loop that will continue until a valid input is received.
            String input = sc.nextLine();//reads the user's input using the nextLine() of the Scanner
            if (input.isEmpty()) {
                System.out.println("--------Not empty !!!"); // Thông báo khi người dùng không nhập gì
                System.out.print("--Please Re-enter No. : "); //print the message 
                continue; // Tiếp tục vòng lặp để yêu cầu người dùng nhập lại
            }

            try {
                int number = Integer.parseInt(input);// parse the input string into an integer
                //if success, it check number that valid 

                if (number < min || number > max) {//if not
                    System.out.println("--------Choose follow No. in table. Max is " + max + ". Please enter again !!!");//print the message
                    System.out.print("--Please re-enter No. : ");//print the message 
                    continue;//next loop step
                }
                return number;//if it in valid range, it return valid number
            } catch (Exception e) {//if enter alphabet, special character,...
                System.out.println("--------Please input an integer number !!!");//print the message 
                System.out.print("--Please Re-enter No. : "); //print the message 
            }
        }
    }

    /**
     * ask the user to enter the quantity of the fruit they enter into the
     * inventory
     *
     * @param mess message to ask the user to enter information
     * @param min Minimum value that can be entered
     * @param max maximum value that can be entered
     * @return valid number of quantity
     */
    public int enterCreateQuantity(String mess, int min, int max) {
        System.out.print(mess);//prints the mess parameter to prompt the user.
        while (true) {//while loop that will continue until a valid input is received.
            String input = sc.nextLine();//reads the user's input using the nextLine() of the Scanner
            try {
                int number = Integer.parseInt(input);// parse the input string into an integer
                //if success, it check number that valid 
                if (number <= 0) {//if number < 0
                    System.out.println("--------Please input a positive number !!!");//print the message
                    System.out.print("--Please re-enter input : "); //print the message
                    continue;//next loop step
                }
                return number;//if it in valid range, it return valid number
            } catch (Exception e) {//if enter alphabet, special character,...
                System.out.println("--------Please input an integer number: ");//print the message 
                System.out.print("--Please re-enter input : "); //print the message
            }
        }
    }

    /**
     * the user enters the price of the fruit they enter into the store
     *
     * @param mess message to ask the user to enter information
     * @param min Minimum value that can be entered
     * @param max maximum value that can be entered
     * @return valid price (positive)
     */
    public double enterPrice(String mess, double min, double max) {
        System.out.print(mess);//prints the mess parameter to prompt the user.
        while (true) {
            String input = sc.nextLine();//reads the user's input using the nextLine() of the Scanner
            if (input.matches("\\d+(\\.\\d+)?")) { // Check if input consists of digits and optional decimal point
                double number = Double.parseDouble(input);// parse the input string into an double
                // Check range of number
                if (number <= 0) {//if number < 0
                    System.out.println("--------Please input a positive number !!!");//print the message
                    System.out.print("--Please re-enter input : "); //print the message
                    continue;//next loop step
                }
                return number;//if it in valid range, it return valid number
            } else {
                System.out.println("--------Please input a positive number: ");//print message 
                System.out.print("--Please re-enter input : "); //print the message
            }
        }
    }

    /**
     * Enter a valid string to continue the program
     *
     * @param mess message to ask the user to enter information
     * @param regex is a requirement for input : include alphanumeric
     * @return a valid string that conforms to the request
     */
    public String inputString(String mess, String regex) {
        System.out.print(mess);//prints the mess parameter to prompt the user.
        while (true) {
            String input = sc.nextLine();//reads the user's input using the nextLine() of the Scanner
            if (!input.matches(regex)) { //If the input string does not match the regex pattern "[a-zA-Z0-9 ]+"
                System.out.println("--------Please enter input (include alphabet & number): ");//print a message
                System.out.print("--Please re-enter input : "); //print the message
                continue;//continue to the next iteration of the loop.
            }
            return input;//if it match, return valid input
        }
    }

    /**
     * the user enters their choice whether to continue the program or not
     *
     * @param mess message to ask the user to enter information
     * @return a valid string of 1 of 4 letters YyNn
     */
    public boolean checkInputYN(String mess) {
        System.out.print(mess);//prints the mess parameter to prompt the user.
        while (true) {//loop until enter valid 
            String result = sc.nextLine();//reads the user's input using the nextLine() of the Scanner
            if (result.equalsIgnoreCase("Y")) {//if user enter y,Y 
                return true;//it return true: continue run program to user enter fruit
            }
            if (result.equalsIgnoreCase("N")) {//if user enter n,N
                return false;//it return fasle: continue run program to return main screen
            }
            System.out.println("--------Please input y/Y or n/N.");//if user not enter n,N,y,Y print message
            System.out.print("--Please re-enter again : ");//require re-enter
        }
    }
}
