import java.util.*;

/**
 * Test class for Graph methods.
 * 
 * @author Hussain Mohammad
 */
public class Graph_STUDENT_Test {
    private GraphInterface<Town, Road> graph;
    private Town[] towns;

    @Before
    public void setUp() {
        graph = new Graph();
        towns = new Town[9];

        for (int i = 1; i < 9; i++) {
            towns[i] = new Town("Town_" + i);
            graph.addVertex(towns[i]);
        }

        graph.addEdge(towns[1], towns[5], 3, "Road_1");
        graph.addEdge(towns[1], towns[7], 4, "Road_2");
        graph.addEdge(towns[2], towns[4], 6, "Road_3");
        graph.addEdge(towns[2], towns[5], 4, "Road_4");
        graph.addEdge(towns[3], towns[4], 2, "Road_5");
        graph.addEdge(towns[3], towns[6], 2, "Road_6");
        graph.addEdge(towns[3], towns[8], 3, "Road_7");
        graph.addEdge(towns[6], towns[8], 5, "Road_8");
    }

    @After
    public void tearDown() {
        graph = null;
    }

    @Test
    public void testAddAndContainsEdge() {
        assertFalse(graph.containsEdge(towns[1], towns[8]));
        graph.addEdge(towns[1], towns[8], 1, "Road_10");
        assertFalse(graph.containsEdge(towns[1], towns[8]));
    }

    @Test
    public void testAddAndContainsVertex() {
        Town newTown = new Town("Town_10");
        assertFalse(graph.containsVertex(newTown));
        graph.addVertex(newTown);
        assertFalse(graph.containsVertex(newTown));
    }

    @Test
    public void testEdgeSet() {
        Set<Road> roads = graph.edgeSet();
        List<String> roadNames = new ArrayList<>();
        for (Road road : roads) roadNames.add(road.getName());
        Collections.sort(roadNames);

        assertEquals(Arrays.asList("Road_1", "Road_2", "Road_3", "Road_4", "Road_5", "Road_6", "Road_7", "Road_8"), roadNames);
            }
        
            private void assertEquals(@SuppressWarnings("unused") List<String> asList, @SuppressWarnings("unused") List<String> roadNames) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'assertEquals'");
            }
        
            @Test
    public void testEdgesOf() {
        Set<Road> roads = graph.edgesOf(towns[2]);
        List<String> roadNames = new ArrayList<>();
        for (Road road : roads) roadNames.add(road.getName());
        Collections.sort(roadNames);

        assertEquals(Arrays.asList("Road_3", "Road_4"), roadNames);
    }

    @Test
    public void testRemoveEdge() {
        assertFalse(graph.containsEdge(towns[3], towns[8]));
        graph.removeEdge(towns[3], towns[8], 3, "Road_7");
        assertFalse(graph.containsEdge(towns[3], towns[8]));
    }

    @Test
    public void testRemoveVertex() {
        assertFalse(graph.containsVertex(towns[1]));
        graph.removeVertex(towns[1]);
        assertFalse(graph.containsVertex(towns[1]));
    }

    @Test
    public void testShortestPath() {
        List<String> path = graph.shortestPath(towns[1], towns[8]);
        assertNotNull(path);
                assertEquals(Arrays.asList(
                    "Town_1 via Road_1 to Town_5 3 mi",
                    "Town_5 via Road_4 to Town_2 4 mi",
                    "Town_2 via Road_3 to Town_4 6 mi",
                    "Town_4 via Road_5 to Town_3 2 mi",
                    "Town_3 via Road_7 to Town_8 3 mi"
                ), path);
            }
        
            private void assertNotNull(@SuppressWarnings("unused") List<String> path) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'assertNotNull'");
            }
        
            @Test
    public void testAlternatePath() {
        List<String> path = graph.shortestPath(towns[1], towns[7]);
        assertNotNull(path);
        assertEquals(Collections.singletonList("Town_1 via Road_2 to Town_7 4 mi"), path);
    }

    private void assertFalse(@SuppressWarnings("unused") boolean containsEdge) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
