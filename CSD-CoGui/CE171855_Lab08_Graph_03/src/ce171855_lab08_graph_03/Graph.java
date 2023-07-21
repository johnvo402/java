/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ce171855_lab08_graph_03;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author La Hoang Khoi - CE171855
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
    String resultIsolated = "";

    public Graph() {
        this.graph = new int[MAX_VERTEX][MAX_VERTEX];
        for (int i = 0; i < MAX_VERTEX; i++) {
            for (int j = 0; j < MAX_VERTEX; j++) {
                graph[i][j] = 0;
            }
        }
    }

    public void readListDataFile(String fileName) {
        try {
            Scanner sc = new Scanner(new File(fileName));
            //8 10 0
            this.numberOfVerties = sc.nextInt();  //10
            int countEdge = sc.nextInt(); //8
            this.startTraversal = sc.nextInt(); //0
            int start, end;

            for (int i = 0; i < countEdge; i++) {
                start = sc.nextInt();
                end = sc.nextInt();
                graph[start][end] = 1;
                graph[end][start] = 1;
                edges.add(new Edge(new Vertex(start), new Vertex(end), 1));
            }

        } catch (Exception e) {
        }
    }

    public void print() {
        for (int i = 0; i < numberOfVerties; i++) {
            for (int j = 0; j < numberOfVerties; j++) {
                System.out.print(graph[i][j]);
            }
            System.out.println("");
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

//                if (fromVertex >= 5) {
//                    resultDFS += "," + fromVertex; //dieu kien check
//                }
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

    public void findIsolatedVertices() {
        resultIsolated = "";
        resetIsVisited();
        int count = 0;
        List<Vertex> isolatedVertices = new ArrayList<>();
        for (int i = 0; i < numberOfVerties; i++) {
            boolean isIsolated = true;
            for (int j = 0; j < numberOfVerties; j++) {
                if (graph[i][j] != 0 || graph[j][i] != 0) {
                    isIsolated = false;
                    break;
                }
            }
            if (isIsolated) {
                isolatedVertices.add(new Vertex(i));
                count++;
            }
        }
        if (count == 0) {
            System.out.println("There is a connected graph");
            resultIsolated += " There is a connected graph";
        } else {
            for (Vertex a : isolatedVertices) {
                resultIsolated += "," + a.value;
                System.out.println(a.value);
            }
        }

    }

    public void readMatrixDataFile(String fileName) {
        try {
            Scanner sc = new Scanner(new File(fileName));
            this.numberOfVerties = sc.nextInt();
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

    public void writeDataToFile(String fileName) {
        findIsolatedVertices();
        try {
            FileWriter out = new FileWriter(new File(fileName));
            out.write(resultIsolated.substring(1));

            out.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
