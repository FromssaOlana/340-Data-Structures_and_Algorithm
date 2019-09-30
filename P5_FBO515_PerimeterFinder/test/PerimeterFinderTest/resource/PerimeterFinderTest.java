package PerimeterFinderTest.resource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import edu.metrostate.ics340.p5.fbo515.PerimeterFinder;

class PerimeterFinderTest {

    final static String SCN_00_PATH = getFilePath("00");
    final static String SCN_01_PATH = getFilePath("01");
    final static String SCN_02_PATH = getFilePath("02");
    final static String SCN_03_PATH = getFilePath("03");
    final static String SCN_04_PATH = getFilePath("04");

    private static String getFilePath(String scenarioNum) {

	String fileName = String.format("Data/PerimeterFinderScenario%s.txt", scenarioNum);
	return PerimeterFinderTest.class.getResource(fileName).getPath();

    }

    @Test //many points test
    void testGetBoundary() {
	Set<String> actual = PerimeterFinder.getBoundary(SCN_00_PATH);
	Set<String> expected = new HashSet<>();
	
	expected.add("B");
	expected.add("J");
	expected.add("G");
	expected.add("I");
	expected.add("K");
	assertEquals(expected, actual);

    }
    @Test // All colinear points test
    void testGetBoundary2() {
	Set<String> actual = PerimeterFinder.getBoundary(SCN_01_PATH);
	Set<String> expected = new HashSet<>();
	assertEquals(expected, actual);

    }
    
    @Test // One point test
    void testGetBoundary3() {
	Set<String> actual = PerimeterFinder.getBoundary(SCN_02_PATH);
	Set<String> expected = new HashSet<>();
	assertEquals(expected, actual);

    }
    
    @Test // Two points test
    void testGetBoundary4() {
	Set<String> actual = PerimeterFinder.getBoundary(SCN_03_PATH);
	Set<String> expected = new HashSet<>();
	assertEquals(expected, actual);

    }
    
    @Test // Three points test
    void testGetBoundary5() {
	Set<String> actual = PerimeterFinder.getBoundary(SCN_04_PATH);
	Set<String> expected = new HashSet<>();
	expected.add("B");
	expected.add("J");
	expected.add("I");
	assertEquals(expected, actual);

    }
    
    

}
