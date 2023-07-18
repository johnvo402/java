/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package L03;

/**
 *
 * @author Tran Tri Tin - CE172062
 */
public class Fruit {

    private String ID;
    private String Name;
    private double price;
    private int quantity;
    private String origin;

    /**
     * Create the constructor
     */
    public Fruit() {
    }

    /**
     * create the constructor with parameters
     *
     * @param ID : valid id that is enter by user
     * @param name: valid name that is enter by user
     * @param price: valid price that is enter by user
     * @param quantity: valid quantity that is enter by user
     * @param origin: valid origin that is enter by user
     */
    public Fruit(String ID, String name, double price, int quantity, String origin) {
        this.ID = ID;
        this.Name = name;
        this.price = price;
        this.quantity = quantity;
        this.origin = origin;
    }

    /**
     * get the ID entered by the user ,( the value of the object's ID), method
     * returns String
     *
     * @return valid ID (not duplicate)
     */
    public String getID() {
        return ID;
    }

    /**
     * to set the value of the ID for the object. method has one parameter
     * fruitId of type String, The method assigns the value of fruitId to the ID
     * variable using the this keyword.
     *
     * @param fruitId : ID of fruit that is enter by user
     */
    public void setID(String fruitId) {
        this.ID = fruitId;
    }

    /**
     * get the Name entered by the user ,( the value of the object's Name),
     * method returns String
     *
     * @return valid Name (only include alphabet and number)
     */
    public String getName() {
        return Name;
    }

    /**
     * to set the value of the Name for the object. method has one parameter
     * fruitName of type String, The method assigns the value of fruitName to
     * the Name variable using the this keyword
     *
     * @param fruitName : Name of fruit that is enter by user
     */
    public void setName(String fruitName) {
        this.Name = fruitName;
    }

    /**
     * get the price entered by the user ,( the value of the object's price),
     * method returns double
     *
     * @return valid price : double , not alphabet and special character
     */
    public double getPrice() {
        return price;
    }

    /**
     * to set the value of the price for the object. method has one parameter
     * price of type double, The method assigns the value of price to the price
     * variable using the this keyword
     *
     * @param price : price of fruit that is enter by user
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * get the quantity entered by the user ,( the value of the object's
     * quantity), method returns integer
     *
     * @return valid quantity : integer , not alphabet and special character
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * to set the value of the quantity for the object. method has one parameter
     * quantity of type integer, The method assigns the value of quantity to the
     * quantity variable using the this keyword
     *
     * @param quantity : quantity of fruit that is enter by user
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * get the origin entered by the user ,( the value of the object's origin),
     * method returns String
     *
     * @return valid origin (only include alphabet and number)
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * to set the value of the origin for the object. method has one parameter
     * origin of type String, The method assigns the value of origin to the
     * origin variable using the this keyword
     *
     * @param origin : origin of fruit that is enter by user
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }
}
