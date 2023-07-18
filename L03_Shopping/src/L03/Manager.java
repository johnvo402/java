/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package L03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Tran Tri Tin - CE172062
 */
public class Manager {

    ArrayList<Fruit> listOrder = new ArrayList<>();//store a list of fruit objects (shop) for -- an order
    List<Fruit> inStock = new ArrayList<>();//declare and initialize another name that can contain objects of the class. The arraylist will be used to store the list of fruit objects currently in the store
    static int count = 0;//declare the value count = 0 to keep the value to find the same name and notice
    Hashtable<String, ArrayList<Fruit>> orders = new Hashtable<>();//store multiple orders, where each order is associated with a unique key
    ValidCheck enter = new ValidCheck();//create an instance of the class and assign it to the variable

    /**
     * check whether the ID entered by the user matches the ID of a certain
     * product already in the lList.
     *
     * @param id : id is entered by user
     * @return a valid ID that does not match an existing id
     */
    public int IDExist(String id, List<Fruit> listFruit) { //The given code is a method called CodeExist that takes a String parameter code and returns an int.
        if (inStock.isEmpty()) { //The method first checks if the database is empty. If it is, it returns 0.
            return 0;
        } else {
            for (Fruit fruit : listFruit) { // loop be used to iterate through the elements of an ArrayList, and check if the element exists in the list.
                if (fruit.getID().equalsIgnoreCase(id)) { //If the phone number matches the existing number
                    System.out.println("This ID already exists. Please enter a different ID !!!");
                    return 1; //1 is returned to message it exist
                }
            }
        }
        return 0; //If the ID is not the same, return 0 and continue the program
    }

    /**
     *
     * Generate the fruit data to save them to the inventory. ask the user to
     * enter the correct format and check. then ask the user if he wants to
     * continue typing
     */
    public void createFruit() {
        while (true) {
            String id = enter.inputString("Enter id : ", "[a-zA-Z0-9 ]+");
            // Check if the ID already exists
            int idExists = IDExist(id, inStock);
            if (idExists == 1) {
                continue; // Continue the loop if ID already exists
            }
            String fruitName;
            String origin;
            boolean f = true;
            do {
                fruitName = enter.inputString("Enter name : ", "[A-Za-z\\s]+");//require name only alphabet and number
                origin = enter.inputString("Enter origin : ", "[A-Za-z\\s]+");//require origin only alphabet and number
                f = checkNameOriginExist(fruitName, origin);//check fruit name and origin exist by checkNameOriginExist
            } while (!f); //if it exist, loop until it valid
            double price = enter.enterPrice("Enter price : ", 0, Double.MAX_VALUE);//prices will be entered in the range from 1 to MAX : 2^31 - 1
            int quantity = enter.enterCreateQuantity("Enter quantity : ", 1, Integer.MAX_VALUE);//quantity also will be entered in the range from 1 to MAX : 2^31 - 1
            inStock.add(new Fruit(id, fruitName, price, quantity, origin));//fruit information will be saved in the storage: inStock
            if (!enter.checkInputYN("Do you want to continue(Y or N)? ")) { //Ask the user if he wants to continue typing
                break;
            }
        }
        displayFruitTables();
    }

    /**
     * Print out the screen a list of all the items in or the user to choose
     * from. then ask the user to enter the code for the user to choose from.
     * then ask the user to enter the code of the item you want to order
     *
     * @return the order code of the item the user wants to order
     */
    public int showListToOrder() {
        int countView = 0;//initializing a variable count = 0
        if (inStock.isEmpty()) {//if no have orders, print message
            return -1;
        }
        for (Fruit fruit : inStock) {// iterate over each Fruit object in the inStock list
            if (fruit.getQuantity() != 0) {//the quantity is not 0,fruit is available for ordering
                countView++;//the count is incremented by 1
                if (countView == 1) {//if the count is 1
                    System.out.println("+-----+-----+--------------------+--------+---------------+----------+");
                    System.out.println("| No. | ID  | Fruit Name         |Quantity| Origin        |   Price  |");
                    System.out.println("+-----+-----+--------------------+--------+---------------+----------+");
                }
                double price = fruit.getPrice();//fruit is retrieved using fruit.getPrice().
                String priceCalculate;
                priceCalculate = String.format("%.2f$", price);//It is then formatted to have two decimal places using String.format()
                System.out.printf("|%5d|%5s|%-20s|%8d|%-15s|%10s|\n", countView, fruit.getID(),
                        fruit.getName(), fruit.getQuantity(), fruit.getOrigin(), priceCalculate);
                System.out.println("+-----+-----+--------------------+--------+---------------+----------+");
            }
        }
        if (countView == 0) {//the count is still 0 after the loop 
            return -1;//no fruits were available
        }
        int num = enter.enterShopingNo("Enter No. : ", 1, countView);//require user enter Number of fruit that they want
        return num;//return valid numer of fruit
    }

    /**
     * check that if the same name is not the same origin, if the same origin is
     * not the same name, if the same name, check the origin, if it is both,
     * print a message asking to re-enter the name and origin
     *
     * @param name : name of fruit that is entered by user
     * @param origin : origin of fruit that is entered by user
     * @return the name and origin must not be the same as the products already
     * in stock
     */
    public boolean checkNameOriginExist(String name, String origin) {
        for (Fruit fruit : inStock) {//iterate over the elements in List : inStock
            if (name.equalsIgnoreCase(fruit.getName())) {//if it has the same name
                if (origin.equalsIgnoreCase(fruit.getOrigin())) {//keep checking if it's the same source
                    System.out.println(origin + " already exists " + name);//print message
                    return false;//return false to indicate it matches
                }
                return true;//return true to indicate it not matches
            }
        }
        return true;//return true to indicate it not matches
    }

    /**
     * take the input integer to find the fruit the user wants to order
     *
     * @param num : item input is entered by the user to find the fruit to order
     * @return fruit found by code
     */
    public Fruit getFruit(int num) {
        int count = 0;//Initialize a variable count  = 0
        for (Fruit fruit : inStock) {//each Fruit object in the inStock list using an enhanced for loop.
            if (fruit.getQuantity() != 0) {//Check if the quantity of the current fruit is not equal to zero using the getQuantity()
                count++;//increment the count variable
            }
            if (num == count) { //if the input(item) is equal to the code of the fruit
                return fruit;// return that fruit.
            }
        }
        return null;//If the loop finishes without finding the desired fruit, return null.
    }

    /**
     * check if the product ordered by the user is in stock by id , if yes then
     * return that product
     *
     * @param id : id input is entered by the user to find the fruit to order
     * @return fruit found by id
     */
    public Fruit checkFruitInOrder(String id) {
        ArrayList<Fruit> listOrder = new ArrayList<>();
        for (Fruit fruit : listOrder) {//each Fruit object in the inStock list using an enhanced for loop.
            if (fruit.getID().equalsIgnoreCase(id)) {//if the input(id) is equal to the code of the fruit
                return fruit;// return that fruit
            }
        }
        return null;//If the loop finishes without finding the desired fruit, return null.
    }

    /**
     * to create a buying process for customers, if there are products in stock,
     * start letting customers choose items and quantities, otherwise, notify
     * out of stock. if no order is created, it says no order yet
     */
    public void shopping() {
        ArrayList<Fruit> listOrder = new ArrayList<>();//listOrder to store selected products.
        boolean outOfStock = false;//initializes a boolean variable outOfStock as false.
        int countView = 0;//initializing a variable count = 0
        HashMap<String, Integer> haveQuantity = new HashMap<>();//to store the cumulative number of each fruit
        while (true) {//loop allows the user to select fruits for ordering until they choose to stop.
            int num = showListToOrder();//display the available fruits 
            if (num == -1) {//If num is -1,no more fruits available for ordering
                outOfStock = true;//initializes a boolean variable outOfStock as false.
                break;// the outOfStock variable is set to true, and the loop is exited using break.
            }
            Fruit fruit = getFruit(num);//If num is a valid fruit number
            System.out.println("-Your selected: " + fruit.getName());//name of the selected fruit is then printed 
            int quantity = enter.enterShopingQuantity("Enter quantity : ", 0, fruit.getQuantity());//enter the quantity of the selected fruit
            fruit.setQuantity(fruit.getQuantity() - quantity);//The selected fruit's quantity is updated accordingly.
            Fruit fruitInOrder = checkFruitInOrder(fruit.getID());//Check if the selected fruit exists in the listOrder
            if (fruitInOrder != null) {//If the fruit type already exists in listOrder
                fruitInOrder.setQuantity(fruitInOrder.getQuantity() + quantity);//we update the quantity of that fruit and the amount accumulated in haveQuantity
                haveQuantity.put(fruit.getID(), haveQuantity.get(fruit.getID()) + quantity);
            } else {
                if (quantity != 0) {//If the selected fruit does not exist in listOrder and the quantity is not 0
                    boolean isDuplicate = false;
                    for (Fruit orderedFruit : listOrder) {//browse orderedFruit in list Ordered
                        if (orderedFruit.getID().equals(fruit.getID())) { //if new fruit's ID is equal to old fruit'ID
                            orderedFruit.setQuantity(orderedFruit.getQuantity() + quantity);// add quantity of new fruit on old fruit
                            isDuplicate = true;//it true
                            break;
                        }
                    }
                    if (!isDuplicate) {//if not have fruit exist in order, create a new product in order
                        listOrder.add(new Fruit(fruit.getID(), fruit.getName(), fruit.getPrice(), quantity, fruit.getOrigin()));//a new Fruit object with the selected fruit's details is created and added to the listOrder.
                    }
                    haveQuantity.put(fruit.getID(), quantity);//update cumulative quantity
                }
            }
            if (!enter.checkInputYN("***Do you want to order more(Y/N)? ")) {//Ask them if they want to order more. If the user enters "N" or "n", the loop will exit.
                break;// If the user enters "N" or "n", the loop will exit.
            }
            // Remove fruits with quantity 0 from listOrder
            Iterator<Fruit> iterator = listOrder.iterator();
            while (iterator.hasNext()) {
                Fruit fruitPicked = iterator.next();
                if (fruitPicked.getQuantity() == 0) {
                    iterator.remove();
                }
            }
        }

        if (outOfStock) {//if out of stock
            System.out.println("~~~~ Stock is out !!!");//print message
        }
        if (!listOrder.isEmpty()) {//If listOrder is not empty, the code proceeds to calculate the total amount for the ordered fruits
            double total = 0;// A total variable is initialized to 0.
            List<Fruit> onlyOrder = new ArrayList<>();//store unique products (no duplicates)
            for (Fruit fruit : listOrder) {//Browse through each product in the listOrder
                boolean isDuplicate = false;
                for (Fruit uniqueFruit : onlyOrder) { //check if product already exists in onlyOrder
                    if (fruit.getID().equals(uniqueFruit.getID())) {//If it already exists,
                        uniqueFruit.setQuantity(uniqueFruit.getQuantity() + fruit.getQuantity());// we update the quantity of that product.
                        isDuplicate = true;//it exist

                        break;
                    }

                }
                if (!isDuplicate) {//If it doesn't exist
                    onlyOrder.add(fruit);//we add that product to onlyOrder

                }
            }

            System.out.println("+-------+-------+--------------------+----------+----------+--------+");
            System.out.println("|   No. |   ID  |   Fruit Name       | Quantity |   Price  | Amount |");
            System.out.println("+-------+-------+--------------------+----------+----------+--------+");
            for (Fruit fruit : onlyOrder) {//Print out detailed information of each product in onlyOrder
                countView++;
                String priceString = String.format("%.2f$", fruit.getPrice());
                String amount = String.format("%.2f$", fruit.getPrice() * fruit.getQuantity());
                System.out.printf("|%5d|%5s|%-20s|%8d|%-15s|%10s|\n", countView, fruit.getID(), fruit.getName(),
                        fruit.getQuantity(), priceString,
                        amount);

                total += fruit.getPrice() * fruit.getQuantity();//Calculate the total amount for the selected products and print out the total amount.

            }
            String totalString = String.format("%.2f$", total);
            System.out.println("+-------+-------+--------------------+----------+----------+--------+");
            System.out.printf("|                                                    TOTAL |  %6s|\n", totalString);
            System.out.println("+-------+-------+--------------------+----------+----------+--------+");
            String name = setName();//prompt the user to enter their name for the order.
            orders.put(name, listOrder);// the listOrder is associated with the user's name using a HashMap named orders, with the put()
        }
    }

    public void displayFruitTables() {
        if (inStock.isEmpty()) {
            System.out.println("No fruit tables available.");
            return;
        }

        int countView = 0;
        for (Fruit fruit : inStock) {// iterate over each Fruit object in the inStock list
            if (fruit.getQuantity() != 0) {//the quantity is not 0,fruit is available for ordering
                countView++;//the count is incremented by 1
                if (countView == 1) {//if the count is 1
                    System.out.println("+-----+-----+--------------------+--------+---------------+----------+");
                    System.out.println("| No. | ID  | Fruit Name         |Quantity| Origin        |   Price  |");
                    System.out.println("+-----+-----+--------------------+--------+---------------+----------+");
                }
                double price = fruit.getPrice();//fruit is retrieved using fruit.getPrice().
                String priceCalculate;
                priceCalculate = String.format("%.2f$", price);//It is then formatted to have two decimal places using String.format()
                System.out.printf("|%5d|%5s|%-20s|%8d|%-15s|%10s|\n", countView, fruit.getID(),
                        fruit.getName(), fruit.getQuantity(), fruit.getOrigin(), priceCalculate);
                System.out.println("+-----+-----+--------------------+--------+---------------+----------+");
            }
        }
    }

    /**
     * Calculate and print the value of the order, the value of each item on the
     * order and the total price to pay that displays the orders stored in a
     * hash table called orders.
     *
     */
    public void viewOrder() {
        int countView = 0;
        if (orders.isEmpty()) {//if no have orders, print message
            System.out.println("----- No orders to display yet");
            return;
        }
        for (String name : orders.keySet()) {//browser hashTable
            int index = name.indexOf("-");//automatically find the location of -:is the virtual position representing the distance between the name and the number of occurrences of that name
            if (index != -1) {//if find the mark -
                String customerName = name.substring(0, index);//customer names will be truncated from beginning to end -
                System.out.println("Customer: " + customerName);//print customer name
            }
            ArrayList<Fruit> listOrder = orders.get(name);//Browse orders

            double total = 0;//for a total value of 0
            System.out.println("+----------+----------+--------------------+----------+----------+--------+");
            System.out.println("|   No.    |   ID     |    Fruit Name      | Quantity |   Price  | Amount |");
            System.out.println("+----------+----------+--------------------+----------+----------+--------+");
            for (Fruit fruit : listOrder) {//browse the fruits contained in listOrder
                countView++;
                String priceString = String.format("%.2f$", fruit.getPrice());//The price will be written as a decimal with 2 commas
                String amount = String.format("%.2f$", fruit.getPrice() * fruit.getQuantity());
                System.out.printf("|%10d|%10s| %-19s|%10d|%10s|%8s|\n", countView, fruit.getID(), fruit.getName(),
                        fruit.getQuantity(), priceString,
                        amount);
                total += fruit.getPrice() * fruit.getQuantity();//Total price will be calculated as SUM : price * quantity
            }
            String totalString = String.format("%.2f$", total);
            System.out.println("+----------+----------+--------------------+----------+----------+--------+");
            System.out.printf("|                                                          TOTAL |  %6s|\n", totalString);//print total
            System.out.println("+----------+----------+--------------------+----------+----------+--------+");
        }
        System.out.println(" ");//print a empty row
    }

    /**
     * requires the user to enter the name according to the correct value, must
     * be at least 1 letter and contain no special characters and write numbers
     * or letters first
     *
     * @return a valid name , can include number and alphabet
     */
    public String setName() {
        String name;
        do {
            name = enter.inputString("Enter name:", "[A-Za-z0-9\\\\s ]*[A-Za-z ][A-Za-z0-9\\\\s ]*");
            for (String name_key : orders.keySet()) {//browse the orders in the hash table
                int index = name_key.indexOf("-");//for the index position is the input name and the position is marked with -
                if (index != -1) {//if it see -
                    String nameNotCount = name_key.substring(0, index); //nameNotCount is the name obtained by slicing the string to get from the beginning to the end of the name
                    if (name.equals(nameNotCount)) {//if it is equal to the entered name then count up
                        count++;//   // Save information for both orders separately
                    }
                }
            }
        } while (name.isEmpty());
        System.out.println("***Order information saved");//print message success
        System.out.println("____Have a good day!!_____");
        return name + "-" + count;//automatically assigns a name followed by a - and then the number of occurrences of that name
    }

    /**
     * create available fruits and save them to List to use when importing
     * products, they will always be available in List. Requirements must be
     * correct including ID, Name, Price, Quantity, and origin
     */
    public void fruitInStock() {
        inStock.add(new Fruit("1", "Dua", 2, 3, "Ben Tre"));
        inStock.add(new Fruit("2", "Cam", 4, 6, "Vinh Long"));
        inStock.add(new Fruit("3", "Xoai", 12.5, 4, "Trung Quoc"));
    }

}
