package edu.metrostate.ics340.p4.fbo515;

public class Edge implements Comparable<Edge> {

    public final Node firstCity;
    public final Node secondCity;
    public final int weight;

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
	return String.format("[%s %s %s]", firstCity, secondCity, weight);
    }

    /**
     * The constructor will take two city variables and a weight between them.
     * @param firstCity
     * @param secondCity
     * @param weight
     */
   public Edge(Node firstCity, Node secondCity, int weight) {
        this.firstCity = firstCity;
        this.secondCity = secondCity;
        this.weight = weight;
    }

    /**
     * a comparator is implemented to help with 'min-heap' operations
     *
     * @param edge the edge to compare with this one
     * @return a number indicating the relative result of the comparison
     */
    public int compareTo(Edge edge) {
        return Integer.compare(weight, edge.weight);
    }

}