package program;

import java.util.Scanner;

/**
 *
 * @author Vo Thanh Thu - CE170522
 */
public class IO {

    /**
     * Get input from the console and continue repeating the process until
     * valid. Display an error message if the input is not valid.
     *
     * @param iMsg the message before input
     * @param iErr The message the user want to print if the input is invalid
     * @return Integer number
     */
    public static int getInteger(String iMsg, String iErr) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.print(iMsg);
                int n = Integer.parseInt(sc.nextLine());
                return n;
            } catch (NumberFormatException e) {
                System.out.println(iErr);
            }
        }
    }

    /**
     * Get input from the console and continue repeating the process until
     * valid. Display an error message if the input is not valid.
     *
     * @param iMsg the message before input
     * @param iErr The message the user want to print if the input is invalid
     * @param star lower bound
     * @return Integer number
     */
    public static int getInteger(String iMsg, String iErr, int star) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.print(iMsg);
                int n = Integer.parseInt(sc.nextLine());
                if (n < star) {
                    throw new Exception();
                }
                return n;
            } catch (Exception e) {

                System.out.println(iErr);
            }
        }
    }

    /**
     * Get input from the console and continue repeating the process until
     * valid. Display an error message if the input is not valid.
     *
     * @param iMsg the message before input
     * @param iErr The message the user want to print if the input is invalid
     * @param star lower bound
     * @param end upper bound
     * @return Integer number
     */
    public static int getInteger(String iMsg, String iErr, int star, int end) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.print(iMsg);
                int n = Integer.parseInt(sc.nextLine());
                if (n < star || n > end) {
                    throw new Exception();
                }
                return n;
            } catch (Exception e) {

                System.out.println(iErr);
            }
        }
    }

    /**
     * Get input from the console and continue repeating the process until
     * valid. Display an error message if the input is not valid.
     *
     * @param iMsg the message before input
     * @param iErr The message the user want to print if the input is invalid
     * @return double number
     */
    public static double getDouble(String iMsg, String iErr) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.print(iMsg);
                double n = Double.parseDouble(sc.nextLine());
                return n;
            } catch (NumberFormatException e) {
                System.out.println(iErr);
            }
        }
    }

    /**
     * Get input from the console and continue repeating the process until
     * valid. Display an error message if the input is not valid.
     *
     * @param iMsg the message before input
     * @param iErr The message the user want to print if the input is invalid
     * @param star lower bound
     * @param end upper bound
     * @return double number
     */
    public static double getDouble(String iMsg, String iErr, int start, int end) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.print(iMsg);
                double n = Double.parseDouble(sc.nextLine());
                if (n < start || n > end) {
                    throw new Exception();
                }
                return n;
            } catch (Exception e) {

                System.out.println(iErr);
            }
        }
    }

    /**
     * Get input from the console and continue repeating the process until
     * valid. Display an error message if the input is not valid.
     *
     * @param iMsg the message before input
     * @param iErr The message the user want to print if the input is invalid
     * @return String
     */
    public static String getString(String iMsg, String iErr) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.print(iMsg);
                String s = sc.nextLine();
                if (s.length() == 0) {
                    throw new Exception();
                }
                return s;
            } catch (Exception e) {
                System.out.println(iErr);
            }
        }
    }

    /**
     * Check the operator and output the message
     *
     * @param iMsg the message before input
     * @param iErr The message the user want to print if the input is invalid
     * @return String
     */
    public static String checkOperator(String iMsg, String iErr) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print(iMsg);
            String result = sc.nextLine().trim();
            if (result.isEmpty()) {
                System.err.println("Not empty");
            } else if (result.equalsIgnoreCase("+") || result.equalsIgnoreCase("-")
                    || result.equalsIgnoreCase("*") || result.equalsIgnoreCase("/")
                    || result.equalsIgnoreCase("^") || result.equalsIgnoreCase("=")) {
                return result;
            } else {
                System.err.println(iErr);
            }

        }
    }

    /**
     * Get input from the console and continue repeating the process until
     * valid. Display an error message if the input is not valid.
     *
     * @param iMsg the message before input
     * @param iErr The message the user want to print if the input is invalid
     * @param star lower bound
     * @return Integer number
     */
    public static int getIntegers(String iMsg, String iErr, int star) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print(iMsg);
            int n = Integer.parseInt(sc.nextLine());
            if (n < star) {
                System.out.println(iErr);
            }
            return n;
        }
    }
}
