/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ce171515_graph_q2;

import java.util.Comparator;

/**
 *
 * @author Le Chi Khiem - CE171515
 */
public class Edge implements Comparable<Edge> {

    Vertex start;
    Vertex end;
    int weight;

    public Edge() {
    }

    public Edge(Vertex start, Vertex end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    public int compareTo(Edge edge) {
        return Integer.compare(this.weight, edge.weight);
    }
}
