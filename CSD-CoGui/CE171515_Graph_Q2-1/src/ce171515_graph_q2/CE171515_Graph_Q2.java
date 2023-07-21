/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ce171515_graph_q2;

/**
 *
 * @author Le Chi Khiem - CE171515
 */
public class CE171515_Graph_Q2 {

    Graph graph = new Graph();
    String fi = "mst_input.txt";
    String fo = "mst_output.txt";

    void setFile(String[] args) {
        fi = args.length >= 2 ? args[0] : fi;
        fo = args.length >= 2 ? args[1] : fo;
    }

    void readFile() {
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
        CE171515_Graph_Q2 q2 = new CE171515_Graph_Q2();

        q2.setFile(args);
        q2.readFile();
        q2.writeToFile();
    }

}
