package edu.metrostate.ics340.p3.fbo515;

import java.util.LinkedList;
import java.util.List;

class Node implements Comparable<Node>{
    private String name;
    private List<Edge> neighborList = new LinkedList<>();
    private boolean visited;
    private Node previouseNode;
    private int minDistance = Integer.MAX_VALUE;

    public Node(String name) {
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public List<Edge> getNeighborList() {
        return neighborList;
    }
    public void  addNeighbor(Edge edge){
        this.neighborList.add(edge);
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public Node getPreviouseNode() {
        return previouseNode;
    }

    public void setPreviouseNode(Node previouseNode) {
        this.previouseNode = previouseNode;
    }

    public int getMinDistance() {
        return minDistance;
    }

    public void setMinDistance(int minDistance) {
        this.minDistance = minDistance;
    }


    @Override
    public int compareTo(Node otherNode) {
        return Integer.compare(this.minDistance,otherNode.getMinDistance());
    }
}
