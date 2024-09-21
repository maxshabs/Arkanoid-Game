package geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a rectangle, defined by upper left point, width and height.
 * @author Max Shabs
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Instantiates a new rectangle.
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     */
// Create a new rectangle with location and width/height.
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Finds the intersection points with the line.
     *
     * @param line the line
     * @return a (possibly empty) List of intersection points with line
     */
    public List<Point> intersectionPoints(Line line) {
        Point lowerLeft = new Point(getLowerLeft().getX(),
                                    getLowerLeft().getY());
        Point upperRight = new Point(getUpperRight().getX(),
                                    getUpperRight().getY());
        Point lowerRight = new Point(getLowerRight().getX(),
                                    getLowerRight().getY());
        Line upperSide = new Line(this.upperLeft, upperRight);
        Line lowerSide = new Line(lowerLeft, lowerRight);
        Line leftSide = new Line(this.upperLeft, lowerLeft);
        Line rightSide = new Line(upperRight, lowerRight);
        List<Point> intersectionList = new ArrayList<>();
        Point interUpper = upperSide.intersectionWith(line);
        Point interLower = lowerSide.intersectionWith(line);
        Point interLeft = leftSide.intersectionWith(line);
        Point interRight = rightSide.intersectionWith(line);
        // checks if the upper side intersects with the line.
        if (interUpper != null) {
            intersectionList.add(interUpper);
        }
        // checks if the lower side intersects with the line.
        if (interLower != null) {
            // checks if the intersection point already exists in the list.
            if (!interLower.equals(interUpper)) {
                intersectionList.add(interLower);
            }
        }
        // checks if the left side intersects with the line.
        if (interLeft != null) {
            // checks if the intersection point already exists in the list.
            if (!interLeft.equals(interUpper)
                    && !interLeft.equals(interLower)) {
                intersectionList.add(interLeft);
            }
        }
        // checks if the right side intersects with the line.
        if (interRight != null) {
            // checks if the intersection point already exists in the list.
            if (!interRight.equals(interUpper)
                    && !interRight.equals(interLower)
                    && !interRight.equals(interLeft)) {
                intersectionList.add(interRight);
            }
        }
        return intersectionList;
    }

    /**
     * Gets the width of the rectangle.
     *
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Gets the height of the rectangle.
     *
     * @return the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Gets the upper left point of the rectangle.
     *
     * @return the upper left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Gets the lower left point of the rectangle.
     *
     * @return the lower left point of the rectangle.
     */
    public Point getLowerLeft() {
        return new Point(upperLeft.getX(), upperLeft.getY() + this.height);
    }

    /**
     * Gets the upper right point of the rectangle.
     *
     * @return the upper right point of the rectangle.
     */
    public Point getUpperRight() {
        return new Point(upperLeft.getX() + this.width, upperLeft.getY());
    }

    /**
     * Gets the lower right point of the rectangle.
     *
     * @return the lower right point of the rectangle.
     */
    public Point getLowerRight() {
        return new Point(upperLeft.getX() + this.width,
                        upperLeft.getY() + this.height);
    }
}
