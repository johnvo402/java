package object;

/**
 *
 * @author Vo Thanh Thu - CE170522
 */
public class Fruit {

    private int idFruit;
    private String nameFruit;
    private double price;
    private int quantity;
    private String origin;

    /**
     * Constructor creates a Fruit object with the necessary properties
     *
     * @param idFruit fruit id
     * @param nameFruit name fruit
     * @param price price of fruit
     * @param quantity quantity of fruit
     * @param origin origin of fruit
     */
    public Fruit(int idFruit, String nameFruit, double price, int quantity, String origin) {
        this.idFruit = idFruit;
        //call method format name to capitalize the first letter of each letter
        this.nameFruit = formatWord(nameFruit);
        this.price = price;
        this.quantity = quantity;
        //call method format origin to capitalize the first letter of each letter
        this.origin = formatWord(origin);
    }

    /**
     * Constructor empty for call
     */
    public Fruit() {
    }

    /**
     * get the Id of the fruit
     *
     * @return id fruit
     */
    public int getIdFruit() {
        return idFruit;
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
     * get the price of fruit
     *
     * @return price of fruit
     */
    public double getPrice() {
        return price;
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
     * get the origin of fruit
     *
     * @return origin fruit
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * update the quantity of fruit
     *
     * @param quantity new quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

}
