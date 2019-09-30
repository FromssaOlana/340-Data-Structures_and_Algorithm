package edu.metrostate.ics340.p5.fbo515;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * The PerimeterFinder Class will identify the locations which bound the minimum
 * patrol area that is defined by the convex hull of the sector.
 * 
 * @author Fromssa Olana
 *
 *
 */

public class PerimeterFinder {

    /**
     * A getBoundary method will find the locations of minimum patrol area points.
     * It will use the Gram’s Scan Algorithm as discussed in (Cormen 2009)
     * 
     * @param sectorFile
     * @return set of convex hull points
     */
    public static Set<String> getBoundary(String sectorFile) {
	List<Point> pointList;
	File file = new File(sectorFile);
	if (file.exists() && file.canRead()) {
	    pointList = pointReader(sectorFile);
	} else {
	    throw new IllegalArgumentException();
	}

	Set<String> boundry = grahamScan(pointList);
	return boundry;

    }

    private static Set<String> grahamScan(List<Point> pointList) {
	Set<String> pointSet = new HashSet<>();
	CustomStack<Point> customStack = new CustomStack<>();
	// returning an empty set if the size is less than 3
	if (pointList.size() < 3) {
	    return pointSet;
	}
	// sort the list
	sort(pointList);

	customStack.push(pointList.get(0));
	customStack.push(pointList.get(1));
	customStack.push(pointList.get(2));
	// checking the second point if it needs to be flagged.
	getTurn(customStack.getAtIndex(0), customStack.getAtIndex(1), customStack.getAtIndex(2));

	for (int i = 3; i < pointList.size(); i++) {
	    while (getTurn(customStack.nextToTop(), customStack.top(), pointList.get(i)) == -1) {
		customStack.pop();
	    }
	    customStack.push(pointList.get(i));
	}

	// converting the customStack to a set of strings
	for (Point point : customStack) {
	    if (!point.getRemove().equals("true")) { // dropping points on the line
		pointSet.add(point.getLocationId());
	    }
	}
	// clearing the Set if all points are coliner,
	if (pointSet.size() < 3) {
	    pointSet.clear();
	}
	return pointSet;
    }

    private static void sort(List<Point> pointList) {

	Collections.sort(pointList, new Comparator<Point>() {
	    Point lowestPoint = getLowestPoint(pointList);

	    @Override
	    public int compare(Point point1, Point point2) {

		double angleResult = Math.atan2(point1.getY() - lowestPoint.getY(), point1.getX() - lowestPoint.getX())
			- Math.atan2(point2.getY() - lowestPoint.getY(), point2.getX() - lowestPoint.getX());

		if (angleResult > 0) {
		    return 1;
		} else if (angleResult < 0) {
		    return -1;
		} else {
		    double radResult = Math
			    .sqrt(Math.pow((point1.getX() - lowestPoint.getX()), 2)
				    + Math.pow((point1.getY() - lowestPoint.getY()), 2))
			    - Math.sqrt(Math.pow((point2.getX() - lowestPoint.getX()), 2)
				    + Math.pow((point2.getY() - lowestPoint.getY()), 2));
		    if (radResult > 0) {
			return 1;
		    } else {
			return -1;
		    }

		}

	    }

	});

    }

    private static Point getLowestPoint(List<Point> points) {

	Point lowest = points.get(0);

	for (int i = 1; i < points.size(); i++) {

	    Point temp = points.get(i);

	    if (temp.getY() < lowest.getY() || (temp.getY() == lowest.getY() && temp.getX() < lowest.getX())) {
		lowest = temp;
	    }
	}

	return lowest;
    }

    private static int getTurn(Point a, Point b, Point c) {

	double crossProduct = (((b.getX() - a.getX()) * (c.getY() - a.getY()))
		- ((c.getX() - a.getX()) * (b.getY() - a.getY())));

	if (crossProduct > 0) {
	    return 1;
	} else if (crossProduct < 0) {
	    return -1;
	} else {
	    b.setRemove("true");// Flags a point that is on parameter line
	    return 0;
	}
    }

    private static List<Point> pointReader(String sectorFile) {
	List<Point> points = new ArrayList<>();

	File file = new File(sectorFile);
	Scanner scanner = null;
	try {
	    scanner = new Scanner(file);
	    scanner.useDelimiter("[|\n]");
	    while (scanner.hasNext()) {
		String id = scanner.next();
		double x = scanner.nextDouble();
		double y = scanner.nextDouble();
		Point point = new Point(id, x, y);
		points.add(point);
	    }
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}

	return points;

    }

}
