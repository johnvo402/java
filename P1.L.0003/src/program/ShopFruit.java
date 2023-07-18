package program;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import object.Fruit;

/**
 *
 * @author Vo Thanh Thu - CE170522
 */
public class ShopFruit {

    private ArrayList<Fruit> listFruit = new ArrayList<>();
    private ArrayList<Fruit> listOrder = new ArrayList<>();
    private int nextID = 1;
    Fruit fr;
    private int count = 0;

    private Hashtable<String, ArrayList<Fruit>> viewOrder = new Hashtable<>();

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
     * Display a list of fruits for buyers to choose from
     *
     * @return
     *
     */
    public int displayShopping() {
        int nextNo = 1;
        if (listFruit.isEmpty()) {
            System.out.println("Out of stock, please pay!");
            return -1;
        }
        //print out the list of fruits
        System.out.println("List of Fruit:");
        System.out.println("|     No.    |    Fruit Name    |    Origin    |    Price    |");
        for (Fruit f : listFruit) {
            System.out.printf("       %-13s%-18s%-15s%-10s\n", nextNo++, f.getNameFruit(), f.getOrigin(), f.getPrice());
        }
        //request to choose
        int selected = IO.getInteger("Enter item you want order: ", "Number must be from 1 to " + listFruit.size(), 1, listFruit.size());
        return selected;

    }

    /**
     * Method for non-manager users to use and buy fruit
     */
    public void shopping() {
        //If there are no fruits, a message will be printed
        if (listFruit.isEmpty()) {
            System.out.println("Haven't Fruit!");
            return;
        }
        ArrayList<Fruit> listOrder = new ArrayList<>();
        HashMap<Integer, Integer> haveQuantity = new HashMap<>();
        //call method to order
        order(haveQuantity, listOrder);
        if (!listOrder.isEmpty()) {//If listOrder is not empty, the code proceeds to calculate the total amount for the ordered fruits
            List<Fruit> onlyOrder = new ArrayList<>();
            //Browse through each product in the listOrder
            for (Fruit fruit : listOrder) {
                boolean isDup = false;
                //check if product already exists in onlyOrder
                for (Fruit unique : onlyOrder) {
                    if (fruit.getIdFruit() == (unique.getIdFruit())) {
                        isDup = true;
                        break;
                    }
                }
                if (!isDup) {
                    onlyOrder.add(fruit);
                }
            }
            bill(onlyOrder, listOrder);
        }
    }

    /**
     * The method used to order when the user selects a certain fruit, the user
     * must enter the quantity
     *
     * @param haveQuantity to store the cumulative number of each fruit
     * @param listOrder list of fruits to be purchased
     */
    public void order(HashMap<Integer, Integer> haveQuantity, ArrayList<Fruit> listOrder) {
        //loop allows the user to select fruits for ordering until they choose to stop.
        while (true) {
            int num = displayShopping();//call method to take number choice
            if (num == -1) {
                break;
            }
            Fruit fruit = listFruit.get(num - 1);
            System.out.println("-Your selected: " + fruit.getNameFruit());
            int quantity = IO.getInteger("Enter quantity : ", "", 0, fruit.getQuantity());
            fruit.setQuantity(fruit.getQuantity() - quantity);
            Fruit fruitInOrder = checkExist(fruit.getIdFruit());
            //If the fruit type already exists in listOrder
            if (fruitInOrder != null) {
                fruitInOrder.setQuantity(fruitInOrder.getQuantity() + quantity);
                haveQuantity.put(fruit.getIdFruit(), haveQuantity.get(fruit.getIdFruit()) + quantity);
            } else {
                //If the selected fruit does not exist in listOrder and the quantity is not 0
                if (quantity != 0) {
                    boolean isDup = false;
                    //browse orderedFruit in list Ordered
                    for (Fruit orderedFruit : listOrder) {
                        if (orderedFruit.getIdFruit() == (fruit.getIdFruit())) {
                            orderedFruit.setQuantity(orderedFruit.getQuantity() + quantity);
                            isDup = true;
                            break;
                        }
                    }
                    if (!isDup) {
                        listOrder.add(new Fruit(fruit.getIdFruit(), fruit.getNameFruit(), fruit.getPrice(), quantity, fruit.getOrigin()));
                    }
                    haveQuantity.put(fruit.getIdFruit(), quantity);
                }
            }
            // Remove fruits with quantity 0 from listOrder
            Iterator<Fruit> iterator = listFruit.iterator();
            while (iterator.hasNext()) {
                Fruit fruitPicked = iterator.next();
                if (fruitPicked.getQuantity() == 0) {
                    iterator.remove();
                }
            }
            if (!nextProgram("Do you want to order more(Y/N)? ")) {
                break;
            }
        }

    }

    /**
     * Method to print out invoices and add to the table containing customer
     * invoices
     *
     * @param onlyOrder list fruit bought current
     * @param listOrder list fruit bought
     */
    public void bill(List<Fruit> onlyOrder, ArrayList<Fruit> listOrder) {
        //print bill
        System.out.println("Product      | Quantity | Price | Amount ");
        double total = 0;
        //prin bill
        for (Fruit f : onlyOrder) {
            double amount = f.getPrice() * f.getQuantity();
            System.out.printf("%-18s%-8s%-8s%-10s\n", f.getNameFruit(), f.getQuantity(), f.getPrice(), amount);
            total += amount;
        }
        System.out.println("Total: " + total);
        //ask enter name
        String nameCustomer = IO.getString("Input your name: ", "Not allow empty string! Pls enter again!");
        //add hashtabel with key is name customer, value is list fruit bought
        String name = formatWord(nameCustomer);
        String finalName = setName(name);
        viewOrder.put(finalName, listOrder);
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
                for (Fruit f : viewOrder.get(nameCustomer)) {
                    double amount = f.getPrice() * f.getQuantity();
                    System.out.printf("%-18s%-8s%-8s%-10s\n", f.getNameFruit(), f.getQuantity(), f.getPrice(), amount);
                    total += amount;
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
            name = str.trim();
            //browse the orders in the hash table
            for (String name_key : viewOrder.keySet()) {
                //marked with -
                int index = name_key.indexOf("-");
                if (index != -1) {
                    String nameNotCount = name_key.substring(0, index);
                    if (name.equals(nameNotCount)) {
                        count++;
                    }
                }
            }
        } while (name.isEmpty());
        System.out.println("Order information saved");
        return name + "-" + count;
    }

    /**
     * check if the product ordered by the user is in stock by id , if yes then
     * return that product
     *
     * @param id : id input is entered by the user to find the fruit to order
     * @return fruit found by id
     */
    public Fruit checkExist(int id) {
        ArrayList<Fruit> listOrder = new ArrayList<>();
        //each Fruit object in the inStock list using an enhanced for loop.
        for (Fruit fruit : listOrder) {
            if (fruit.getIdFruit() == (id)) {
                return fruit;
            }
        }
        return null;
    }
}
