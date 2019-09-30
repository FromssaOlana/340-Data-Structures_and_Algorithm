package edu.metrostate.ics340.p3.fbo515;

public class Edge {

    private int weight;
    private Node startNode;
    private Node targetNode;

    public Edge(int weight, Node startNode, Node targetNode) {
        this.weight = weight;
        this.startNode = startNode;
        this.targetNode = targetNode;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Node getStartNode() {
        return startNode;
    }

    public void setStartNode(Node startNode) {
        this.startNode = startNode;
    }

    public Node getTargetNode() {
        return targetNode;
    }

    public void setTargetNode(Node targetNode) {
        this.targetNode = targetNode;
    }
    public String toString(){
        return this.targetNode.getName();
    }
}
