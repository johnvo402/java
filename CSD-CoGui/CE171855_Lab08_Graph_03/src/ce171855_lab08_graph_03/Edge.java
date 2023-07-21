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
public class Edge implements Comparable<Edge> {

    Vertex start;
    Vertex end;
    int weight;

    public Edge(Vertex start, Vertex end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        return this.weight - o.weight;
    }

    public Vertex getStart() {
        return start;
    }

    public Vertex getEnd() {
        return end;
    }

    public int getWeight() {
        return weight;
    }

}
