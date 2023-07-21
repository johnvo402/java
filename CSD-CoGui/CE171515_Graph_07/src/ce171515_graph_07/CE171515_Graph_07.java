package ce171515_graph_07;

public class CE171515_Graph_07 {

    Graph graph = new Graph();
    String fi = "ex07_input.txt";
    String fo = "ex07_output.txt";

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
        CE171515_Graph_07 q3 = new CE171515_Graph_07();

        q3.setFile(args);
        q3.readFile();
        q3.writeToFile();
    }
}


