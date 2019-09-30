package DrownRouterTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.metrostate.ics340.p3.fbo515.DroneRouter;
import edu.metrostate.p3.Router;

class DroneRouterTest {

    Router router = new DroneRouter();

    @Test
    void loadRoutes() {
        assertThrows(IllegalArgumentException.class, () -> router.loadRoutes("/Users/fromssaolana/Desktop/FinalTes.txt", "A"));
    }

    @Test
    void getPathCost() {
        router.loadRoutes("/Users/fromssaolana/Desktop/FinalTest.txt", "A");
        assertEquals(Router.NO_ROUTE, router.getPathCost("M"));
        assertEquals(5, router.getPathCost("G"));

    }

    @Test
    void getRoute() {
        router.loadRoutes("/Users/fromssaolana/Desktop/FinalTest.txt", "A");
        assertArrayEquals(new String[] {}, router.getRoute("M"));
        assertArrayEquals(new String[] { "A", "E", "G" }, router.getRoute("G"));
        assertThrows(IllegalArgumentException.class, () -> router.getRoute("Z"));
    }
    
    
}