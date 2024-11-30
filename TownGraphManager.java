import java.io.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * TownGraphManager handles the operations for managing towns and roads in a graph.
 * It provides methods to add, delete, and query towns and roads, and populate the graph
 * from a file.
 * 
 * @author Hussain Mohammad
 */
public class TownGraphManager implements TownGraphManagerInterface {

    private final Graph graph = new Graph();

    /**
     * Adds a road to the graph.
     * 
     * @param town1    - name of the first town
     * @param town2    - name of the second town
     * @param distance - distance of the road
     * @param roadName - name of the road
     * @return true if the road was added successfully, false otherwise
     */
    @Override
    public boolean addRoad(String town1, String town2, int distance, String roadName) {
        return graph.addEdge(new Town(town1), new Town(town2), distance, roadName) != null;
    }

    /**
     * Gets the name of the road connecting two towns.
     * 
     * @param town1 - name of the first town
     * @param town2 - name of the second town
     * @return the road name if the towns are connected, null otherwise
     */
    @Override
    public String getRoad(String town1, String town2) {
        Road road = graph.getEdge(new Town(town1), new Town(town2));
        return road != null ? road.getName() : null;
    }

    /**
     * Adds a town to the graph.
     * 
     * @param name - the town's name
     * @return true if the town was successfully added, false otherwise
     */
    @Override
    public boolean addTown(String name) {
        return graph.addVertex(new Town(name));
    }

    /**
     * Retrieves a town by its name.
     * 
     * @param name - the town's name
     * @return the Town object, or null if the town does not exist
     */
    @Override
    public Town getTown(String name) {
        return graph.vertexSet()
                    .stream()
                    .filter(town -> town.getName().equals(name))
                    .findFirst()
                    .orElse(null);
    }

    /**
     * Checks if a town exists in the graph.
     * 
     * @param name - the town's name
     * @return true if the town exists, false otherwise
     */
    @Override
    public boolean containsTown(String name) {
        return graph.containsVertex(new Town(name));
    }

    /**
     * Checks if a road connection exists between two towns.
     * 
     * @param town1 - name of the first town
     * @param town2 - name of the second town
     * @return true if the road exists, false otherwise
     */
    @Override
    public boolean containsRoadConnection(String town1, String town2) {
        return graph.containsEdge(new Town(town1), new Town(town2));
    }

    /**
     * Retrieves all road names sorted alphabetically.
     * 
     * @return a list of road names sorted in alphabetical order
     */
    @Override
    public ArrayList<String> allRoads() {
        return graph.edgeSet()
                    .stream()
                    .map(Road::getName)
                    .sorted()
                    .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Deletes a road from the graph.
     * 
     * @param town1 - name of the first town
     * @param town2 - name of the second town
     * @param road  - the road name
     * @return true if the road was deleted successfully, false otherwise
     */
    @Override
    public boolean deleteRoadConnection(String town1, String town2, String road) {
        return graph.removeEdge(new Town(town1), new Town(town2), 0, road) != null;
    }

    /**
     * Deletes a town from the graph.
     * 
     * @param name - the town's name
     * @return true if the town was deleted successfully, false otherwise
     */
    @Override
    public boolean deleteTown(String name) {
        return graph.removeVertex(new Town(name));
    }

    /**
     * Retrieves all towns sorted alphabetically.
     * 
     * @return a list of town names sorted in alphabetical order
     */
    @Override
    public ArrayList<String> allTowns() {
        return graph.vertexSet()
                    .stream()
                    .map(Town::getName)
                    .sorted()
                    .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Finds the shortest path between two towns.
     * 
     * @param town1 - name of the starting town
     * @param town2 - name of the destination town
     * @return a list of roads connecting the two towns, or null if no path exists
     */
    @Override
    public ArrayList<String> getPath(String town1, String town2) {
        return graph.shortestPath(new Town(town1), new Town(town2));
    }

    /**
     * Populates the graph with data from a file.
     * The file should have the format: road-name,miles;town-name;town-name
     * 
     * @param file - the file containing graph data
     * @throws IOException if an error occurs during file reading
     */
    public void populateTownGraph(File file) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            br.lines()
              .map(line -> line.split(";|,"))
              .forEach(parts -> {
                  addTown(parts[2]);
                  addTown(parts[3]);
                  addRoad(parts[2], parts[3], Integer.parseInt(parts[1]), parts[0]);
              });
        }
    }
}
