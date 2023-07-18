package program;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import object.Fruit;
import object.Order;

/**
 *
 * @author Vo Thanh Thu - CE170522
 */
public class ShopFruit {

    private List<Fruit> listFruit = new ArrayList<>();
    private List<Order> listOrder = new ArrayList<>();
    private List<Order> addCus;
    private int nextID = 1;
    Fruit fr;
    private int count = 0;

    private Hashtable<String, List<Order>> viewOrder = new Hashtable<>();

    /**
     * methods provide memory for objects
     */
    public ShopFruit() {
        listFruit = new ArrayList();
        viewOrder = new Hashtable<>();
        listOrder = new ArrayList<>();
        addCus  = new ArrayList<>();
    }

    /**
     * Method to create fruits in the form of ArrayList
     */
    public void createProduct() {
        boolean check;
        do {
            //create fruit
            String nameFruit = IO.getString("Enter fruit name: ", "Not allow empty string! Pls enter again!");
            double price = IO.getDouble("Enter price: ", "This number must be greater than 0", 1, Integer.MAX_VALUE);
            int quantity = IO.getInteger("Enter quantity: ", "This number must be greater than 0", 1);
            String origin = IO.getString("Enter origin: ", "Not allow empty string! Pls enter again!");
            Fruit f = new Fruit(nextID++, nameFruit, price, quantity, origin);
            listFruit.add(f);
            //Ask if you can create another fruit
            check = nextProgram("Do you want create another fruit? (Y|N) ");
        } while (check == true);
        System.out.println("Create Fruit successfuly!");
    }

    /**
     * Method used to continue or not a program
     *
     * @param mgg lead sentence (question)
     * @return true or false
     */
    public boolean nextProgram(String mgg) {

        while (true) {
            String option = IO.getString(mgg, "Not allow empty string! Pls enter again!");
            //if Y/y then return true
            if (option.equalsIgnoreCase("Y")) {

                return true;

                //if N/ then returnn true
            } else if (option.equalsIgnoreCase("N")) {
                return false;
            } else {
                //when entering another word will give a message
                System.out.println("You must be enter Y/y or N/n!");
            }
        }

    }

    /**
     * Method for non-manager users to use and buy fruit
     */
    public void shopping() {
        //If there are no fruits, a message will be printed
        if (listFruit.isEmpty()) {
            System.out.println("Haven't Fruit!");
        } else {
            boolean check;

            //print out the list of fruits
            System.out.println("List of Fruit:");
            System.out.println("|     No.    |    Fruit Name    |    Origin    |    Price    |");
            for (Fruit f : listFruit) {
                System.out.printf("       %-13s%-18s%-15s%-10s\n", f.getIdFruit(), f.getNameFruit(), f.getOrigin(), f.getPrice());
            }
            do {
                //call to the order method
                order(listOrder);
                //Ask the user if they want to order now, if not, they will continue to choose more fruits
                check = nextProgram("Do you want order now? (Y|N) ");

            } while (check == false);
            //Once completed, it will be added to the invoice
            bill(listOrder);
        }

    }

    /**
     * The method used to order when the user selects a certain fruit, the user
     * must enter the quantity
     *
     * @param listItemBought list of fruits to be purchased
     */
    public void order(List<Order> listItemBought) {
        boolean checked = true;
        int selected;
        Fruit selecFruit;
        //If the selected fruit is out of stock, print out a notice and ask to re-enter it
        do {
            //request to choose
            selected = IO.getInteger("Enter item you want order: ", "Number must be from 1 to " + listFruit.size(), 1, listFruit.size());
            //get selected fruit object
            selecFruit = listFruit.get(selected - 1);
            if (selecFruit.getQuantity() <= 0) {
                checked = true;
                System.out.println("The fruit you chose is out of stock!");
            } else {
                checked = false;

            }
        } while (checked == true);

        System.out.println("You selected: " + selecFruit.getNameFruit());
        boolean check = true;
        int quantity = 0;
        do {
            //enter the quantity
            quantity = IO.getInteger("Please input quantity: ", "Number must be greater than 0", 1);
            //If the quantity entered is more than the existing quantity, then print a message
            if (quantity > selecFruit.getQuantity()) {
                System.out.println("The number of requests exceeds the number of existing ones!");
            } else {
                String nameF = selecFruit.getNameFruit();
                check = true;
                //if name exits then add quantity
                for (Order order : listItemBought) {
                    if (nameF.equals(order.getNameFruit())) {
                        order.setQuantity(order.getQuantity() + quantity);
                        check = false;
                    }
                }
                if (check == true) {
                    //add fruit attributes to order list
                    listItemBought.add(new Order(selecFruit.getNameFruit(), quantity, selecFruit.getPrice()));
                }
                int tempQuantity = selecFruit.getQuantity();
                //update the existing quantity
                selecFruit.setQuantity(tempQuantity - quantity);
                check = false;
            }

        } while (check == true);

    }

    /**
     * Method to print out invoices and add to the table containing customer
     * invoices
     *
     * @param listItemBought list fruit bought
     */
    public void bill(List<Order> listItemBought) {
        //print bill
        System.out.println("Product      | Quantity | Price | Amount ");
        double total = 0;
        for (Order f : listItemBought) {
            System.out.printf("%-18s%-8s%-8s%-10s\n", f.getNameFruit(), f.getQuantity(), f.getPrice(), f.getAmount());
            total += f.getAmount();
        }
        System.out.println("Total: " + total);
        for (Order order : listItemBought) {
            addCus.add(order);
        }
        listItemBought.clear();
        //ask enter name
        String nameCustomer = IO.getString("Input your name: ", "Not allow empty string! Pls enter again!");
        //add hashtabel with key is name customer, value is list fruit bought
        String name = formatWord(nameCustomer);
        String finalName = setName(name);
        viewOrder.put(finalName, addCus);

    }

    /**
     * Method use to view the invoices of previous customers
     */
    public void viewOrders() {
        //if hash table empty then print message
        if (viewOrder.isEmpty()) {
            System.out.println("Haven't Bill!");
        } else {
            Enumeration<String> nameCustomerList = viewOrder.keys();
            while (nameCustomerList.hasMoreElements()) {
                //print list bill have name customer
                String nameCustomer = nameCustomerList.nextElement();
                String nameCus = nameCustomer.substring(0, nameCustomer.length() - 2);
                System.out.println("Customer: " + nameCus);
                System.out.println("Product      | Quantity | Price | Amount ");
                double total = 0;
                for (Order f : viewOrder.get(nameCustomer)) {
                    System.out.printf("%-18s%-8s%-8s%-10s\n", f.getNameFruit(), f.getQuantity(), f.getPrice(), f.getAmount());
                    total += f.getAmount();
                }
                System.out.println("Total: " + total);
            }
        }
    }

    /**
     * Reformat the string, the first character of each letter is uppercase, and
     * remove redundant spaces
     *
     * @param str string input
     * @return formatted string
     */
    public String formatWord(String str) {
        String[] arr = str.trim().split("\\s+");
        String resad = "";
        //iterate over each element to capitalize the first letter
        for (int i = 0; i < arr.length; i++) {
            resad += arr[i].substring(0, 1).toUpperCase() + arr[i].substring(1).toLowerCase() + " ";
        }
        return resad.substring(0, resad.length());
    }

    /**
     * requires the user to enter the name according to the correct value, must
     * be at least 1 letter and contain no special characters and write numbers
     * or letters first
     *
     * @param str string input
     * @return a valid name , can include number and alphabet
     */
    public String setName(String str) {
        String name;
        do {
//            name = IO.getString("Enter name:", "[A-Za-z0-9\\\\s ]*[A-Za-z ][A-Za-z0-9\\\\s ]*");
            name = str.trim();
            for (String name_key : viewOrder.keySet()) {//browse the orders in the hash table
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
        return name + "-" + count;//automatically assigns a name followed by a - and then the number of occurrences of that name
    }
}
