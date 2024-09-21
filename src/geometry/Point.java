package geometry;

/**
 * Represents a point, defined by X and Y values.
 * @author Max Shabs
 */
public class Point {
    private static final double EPSILON = 0.00001;
    private double x;
    private double y;

    /**
     * The constructor, instantiates a new point.
     *
     * @param x the x coordinate of the point.
     * @param y the y coordinate of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates the distance of this point to the other point.
     *
     * @param other the other point.
     * @return double which is the distance of this point to the other point.
     */
    public double distance(Point other) {
        // calculates the distance using Pythagoras formula.
        return Math.sqrt(((this.x - other.x) * (this.x - other.x))
                        + ((this.y - other.y) * (this.y - other.y)));
    }

    /**
     * Checks if two point are equal.
     *
     * @param other the other point.
     * @return a boolean, return true is the points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        // checks if other point is null.
        if (other == null) {
            return false;
        }
        return (Math.abs(this.x - other.getX()) < EPSILON)
                && (Math.abs(this.y - other.getY()) < EPSILON);
    }

    /**
     * Gets x value of the point.
     *
     * @return a double which is the x value of the point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * Gets y value of the point.
     *
     * @return a double which is the y value of the point.
     */
    public double getY() {
        return this.y;
    }
}
