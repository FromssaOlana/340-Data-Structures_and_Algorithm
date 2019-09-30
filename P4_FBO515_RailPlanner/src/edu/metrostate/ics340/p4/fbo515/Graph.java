package edu.metrostate.ics340.p4.fbo515;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraph;
import com.google.common.graph.ValueGraphBuilder;

public class Graph {

    @Override
    public String toString() {
	return String.format("%s", edges);
    }

    private List<Edge> edges = null;

    /**
     * adds an edge to the Graph
     * 
     * @param edge to add to the Graph
     */
    public void add(Edge edge) {
	if (edges == null) {
	    edges = new ArrayList<Edge>();
	}
	edges.add(edge);
    }

    /**
     * 
     * @return A set of all nodes in the graph.
     */
    public Set<Node> getNodes() {
	Set<Node> nodeSet = null;
	if (edges != null) {
	    nodeSet = new HashSet<Node>();
	    for (Edge edge : edges) {
		nodeSet.add(edge.firstCity);
		nodeSet.add(edge.secondCity);
	    }
	}
	return nodeSet;
    }

    /**
     * 
     * @return A set of all edges in the graph.
     */
    public Set<Edge> getEdges() {
	Set<Edge> edges = new HashSet<>();
	for (Edge edge : this.edges) {
	    edges.add(edge);
	}

	return edges;
    }

    /**
     * 
     * @return A sorted list of all edges in the graph.
     */
    public List<Edge> getEdgesNonDecreasing() {
	List<Edge> orderedEdges = new LinkedList<>(edges);
	orderedEdges.sort(Comparator.comparingInt(Edge::getWeight));
	return orderedEdges;
    }

    /**
     * 
     * @return A valueGraph type graph of a graph.
     */
    public ValueGraph<String, Integer> toValueGraph() {

	MutableValueGraph<String, Integer> graph = ValueGraphBuilder.undirected().build();

	for (var edge : this.getEdgesNonDecreasing()) {
	    graph.putEdgeValue(edge.firstCity.getNameOfCity(), edge.secondCity.getNameOfCity(), edge.weight);
	}
	return graph;
    }
}