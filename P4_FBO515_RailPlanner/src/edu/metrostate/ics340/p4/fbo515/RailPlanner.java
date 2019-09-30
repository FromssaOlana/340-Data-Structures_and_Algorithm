package edu.metrostate.ics340.p4.fbo515;

import com.google.common.graph.ValueGraph;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * 
 * @author Fromssa Olana
 *
 */

public class RailPlanner {
    
/**
 * createPlanner method will construct a rail system connecting all of the cities for 
 * minimum cost. The implementation is based on the  Kruskal Algorithms in  Cormen book (2009).
 * @param estimateFilePath
 * @return ValueGraph of an optimal solution. 
 */
    public static ValueGraph<String, Integer> createPlan(String estimateFilePath){

        Graph kruskalGraph;
        File file = new File(estimateFilePath);

        if (file.exists() && file.canRead()) {
            kruskalGraph = kruskalGraph(estimateFilePath);
        } else {
            throw new IllegalArgumentException();
        }


        return kruskalGraph.toValueGraph();
    }
    private static Graph kruskalGraph(String filePath){

        Graph graph = new Graph();
        File file = new File(filePath);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
            scanner.useDelimiter("[|\n]");
            while (scanner.hasNext()) {
                String city1 = scanner.next();
                String city2 = scanner.next();
                int weight = scanner.nextInt();
                graph.add(new Edge(new Node(city1), new Node(city2), weight));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Graph kruskalGraph = kruskal(graph);
        return kruskalGraph;

    }

    private static void makeSet(Node node,   Set<Set<Node>> sets) {
        if (node != null) {
            HashSet<Node> newSet = new HashSet<Node>();
            newSet.add(node);

            if (sets == null) {
                sets = new HashSet<Set<Node>>();
                sets.add(newSet);
            } else {
              sets.add(newSet);
                }
            }
           
    }


    private static Set<Node> findSet(Node node,  Set<Set<Node>> sets) {
        Set<Node> nodeSet = null;
        for (Set<Node> set : sets) {
            if (set.contains(node)) {
                nodeSet = set;
            }
        }
        return nodeSet;
    }

    private static void union(Node node1, Node node2,   Set<Set<Node>> sets) {
        Set<Node> s1 = findSet(node1, sets);
        Set<Node> s2 = findSet(node2, sets);

        assert (s1 != null);
        assert (s2 != null);

        HashSet<Node> union = new HashSet<Node>(s1);
        union.addAll(s2);
        sets.remove(s1);
        sets.remove(s2);
        s1 = null;
        s2 = null;
        sets.add(union);
    }

    private static Graph kruskal(Graph G) {
	Set<Set<Node>> sets = new HashSet<Set<Node>>();

        Graph graph = new Graph();
        for (Node node : G.getNodes()) {
            makeSet(node, sets);
        }
        
        for (Edge edge : G.getEdgesNonDecreasing()) {
         
            if (findSet(edge.firstCity, sets) != (findSet(edge.secondCity, sets))) {
                
                graph.add(edge);
                
                union(edge.firstCity, edge.secondCity, sets);
            }
        }
        return graph;
    }

}
