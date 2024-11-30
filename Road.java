/**
 * Represents the edges of a Graph of Towns. Each road connects two towns and includes the distance
 * between them and the road's name. Since this is an undirected graph, a road from A to B is 
 * considered equivalent to a road from B to A.
 * 
 * @author Hussain Mohammad
 */
public class Road implements Comparable<Road> {

    private final Town source;
    private final Town destination;
    private final int distance;
    private final String name;

    /**
     * Constructor to initialize a road with specified distance.
     * 
     * @param source      one town on the road
     * @param destination another town on the road
     * @param distance    the distance between the two towns
     * @param name        the name of the road
     */
    public Road(Town source, Town destination, int distance, String name) {
        if (source == null || destination == null || name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Source, destination, and road name cannot be null or empty.");
        }
        if (distance < 0) {
            throw new IllegalArgumentException("Distance cannot be negative.");
        }
        this.source = source;
        this.destination = destination;
        this.distance = distance;
        this.name = name;
    }

    /**
     * Constructor to initialize a road with a default distance of 1.
     * 
     * @param source      one town on the road
     * @param destination another town on the road
     * @param name        the name of the road
     */
    public Road(Town source, Town destination, String name) {
        this(source, destination, 1, name);
    }

    /**
     * Compares two roads by their names lexicographically.
     * 
     * @param other the road to compare to
     * @return a negative, zero, or positive integer as this road's name is lexicographically 
     *         less than, equal to, or greater than the other road's name
     */
    @Override
    public int compareTo(Road other) {
        return this.name.compareTo(other.name);
    }

    /**
     * Checks if the road connects to the given town.
     * 
     * @param town a town to check
     * @return true if the road connects to the specified town, false otherwise
     */
    public boolean contains(Town town) {
        return source.equals(town) || destination.equals(town);
    }

    /**
     * Determines equality based on the towns and direction-independence of the road.
     * 
     * @param obj the object to compare with
     * @return true if the roads connect the same towns, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Road)) {
            return false;
        }
        Road other = (Road) obj;
        return (this.source.equals(other.source) && this.destination.equals(other.destination))
                || (this.source.equals(other.destination) && this.destination.equals(other.source));
    }

    /**
     * Generates a hash code based on the towns and road name.
     * 
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return source.hashCode() + destination.hashCode() + name.hashCode();
    }

    /**
     * Provides a string representation of the road.
     * Format: "TownA via RoadName to TownB Distance mi"
     * 
     * @return a string representation of the road
     */
    @Override
    public String toString() {
        return source.getName() + " via " + name + " to " + destination.getName() + " " + distance + " mi";
    }

    /**
     * Gets the first town (source) of the road.
     * 
     * @return the source town
     */
    public Town getSource() {
        return source;
    }

    /**
     * Gets the second town (destination) of the road.
     * 
     * @return the destination town
     */
    public Town getDestination() {
        return destination;
    }

    /**
     * Gets the distance of the road.
     * 
     * @return the distance
     */
    public int getDistance() {
        return distance;
    }

    /**
     * Gets the name of the road.
     * 
     * @return the road's name
     */
    public String getName() {
        return name;
    }
}
