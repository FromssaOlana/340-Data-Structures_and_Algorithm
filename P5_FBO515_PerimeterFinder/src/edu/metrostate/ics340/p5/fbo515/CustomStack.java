package edu.metrostate.ics340.p5.fbo515;
import java.util.ArrayList;
import java.util.Iterator;
/**
 * 
 * A CustomStack class will serve as a stack to the PerimeterFinder class. 
 * It is customized to fit the need of the algorithm used. 
 * @author Fromssa Olana
 *
 * @param <T>
 */

public class CustomStack<T> implements Iterable<T> {

    private ArrayList<T> pointArrayList = new ArrayList<>();

    public void push(T point) {
	pointArrayList.add(point);
    }

    public T nextToTop() {
	return pointArrayList.get(pointArrayList.size() - 2); // next to the top
    }

    public T top() {
	return pointArrayList.get(pointArrayList.size() - 1); // top of the stack
    }

    public void pop() {
	pointArrayList.remove(pointArrayList.size() - 1); // removes the top of the stack
    }

    public int size() {
	return pointArrayList.size();
    }

    public T getAtIndex(int index) {
	return pointArrayList.get(index);
    }

    @Override
    public String toString() {
	return pointArrayList.toString();
    }

    @Override
    public Iterator<T> iterator() {
	return pointArrayList.iterator();
    }
}
