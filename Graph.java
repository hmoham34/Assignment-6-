import java.util.*;
import java.util.stream.Collectors;

/**
 * A Graph implementation for towns and roads. The graph is represented using adjacency lists.
 * Vertices are Town objects, and edges are Road objects.
 * 
 * @author Hussain Mohammad
 */
public class Graph implements GraphInterface<Town, Road> {

    private final Set<Town> towns = new HashSet<>();
    private final Set<Road> roads = new HashSet<>();
    @SuppressWarnings("FieldMayBeFinal")
    private List<String> shortestPath = new ArrayList<>();
    /**
     * Retrieves the road connecting two towns, if it exists.
     *
     * @param sourceVertex      source town
     * @param destinationVertex destination town
     * @return the connecting road, or null if none exists
     */
    @Override
    public Road getEdge(Town sourceVertex, Town destinationVertex) {
        if (sourceVertex == null || destinationVertex == null) return null;
        return roads.stream()
                .filter(road -> road.contains(sourceVertex) && road.contains(destinationVertex))
                .findFirst().orElse(null);
    }

    /**
     * Adds a road to the graph.
     *
     * @param sourceVertex      source town
     * @param destinationVertex destination town
     * @param distance          distance of the road
     * @param description       road name
     * @return the added road, or null if not added
     * @throws IllegalArgumentException if either town is not in the graph
     * @throws NullPointerException     if any input is null
     */
    @Override
    public Road addEdge(Town sourceVertex, Town destinationVertex, int distance, String description) {
        Objects.requireNonNull(sourceVertex, "Source town cannot be null");
        Objects.requireNonNull(destinationVertex, "Destination town cannot be null");
        if (!containsVertex(sourceVertex) || !containsVertex(destinationVertex)) {
            throw new IllegalArgumentException("Both towns must be in the graph");
        }
        Road road = new Road(sourceVertex, destinationVertex, distance, description);
        roads.add(road);
        return road;
    }

    /**
     * Adds a town to the graph.
     *
     * @param town the town to add
     * @return true if the town was added, false otherwise
     * @throws NullPointerException if the town is null
     */
    @Override
    public boolean addVertex(Town town) {
        Objects.requireNonNull(town, "Town cannot be null");
        return towns.add(town);
    }

    /**
     * Checks if a road exists between two towns.
     *
     * @param sourceVertex      source town
     * @param destinationVertex destination town
     * @return true if the road exists, false otherwise
     */
    @Override
    public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
        return roads.stream().anyMatch(road -> road.contains(sourceVertex) && road.contains(destinationVertex));
    }

    /**
     * Checks if the graph contains the specified town.
     *
     * @param town the town to check
     * @return true if the town exists, false otherwise
     */
    @Override
    public boolean containsVertex(Town town) {
        return towns.contains(town);
    }

    /**
     * Retrieves all roads in the graph.
     *
     * @return a set of roads
     */
    @Override
    public Set<Road> edgeSet() {
        return roads;
    }

    /**
     * Retrieves all roads connected to a specific town.
     *
     * @param town the town whose roads are to be retrieved
     * @return a set of connected roads
     * @throws IllegalArgumentException if the town is not in the graph
     * @throws NullPointerException     if the town is null
     */
    @Override
    public Set<Road> edgesOf(Town town) {
        Objects.requireNonNull(town, "Town cannot be null");
        Set<Road> connectedRoads = roads.stream()
                .filter(road -> road.contains(town))
                .collect(Collectors.toSet());
        if (connectedRoads.isEmpty()) throw new IllegalArgumentException("Town is not in the graph");
        return connectedRoads;
    }

    /**
     * Removes a road from the graph.
     *
     * @param sourceVertex      source town
     * @param destinationVertex destination town
     * @param distance          road distance
     * @param description       road name
     * @return the removed road, or null if no road was removed
     */
    @Override
    public Road removeEdge(Town sourceVertex, Town destinationVertex, int distance, String description) {
        Road roadToRemove = getEdge(sourceVertex, destinationVertex);
        if (roadToRemove != null && roadToRemove.getDistance() == distance
                && roadToRemove.getName().equals(description)) {
            roads.remove(roadToRemove);
            return roadToRemove;
        }
        return null;
    }

    /**
     * Removes a town from the graph.
     *
     * @param town the town to remove
     * @return true if the town was removed, false otherwise
     */
    @Override
    public boolean removeVertex(Town town) {
        if (town == null) return false;
        roads.removeIf(road -> road.contains(town));
        return towns.remove(town);
    }

    /**
     * Retrieves all towns in the graph.
     *
     * @return a set of towns
     */
    @Override
    public Set<Town> vertexSet() {
        return towns;
    }

    /**
     * Finds the shortest path from one town to another.
     *
     * @param sourceVertex      starting town
     * @param destinationVertex ending town
     * @return an ArrayList of Strings describing the path
     */
    @Override
    public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
        dijkstraShortestPath(sourceVertex);
        return shortestPath.stream().map(path -> {
            Town source = new Town(path.split(" ")[0]);
            Town destination = new Town(path.split(" ")[2]);
            Road road = getEdge(source, destination);
            return road.toString();
        }).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Implements Dijkstra's algorithm to find shortest paths.
     *
     * @param sourceVertex starting town
     */
    @Override
    public void dijkstraShortestPath(Town sourceVertex) {
        // Implement Dijkstra's logic here
    }

    public List<String> getShortestPath() {
        return shortestPath;
    }
}
