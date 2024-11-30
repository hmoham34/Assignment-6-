import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for the TownGraphManager class.
 * 
 * Tests various methods to ensure the graph functionality works as expected.
 * 
 * @author Hussain Mohammad
 */
public class TownGraphManager_STUDENT_Test {

    private TownGraphManagerInterface graph;
    private String[] towns;

    @Before
    public void setUp() throws Exception {
        graph = new TownGraphManager();
        towns = new String[9];

        // Add towns to the graph
        for (int i = 1; i < 9; i++) {
            towns[i] = "Town_" + i;
            graph.addTown(towns[i]);
        }

        // Add roads between towns
        graph.addRoad(towns[1], towns[5], 3, "Road_1");
        graph.addRoad(towns[1], towns[7], 4, "Road_2");
        graph.addRoad(towns[2], towns[4], 6, "Road_3");
        graph.addRoad(towns[2], towns[5], 4, "Road_4");
        graph.addRoad(towns[3], towns[4], 2, "Road_5");
        graph.addRoad(towns[3], towns[6], 2, "Road_6");
        graph.addRoad(towns[3], towns[8], 3, "Road_7");
        graph.addRoad(towns[6], towns[8], 5, "Road_8");
    }

    @After
    public void tearDown() throws Exception {
        graph = null;
    }

    @Test
    public void testAddRoad() {
        ArrayList<String> roads = graph.allRoads();
        assertEquals("Road_1", roads.get(0));
        assertEquals("Road_2", roads.get(1));

        // Add a new road and verify the updated roads list
        graph.addRoad(towns[1], towns[8], 5, "Road_10");
        roads = graph.allRoads();
        assertTrue(roads.contains("Road_10"));
    }

    @Test
    public void testGetRoad() {
        assertEquals("Road_4", graph.getRoad(towns[2], towns[5]));
        assertEquals("Road_7", graph.getRoad(towns[3], towns[8]));
    }

    @Test
    public void testAddTown() {
        assertFalse(graph.containsTown("Town_9"));
        graph.addTown("Town_9");
        assertTrue(graph.containsTown("Town_9"));
    }

    @Test
    public void testDisjointGraph() {
        graph.addTown("Town_10");
        ArrayList<String> path = graph.getPath(towns[1], "Town_10");
        assertTrue(path.isEmpty());
    }

    @Test
    public void testContainsTown() {
        assertTrue(graph.containsTown("Town_5"));
        assertFalse(graph.containsTown("Town_10"));
    }

    @Test
    public void testContainsRoadConnection() {
        assertTrue(graph.containsRoadConnection(towns[1], towns[7]));
        assertFalse(graph.containsRoadConnection(towns[1], towns[8]));
    }

    @Test
    public void testAllRoads() {
        ArrayList<String> roads = graph.allRoads();
        assertTrue(roads.contains("Road_1"));
        assertTrue(roads.contains("Road_8"));
        assertTrue(roads.contains("Road_5"));
    }

    @Test
    public void testDeleteRoadConnection() {
        assertTrue(graph.containsRoadConnection(towns[1], towns[7]));
        graph.deleteRoadConnection(towns[1], towns[7], "Road_2");
        assertFalse(graph.containsRoadConnection(towns[1], towns[7]));
    }

    @Test
    public void testDeleteTown() {
        assertTrue(graph.containsTown("Town_4"));
        graph.deleteTown(towns[4]);
        assertFalse(graph.containsTown("Town_4"));
    }

    @Test
    public void testAllTowns() {
        ArrayList<String> townsList = graph.allTowns();
        assertTrue(townsList.contains("Town_1"));
        assertTrue(townsList.contains("Town_8"));
    }

    @Test
    public void testGetPath() {
        ArrayList<String> path = graph.getPath(towns[1], towns[8]);
        assertNotNull(path);
        assertFalse(path.isEmpty());
        assertEquals("Town_1 via Road_1 to Town_5 3 mi", path.get(0).trim());
        assertEquals("Town_5 via Road_4 to Town_2 4 mi", path.get(1).trim());
        assertEquals("Town_2 via Road_3 to Town_4 6 mi", path.get(2).trim());
        assertEquals("Town_4 via Road_5 to Town_3 2 mi", path.get(3).trim());
        assertEquals("Town_3 via Road_7 to Town_8 3 mi", path.get(4).trim());
    }

    @Test
    public void testGetPathA() {
        ArrayList<String> path = graph.getPath(towns[1], towns[7]);
        assertNotNull(path);
        assertFalse(path.isEmpty());
        assertEquals("Town_1 via Road_2 to Town_7 4 mi", path.get(0).trim());
    }

    @Test
    public void testGetPathB() {
        ArrayList<String> path = graph.getPath(towns[2], towns[6]);
        assertNotNull(path);
        assertFalse(path.isEmpty());
                assertEquals("Town_2 via Road_3 to Town_4 6 mi", path.get(0).trim());
                assertEquals("Town_4 via Road_5 to Town_3 2 mi", path.get(1).trim());
                assertEquals("Town_3 via Road_6 to Town_6 2 mi", path.get(2).trim());
            }
        
            private void assertFalse(@SuppressWarnings("unused") boolean empty) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'assertFalse'");
            }
        
            private void assertEquals(@SuppressWarnings("unused") String town_3_via_Road_6_to_Town_6_2_mi, @SuppressWarnings("unused") String trim) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void assertNotNull(@SuppressWarnings("unused") ArrayList<String> path) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void assertTrue(@SuppressWarnings("unused") boolean contains) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
