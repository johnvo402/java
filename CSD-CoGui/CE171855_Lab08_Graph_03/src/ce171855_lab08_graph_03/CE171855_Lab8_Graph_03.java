/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ce171855_lab08_graph_03;

/**
 *
 * @author La Hoang Khoi - CE171855
 */
public class CE171855_Lab8_Graph_03 {

    Graph graph = new Graph();
    String fi = "isolatedVertices_input.txt";
    String fo = "isolatedVertices_output.txt";

    void setFile(String[] args) {
        fi = args.length >= 2 ? args[0] : fi;
        fo = args.length >= 2 ? args[1] : fo;
    }

    void readFile() {
        //   graph.readListDataFile(fi);
        graph.readMatrixDataFile(fi);
    }

    void writeToFile() {
        graph.writeDataToFile(fo);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        CE171855_Lab8_Graph_03 q1 = new CE171855_Lab8_Graph_03();
        q1.setFile(args);
        q1.readFile();
        q1.writeToFile();
    }

}
