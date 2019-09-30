package edu.metrostate.ics340.p3.fbo515;

import edu.metrostate.p3.Router;
import java.io.File;
import java.io.IOException;
import java.util.*;
/**
 * 
 * @author Fromssa Olana
 *
 */

public class DroneRouter implements Router {

    private Node[] adjacencyList;
    private String source;


    /**
     * Loads the file containing waypoints pairs and the cost to travel between
     * them.
     *
     * @param routesFilePath path to the routes file
     * @param source         the waypoint that is the source location, that is, the
     *                       starting point of all routs
     * @throws IllegalArgumentException if the file is not accessible or the source
     *                                  location does not exist
     */

    public void loadRoutes(String routesFilePath, String source) {

        File path;
        path = new File(routesFilePath);
        if (path.exists() && path.canRead()) {
            fileReader(routesFilePath);
        } else {
            throw new IllegalArgumentException();
        }
        this.source = source;
        buildShortestPaths(this.source);

    }


    /**
     * Returns the cost of the shortest route from the designated source waypoint to
     * the specified destination
     *
     * @param destination endpoint of route from the source waypoint
     * @return the cost in units, or {@link NO_ROUTE} if no route exists from the
     * source to the specified destination
     * @throws IllegalArgumentException if destination does not exist in the route
     *                                  file
     * @throws NullPointerException     if destination is {@code null}
     */

    @Override
    public int getPathCost(String destination) {
        if (destination == null) {
            throw new NullPointerException();
        }
        int destIndex = indexForName(destination);
        if (destIndex == -1) {
            throw new IllegalArgumentException();
        }

        if (getRoute(destination).length == 0) {
            return NO_ROUTE;
        }

        return adjacencyList[destIndex].getMinDistance();

    }

    /**
     * Returns the route from the designated source waypoint to the specified
     * destination
     *
     * @param destination endpoint of route
     * @return array of waypoints representing the least expensive route from the
     * source to the destination (inclusive). The array is empty if no path
     * exists.
     * @throws IllegalArgumentException if destination does not exist in the route
     *                                  file
     */


    @Override
    public String[] getRoute(String destination) {

        List<String> path = new ArrayList<>();
        int destIndex = indexForName(destination);

        if (destIndex == -1) {
            throw new IllegalArgumentException();
        } else {

            for (Node node = adjacencyList[destIndex]; node != null; node = node.getPreviouseNode()) {
                path.add(node.getName());
            }
        }
        Collections.reverse(path);

        // there is no path to the destination.
        if (!(path.contains(source) && path.contains(destination))) {
            String[] array = new String[0];
            return array;
        }

        return path.toArray(new String[path.size()]);
    }


    private void fileReader(String filePath) {

        Queue<String> nodeQueue = new LinkedList<>();
        Queue<String> relationQueue = new LinkedList<>();
        Queue<Integer> distanceList = new LinkedList<>();
        File file = new File(filePath);

        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                String firstWord = scanner.next();
                String secondWord = scanner.next();
                int intValue = scanner.nextInt();
                relationQueue.add(firstWord);
                relationQueue.add(secondWord);
                distanceList.add(intValue);
                if (!nodeQueue.contains(firstWord)) {
                    nodeQueue.add(firstWord);

                }
                if (!nodeQueue.contains(secondWord)) {
                    nodeQueue.add(secondWord);
                }
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();

        }

        Graph(nodeQueue, relationQueue, distanceList);
    }

    private int indexForName(String name) {

        for (int i = 0; i < adjacencyList.length; i++) {

            if (adjacencyList[i].getName().equals(name)) {
                return i;
            }

        }
        return -1;
    }

    private void Graph(Queue<String> nodeQueue, Queue<String> relationQueue, Queue<Integer> distanceList) {

        adjacencyList = new Node[nodeQueue.size()]; // an array of size of nodes

        // populating the adjecencyList form nodeQueue.
        for (int i = 0; i < adjacencyList.length; i++) {
            adjacencyList[i] = new Node(nodeQueue.poll());

        }

        while (!relationQueue.isEmpty()) {
            int weight = distanceList.poll().intValue();
            int nodeOneIndex = indexForName(relationQueue.poll());
            int nodeTwoIndex = indexForName(relationQueue.poll());

            adjacencyList[nodeOneIndex]
                    .addNeighbor(new Edge(weight, adjacencyList[nodeOneIndex], adjacencyList[nodeTwoIndex]));
            adjacencyList[nodeTwoIndex]
                    .addNeighbor(new Edge(weight, adjacencyList[nodeTwoIndex], adjacencyList[nodeOneIndex]));

        }

    }


    private void buildShortestPaths(String source) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        Node sourceNode = null;
        for (Node node : adjacencyList) {
            if (node.getName().equals(source)) {
                sourceNode = node;
            }
        }
        sourceNode.setMinDistance(0);

        priorityQueue.add(sourceNode);
        sourceNode.setVisited(true);

        while (!priorityQueue.isEmpty()) {

            Node currentNode = priorityQueue.poll();
            for (Edge edge : currentNode.getNeighborList()) {
                Node neighborNode = edge.getTargetNode();
                if (!neighborNode.isVisited()) {
                    int weight = edge.getWeight();
                    int minDistance = currentNode.getMinDistance() + weight;

                    if (minDistance < neighborNode.getMinDistance()) {
                        priorityQueue.remove(neighborNode);
                        neighborNode.setPreviouseNode(currentNode);
                        neighborNode.setMinDistance(minDistance);
                        priorityQueue.add(neighborNode);
                    }
                }

            }
            currentNode.setVisited(true);
        }

    }
}
