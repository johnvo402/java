/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ce171515_graph_q8;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
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
    String resultFindVertex = "";
    int[] distance = new int[MAX_VERTEX];
    int[] theVertexBefore = new int[MAX_VERTEX];
    int sumPrim, sumKruskal;
    int[] parentKruskal = new int[MAX_VERTEX];
    
    String resultIsolated = "";
    
    ArrayList<Vertex> listCutVertices = new ArrayList<>();
    ArrayList<Edge> listEdgesKruskal = new ArrayList<>();
    
    int start, end;
    
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
            //this.start = sc.nextInt();
            //this.end = sc.nextInt();

            for (int i = 0; i < numberOfVerties; i++) {
                for (int j = 0; j < numberOfVerties; j++) {
                    graph[i][j] = sc.nextInt();
                    if (graph[i][j] > 0 && i < j) {
                        edges.add(new Edge(new Vertex(j), new Vertex(i), graph[i][j]));
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
                graph[start][end] = graph[end][start] = 1;
                edges.add(new Edge(new Vertex(start), new Vertex(end), 1));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

//    public void isolatedVertices() {
//        int j;
//        for (int i = 0; i < numberOfVerties; i++) {
//            for (j = 0; j < numberOfVerties; j++) {
//                if (graph[i][j] > 0) {
//                    break;
//                }
//            }
//            if (j == numberOfVerties) {
//                resultIsolated += "," + i;
//                listIsolated.add(i);
//            }
//        }
//    }
//    public void findCC() {
//        listCC.clear();
//        for (int i = 0; i < numberOfVerties; i++) {
//            if (isVisited[i] == false) {
//                resultDFS = "";
//                DFS(i);
//                listCC.add(resultDFS);
//            }
//        }
//    }
    public void resetIsVisited() {
        for (int i = 0; i < MAX_VERTEX; i++) {
            isVisited[i] = false;
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
        int minIndex = 1;
        int minValue = Integer.MAX_VALUE;
        for (int i = 0; i < numberOfVerties; i++) {
            if (isVisited[i] == false && distance[i] < minValue) {
                minValue = distance[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    public void findCutVertex() {
        int theNumberOfConnectedComponent = 0;
        for (int i = 0; i < numberOfVerties; i++) {
            if (isVisited[i] == false) {
                theNumberOfConnectedComponent++;
                DFS(i);
            }
        }
        System.out.println("theNumberOfConnectedComponent: " + theNumberOfConnectedComponent);
        for (int i = 0; i < numberOfVerties; i++) {
            resetIsVisited();
            isVisited[i] = true;
            int count = 0;
            for (int j = 0; j < numberOfVerties; j++) {
                if (isVisited[j] == false) {
                    count++;
                    DFS(j);
                }
            }
            System.out.println("i: " + i + " - count: " + count);
            if (count > theNumberOfConnectedComponent) {
                listCutVertices.add(new Vertex(i));
            }
        }
    }

    public void findVertex(int start) {
        resultFindVertex = "";
        resetIsVisited();
        Stack<Integer> s = new Stack<>();
        s.push(0);
        int fromVertex;
        while (!s.isEmpty()) {
            fromVertex = s.pop();
            if (isVisited[fromVertex] == false) {
                if (fromVertex >= startTraversal) {
                    resultFindVertex += "," + fromVertex;
                }
                isVisited[fromVertex] = true;
                for (int toVertex = numberOfVerties - 1; toVertex >= 0; toVertex--) {
                    if (graph[fromVertex][toVertex] > 0 && isVisited[toVertex] == false) {
                        s.push(toVertex);
                    }
                }
            }
        }
    }

    public void prim() {
        resetIsVisited();
        resetDistance();
        resetTheVertexBefore();
        sumPrim = 0;
        distance[0] = 0;
        int current;
        for (int i = 0; i < numberOfVerties; i++) {
            current = findNearestVertex();
            sumPrim += distance[current];
            isVisited[current] = true;
            for (int toVertex = 0; toVertex < numberOfVerties; toVertex++) {
                if (graph[current][toVertex] > 0
                        && isVisited[toVertex] == false
                        && graph[current][toVertex] < distance[toVertex]) {
                    distance[toVertex] = graph[current][toVertex];
                    theVertexBefore[toVertex] = current;
                }
            }
        }
    }

    public void make_Set() {
        for (int i = 0; i < numberOfVerties; i++) {
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
        make_Set();
        Collections.sort(edges);
        sumKruskal = 0;
        for (Edge edge : edges) {
            if (union(edge.start.value, edge.end.value)) {
                listEdgesKruskal.add(edge);
                sumKruskal += edge.weight;
            }
        }
    }

    public void DFS(int start) {
        resultDFS = "";
//        resetIsVisited();
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

    public void writeDataToFile(String fileName) {
        try {
            findCutVertex();
            FileWriter fw = new FileWriter(new File(fileName));
            fw.write(listCutVertices.size()+"\r\n");
            String str = "";
            for (Vertex v: listCutVertices) {
                str += "," + v.value;
            }
            fw.write(str.substring(1));
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
            System.out.println();
        }
    }

}
