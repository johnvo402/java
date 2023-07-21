/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ce171515_graph_q2;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author Le Chi Khiem - CE171515
 */
public class Graph {

    int[][] graph;
    static final int MAX_VERTEX = 100;
    int numberOfVerties;
    List<Edge> edges = new ArrayList<>();
    boolean[] isVisited = new boolean[MAX_VERTEX];
    int startTraversal;
    String resultDFS = "";
    String resultBFS = "";
    String resultPrime = "";
    String resultKruskal = "";
    int sumWeight = 0;
    ArrayList<Edge> listEdgesKrukal = new ArrayList<>();
    int[] distance = new int[MAX_VERTEX];
    int[] theVertexBefore = new int[MAX_VERTEX];
    int[] parentKruskal = new int[MAX_VERTEX];
    int sumPrim = 0;
    int sumKruskal = 0;
    boolean isConectedComponent;

    public Graph() {
        this.graph = new int[MAX_VERTEX][MAX_VERTEX];
        for (int i = 0; i < MAX_VERTEX; i++) {
            for (int j = 0; j < MAX_VERTEX; j++) {
                graph[i][j] = 0;
            }
        }
    }

    public void readMatrixDataFile(String fileName) {
        try {
            Scanner sc = new Scanner(new File(fileName));
            this.numberOfVerties = sc.nextInt();
            //this.startTraversal = sc.nextInt();
            for (int i = 0; i < numberOfVerties; i++) {
                for (int j = 0; j < numberOfVerties; j++) {
                    graph[i][j] = sc.nextInt();
                    if (graph[i][j] > 0 && i < j) {
                        edges.add(new Edge(new Vertex(i), new Vertex(j), graph[i][j]));
                    }

                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void readListDataFile(String fileName) {
        try {
            Scanner sc = new Scanner(new File(fileName));
            this.numberOfVerties = sc.nextInt();
            int countEdge = sc.nextInt();
            this.startTraversal = sc.nextInt();
            int start, end;

            for (int i = 0; i < countEdge; i++) {
                start = sc.nextInt();
                end = sc.nextInt();
                graph[start][end] = 1;
                graph[end][start] = 1;
                //edges.add(new Edge(new Vertex(start), new Vertex(end), 1));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void resetIsVisited() {
        for (int i = 0; i < MAX_VERTEX; i++) {
            isVisited[i] = false;
        }
    }

    public void DFS(int start) {
        resultDFS = "";
        resetIsVisited();
        Stack<Integer> s = new Stack<>();
        s.push(start);
        int fromVertex;

        while (!s.isEmpty()) {
            fromVertex = s.pop();
            if (isVisited[fromVertex] == false) {
                resultDFS += "," + fromVertex;
                isVisited[fromVertex] = true;
                for (int toVertex = numberOfVerties - 1; toVertex >= 0; toVertex--) {
                    if (graph[fromVertex][toVertex] > 0 && isVisited[toVertex] == false) {
                        s.push(toVertex);
                    }
                }
            }
        }
    }

    public void BFS(int start) {
        resultBFS = "";
        resetIsVisited();
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        int fromVertex;

        while (!q.isEmpty()) {
            fromVertex = q.poll();
            if (isVisited[fromVertex] == false) {
                resultBFS += "," + fromVertex;
                isVisited[fromVertex] = true;
                for (int toVertex = 0; toVertex < numberOfVerties; toVertex++) {
                    if (graph[fromVertex][toVertex] > 0 && isVisited[toVertex] == false) {
                        q.add(toVertex);
                    }
                }
            }
        }
    }

    public void resetDistance() {
        for (int i = 0; i < MAX_VERTEX; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
    }

    public void resetTheVertexBefore() {
        for (int i = 0; i < MAX_VERTEX; i++) {
            theVertexBefore[i] = i;
        }
    }

    public int findNearestVertex() {
        int minIndex = -1;
        int minValue = Integer.MAX_VALUE;
        for (int i = 0; i < numberOfVerties; i++) {
            if (distance[i] < minValue && isVisited[i] == false) {
                minValue = distance[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    public void prim() {
        resetIsVisited();
        resetDistance();
        resetTheVertexBefore();
        int current;
        this.sumPrim = 0;
        this.isConectedComponent = true;
        distance[0] = 0;
        for (int i = 0; i < numberOfVerties; i++) {
            current = findNearestVertex();
            if (current == -1) {
                this.isConectedComponent = false;
                return;
            }
            sumPrim += distance[current];
            isVisited[current] = true;
            for (int toVertex = 0; toVertex < numberOfVerties; toVertex++) {
                if (isVisited[toVertex] == false && graph[current][toVertex] > 0
                        && graph[current][toVertex] < distance[toVertex]) {
                    distance[toVertex] = graph[current][toVertex];
                    theVertexBefore[toVertex] = current;
                }
            }
        }
    }

    public void makeSet() {
        for (int i = 0; i < MAX_VERTEX; i++) {
            parentKruskal[i] = i;
        }
    }

    public int findParent(int v) {
        if (v == parentKruskal[v]) {
            return v;
        }
        return findParent(parentKruskal[v]);
    }

    public boolean union(int a, int b) {
        a = findParent(a);
        b = findParent(b);
        if (a == b) {
            return false;
        }
        parentKruskal[a] = b;
        return true;
    }

    public void kruskal() {
        makeSet();
        listEdgesKrukal.clear();
        sumKruskal = 0;
        Collections.sort(edges);
        for (int i = 0; i < edges.size(); i++) {
            if (listEdgesKrukal.size() == numberOfVerties - 1) {
                break;
            }
            if (union(edges.get(i).start.value, edges.get(i).end.value)) {
                listEdgesKrukal.add(edges.get(i));
                sumKruskal += edges.get(i).weight;
            }
        }
    }

    public void writeDataToFile(String fileName) {
        prim();
        kruskal();
        System.out.println("sumPrim: " + this.sumPrim);
        try {
            FileWriter fw = new FileWriter(new File(fileName));
            fw.write("Prim:\n");
            fw.write(sumPrim + "\n");
            for (int i = 0; i < numberOfVerties; i++) {
                fw.write(theVertexBefore[i] + " " + i + " " + graph[theVertexBefore[i]][i] + "\n");

            }
            fw.write("Kruskal:\n");
            fw.write(sumKruskal + "\n");
            for (Edge edge : listEdgesKrukal) {
                fw.write(edge.start.value + " " + edge.end.value + " " + edge.weight + "\n");
            }
            fw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void print() {
        for (int i = 0; i < numberOfVerties; i++) {
            for (int j = 0; j < numberOfVerties; j++) {
                System.out.print(graph[i][j]);
            }
            System.out.println("\n");
        }
    }

}
