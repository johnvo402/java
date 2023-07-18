/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

/**
 *
 * @author Vo Thanh Thu - CE170522
 */
public class Order {

    private String nameFruit;
    private int quantity;
    private double price;
    private double amount;

    /**
     * Constructor empty for call
     */
    public Order() {

    }

    /**
     * Constructor create Order object for list of purchased fruits
     *
     * @param nameFruit name of fruit
     * @param quantity quantity of fruit
     * @param price price of fruit
     */
    public Order(String nameFruit, int quantity, double price) {
        this.nameFruit = nameFruit;
        this.quantity = quantity;
        this.price = price;
        this.amount = quantity * price;
    }

    /**
     * get the name of fruit
     *
     * @return name fruit
     */
    public String getNameFruit() {
        return nameFruit;
    }

    /**
     * get the quantity of fruit
     *
     * @return quantity fruit
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * get the price of fruit
     *
     * @return price fruit
     */
    public double getPrice() {
        return price;
    }

    /**
     * Update new quantity of fruit
     *
     * @param quantity new quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * get the amount of bill
     *
     * bill* @return amount
     */
    public double getAmount() {

        return amount;
    }

}
