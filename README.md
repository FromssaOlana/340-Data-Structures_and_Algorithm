# 340-Data-Structures_and_Algorithm
Class project P3. (DroneRouter)

BACKGROUND:
Amazonian Delivery Services is piloting a drone delivery service in a specified region of the country. 
To avoid conflicts with other air traffic the FAA has restricted drone flights to defined pathways between designated way-points. 
The paths between way-points are given values—lower values are preferred paths. Moreover fees are assessed on the total path score,
so it is in Amazonian interest to route drones using the lowest cost path from the warehouse to its destination.

ASSIGNMENT:
Create a class, DroneRouter, that will determine the best (i.e. minimum cost) path between the distribution center and 
a specified waypoint. The DroneRouter class will implement the Router interface (provided).

Class project P4.(RailPlaner)

BACKGROUND:
The Monarchy of Freedonia has commissioned the construction of a monorail system to connect its major cities. Its ambitious goals are tempered by tight fiscal controls that require the costs be minimized.
As part of the planning phase you must develop a program that will load in cost estimates for various rail segments between cities and produce a plan that connects all cities at the minimum construction cost.

ASSIGNMENT: 
Create a class, RailPlanner that will construct a rail system connecting all of the cities for minimum cost, using either the Kruskall or Prim Algorithms in Cormen (2009). Your solution must use the Googal Guava com.google.common.graph.ValueGraph
data type (version 27.1 or later).

Class project P5.(PERIMETER PATROL)


BACKGROUND:
The Robitron Drone Security Company programs their drones to focus on key locations. More than just point-to-point visits, the drones will also surveil the area bounded by those points.
You are asked to write a program that, provided a sector file containing the location points and their coordinates, will identify which locations bound the patrol area that will be covered by drones assigned to that sector.

ASSIGNMENT: 
Create a program PerimeterFinder that identifies the locations which bound the minimum patrol area that is defined by the convex hull of the sector. Your solution should use either the Gram’s Scan Algorithm (Cormen 2009) or the Jarvis’ March (as described in Wikipedia). If you use any other resource be sure to cite in your code.
