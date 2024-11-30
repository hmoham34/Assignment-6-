import java.util.Objects;

/**
 * Represents a town as a node (vertex) in a graph.
 * 
 * @author Hussain Mohammad
 */
public class Town implements Comparable<Town> {

    private final String name;

    /**
     * Constructor to initialize a town with its name.
     * 
     * @param name the name of the town
     */
    public Town(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Town name cannot be null or empty.");
        }
        this.name = name;
    }

    /**
     * Copy constructor to create a new Town based on an existing one.
     * 
     * @param templateTown the Town to copy
     */
    public Town(Town templateTown) {
        if (templateTown == null) {
            throw new IllegalArgumentException("Template town cannot be null.");
        }
        this.name = templateTown.name;
    }

    /**
     * Compares two towns by their names.
     * 
     * @param other the town to compare to
     * @return a negative integer, zero, or a positive integer as this town's name
     *         is lexicographically less than, equal to, or greater than the other town's name
     */
    @Override
    public int compareTo(Town other) {
        return this.name.compareTo(other.name);
    }

    /**
     * Determines equality based on the town's name.
     * 
     * @param obj the object to compare with
     * @return true if the other object is a Town with the same name, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Town other) {
            return this.name.equals(other.name);
        }
        return false;
    }

    /**
     * Returns the hash code for the town based on its name.
     * 
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /**
     * Returns the name of the town.
     * 
     * @return the town's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a string representation of the town, which is its name.
     * 
     * @return the town's name
     */
    @Override
    public String toString() {
        return name;
    }
}
