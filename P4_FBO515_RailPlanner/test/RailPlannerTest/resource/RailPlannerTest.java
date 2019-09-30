package RailPlannerTest.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.google.common.graph.ValueGraph;

import edu.metrostate.ics340.p4.fbo515.RailPlanner;

class RailPlannerTest {

    final static String SCN_00_PATH = getFilePath("00");
    final static String SCN_01_PATH = getFilePath("01");
    final static String SCN_02_PATH = getFilePath("02");
    final static String SCN_03_PATH = getFilePath("03");

    RailPlanner railPlanner = new RailPlanner();

    private static String getFilePath(String scenarioNum) {

	String fileName = String.format("Data/RailPlannerScenario%s.txt", scenarioNum);
	return RailPlannerTest.class.getResource(fileName).getPath();

    }

    @Test
    void test0() {

	ValueGraph<String, Integer> graph01 = RailPlanner.createPlan(SCN_00_PATH);
	assertEquals(20, getCost(graph01));
	assertEquals(graph01.nodes().size() - 1, graph01.edges().size());

    }

    @Test
    void test1() {
	ValueGraph<String, Integer> graph02 = RailPlanner.createPlan(SCN_01_PATH);
	assertEquals(15, getCost(graph02));
	assertEquals(graph02.nodes().size() - 1, graph02.edges().size());
    }

    @Test
    void test2() {
	ValueGraph<String, Integer> graph03 = RailPlanner.createPlan(SCN_02_PATH);
	assertEquals(16, getCost(graph03));
	assertEquals(graph03.nodes().size() - 1, graph03.edges().size());

    }
    @Test
    void test3() {
	ValueGraph<String, Integer> graph04 = RailPlanner.createPlan(SCN_03_PATH);
	assertEquals(37, getCost(graph04));
	assertEquals(graph04.nodes().size() - 1, graph04.edges().size());

    }
    

    public static int getCost(ValueGraph<String, Integer> graph) {
	int totalWeight = 0;
	for (var edge : graph.edges()) {
	    totalWeight += graph.edgeValue(edge).get();
	}
	return totalWeight;
    }
}