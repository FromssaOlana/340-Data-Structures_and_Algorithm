package edu.metrostate.ics340.p5.fbo515;
/**
 * The Point class will hold an characters of a point. 
 * @author fromssaolana
 *
 */
public class Point {
    private double x;
    private double y;
    private String locationId;
    private String remove = "false";


    public String getRemove() {
        return remove;
    }

    public void setRemove(String remove) {
        this.remove = remove;
    }


    public String getLocationId() {
        return locationId;
    }


    public Point(String locationId, double x, double y) {
        this.x = x;
        this.y = y;
        this.locationId = locationId;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Point other = (Point) obj;
        if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
            return false;
        if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Point{" +
                " locationId='" + locationId +
                '}';
    }
}