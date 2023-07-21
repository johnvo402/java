/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ce171515_graph_06;

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
    String resultIsolated = "";
    ArrayList<Integer> listIsolated = new ArrayList<>();
    ArrayList<String> listCC = new ArrayList<>();
    
    int[] theVervexBefore = new int[MAX_VERTEX];
    int start, end;
    int[] distance = new int[MAX_VERTEX];
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
            this.start = sc.nextInt();
            this.end = sc.nextInt();
            
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
    
    public void isolatedVertices() {
        int j;
        for (int i = 0; i < numberOfVerties; i++) {
            for (j = 0; j < numberOfVerties; j++) {
                if (graph[i][j] > 0) {
                    break;
                }
            }
            if (j == numberOfVerties) {
                resultIsolated += "," + i;
                listIsolated.add(i);
            }
        }
    }
    
    public void findCC() {
        listCC.clear();
        for (int i = 0; i < numberOfVerties; i++) {
            if (isVisited[i] == false) {
                resultDFS = "";
                DFS(i);
                listCC.add(resultDFS);
            }
        }
    }
    
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
            theVervexBefore[i] = i;
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
    
    public void dijkstra() {
        resetIsVisited();
        resetDistance();
        resetTheVertexBefore();
        
        distance[start] = 0;
        int current;
        isConectedComponent = true;
        for (int i = 0; i < numberOfVerties; i++) {
            current = findNearestVertex();
            if (current == -1) {
                isConectedComponent = false;
                break;
            } else {
                isVisited[current] = true;
                for (int toVertex = 0; toVertex < numberOfVerties; toVertex++) {
                    if (isVisited[toVertex] == false && graph[current][toVertex] > 0
                            && distance[current] + graph[current][toVertex] < distance[toVertex]) {
                        distance[toVertex] = distance[current] + graph[current][toVertex];
                        theVervexBefore[toVertex] = current;
                    }
                }
            }
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
    
    public void writeDataToFile(String fileName) {
        dijkstra();
        try {
            FileWriter fw = new FileWriter(new File(fileName));
            String path = "" + end;
            int current = end;
            while (current != start) {
                current = theVervexBefore[current];
                path = current + "->" + path;
            }
            path += ": " + distance[end];
            fw.write(path);
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
