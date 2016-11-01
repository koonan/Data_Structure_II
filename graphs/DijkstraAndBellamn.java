package eg.edu.alexu.csd.filestructure.graphs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DijkstraAndBellamn implements IGraph {

  private Scanner reader;

  private int verticesNum = 0, edgesNum = 0;
  private int from, to, weight;
  private MyEdge<Integer, Integer, Integer> edge;

  private ArrayList<MyEdge<Integer, Integer, Integer>> graph;

  private ArrayList<Integer> arr;
  private ArrayList<Integer> vertices;
  private String line;

  private boolean[] sptSet;
  private int[][] graph1;

  @Override
  public void readGraph(File file) {
    try {
      vertices = new ArrayList<Integer>();
      reader = new Scanner(file);
      if(reader.hasNextLine()){

      line = reader.nextLine();
      String[] splited = line.split(" ");
      verticesNum = Integer.parseInt(splited[0]);
      edgesNum = Integer.parseInt(splited[1]);
      graph1 = new int[verticesNum][verticesNum];
      graph = new ArrayList<MyEdge<Integer, Integer, Integer>>();
      }
      else{
        reader.close();
        throw new RuntimeException();
      }

      for (int i = 0; i < edgesNum; i++) {
        line = reader.nextLine();
        String[] splited2 = line.split(" ");
        from = Integer.parseInt(splited2[0]);
        to = Integer.parseInt(splited2[1]);
        weight = Integer.parseInt(splited2[2]);
        graph1[from][to] = weight;

        if (!vertices.contains(from)) {
          vertices.add(from);
        }
        if (!vertices.contains(to)) {
          vertices.add(to);
        }
        edge = new MyEdge<Integer, Integer, Integer>(from, to, weight);
        graph.add(edge);
      }
      reader.close();
    } catch (FileNotFoundException e) {
      reader.close();
      System.out.println("Unable to load file !");
      throw new RuntimeException();
    }

  }

  @Override
  public int size() {
    return edgesNum;
  }

  @Override
  public ArrayList<Integer> getVertices() {
    return vertices;

  }

  @Override
  public ArrayList<Integer> getNeighbors(int v) {

    ArrayList<Integer> arr1 = new ArrayList<Integer>();
    for (int i = 0; i < verticesNum; i++) {
      if (graph1[v][i] != 0) {
        arr1.add(i);
      }
    }
    return arr1;
  }

  @Override
  public void runDijkstra(int src, int[] distances) {
    sptSet = new boolean[distances.length];
    arr = new ArrayList<>();

    for (int index = 0; index < distances.length; index++) {
      sptSet[index] = false;
      distances[index] = Integer.MAX_VALUE / 2;

    }
    distances[src] = 0;
    for (int count = 0; count < vertices.size() - 1; count++) {
      int min = minDist(distances, sptSet);
      arr.add(min);
      sptSet[min] = true;
      for (int v = 0; v < verticesNum; v++) {

        if (!sptSet[v] && graph1[min][v] != 0
                && distances[min] + graph1[min][v] < distances[v] && distances[min] != Integer.MAX_VALUE/2)
          distances[v] = distances[min] + graph1[min][v];
      }
    }

  }

  @Override
  public ArrayList<Integer> getDijkstraProcessedOrder() {
    return arr;

  }

  public int minDist(int[] distances, boolean[] sptSet) {
    int min = Integer.MAX_VALUE / 2;
    int minIndex = -1;
    for (int index = 0; index < distances.length; index++) {
      if (distances[index] <= min && sptSet[index] == false) {
        min = distances[index];
        minIndex = index;
      }
    }
    return minIndex;
  }

  @Override
  public boolean runBellmanFord(int src, int[] distances) {
    for (int index = 0; index < distances.length; index++) {
      distances[index] = Integer.MAX_VALUE / 2;
    }
    boolean flag= false;
    distances[src] = 0;
    for (int count = 0; count < vertices.size() - 1; count++) {
      flag=false;
      for (int j = 0; j < edgesNum; j++) {
        int u = graph.get(j).getFrom();
        int v = graph.get(j).getTo();
        int weight = graph.get(j).getWeight();
        if (distances[u] + weight < distances[v]){
          distances[v] = distances[u] + weight;
        flag=true;
        }
      }
      if(!flag){
        return true;
      }
    }
    for (int j = 0; j < edgesNum; j++) {
      int u = graph.get(j).getFrom();
      int v = graph.get(j).getTo();
      int weight = graph.get(j).getWeight();
      if (distances[u] != Integer.MAX_VALUE/2
              && distances[u] + weight < distances[v])
        return false;
    }
    return true;
  }

}
