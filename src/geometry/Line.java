package geometry;

import java.util.List;

/**
 * Represents a line, defined by a start point and an end point.
 * @author Max Shabs
 */
public class Line {
    private static final double EPSILON = 0.00001;
    private Point start;
    private Point end;

    /**
     * A constructor, instantiates a new line.
     *
     * @param start a point that is the start of the line.
     * @param end   a point that is the end of the line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * A constructor, instantiates a new line.
     *
     * @param x1 the x coordinate of the first point.
     * @param y1 the y coordinate of the first point.
     * @param x2 the x coordinate of the second point.
     * @param y2 the y coordinate of the second point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Calculates the length of the line.
     *
     * @return a double which is the length of the line.
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * Calculates the middle point of the line.
     *
     * @return a point which is the middle of the line.
     */
    public Point middle() {
        double midX = (start.getX() + end.getX()) / 2;
        double midY = (start.getY() + end.getY()) / 2;
        return new Point(midX, midY);
    }

    /**
     * Gets the starting point of the line.
     *
     * @return a point which is the start of the line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * Gets the ending point of the line.
     *
     * @return a point which is the end of the line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * Calculates the slope of the line.
     *
     * @param p1 is a point on the line.
     * @param p2 is a point on the line.
     * @return a double which is the slope.
     */
    public double calcSlope(Point p1, Point p2) {
        return (p2.getY() - p1.getY()) / (p2.getX() - p1.getX());
    }

    /**
     * Checks if two line intersect. Returns true if they do, false if not.
     *
     * @param other the other line.
     * @return a boolean which is true if the lines intersect, false if not.
     */
    public boolean isIntersecting(Line other) {
        double thisMaxX = Math.max(this.start.getX(), this.end.getX());
        double thisMinX = Math.min(this.start.getX(), this.end.getX());
        double otherMaxX = Math.max(other.start().getX(), other.end().getX());
        double otherMinX = Math.min(other.start().getX(), other.end().getX());
        double thisMaxY = Math.max(this.start.getY(), this.end.getY());
        double thisMinY = Math.min(this.start.getY(), this.end.getY());
        double otherMaxY = Math.max(other.start().getY(), other.end().getY());
        double otherMinY = Math.min(other.start().getY(), other.end().getY());
        double thisSlope, otherSlope, thisYIntercept, otherYIntercept;
        double intersectionX, intersectionY;
        // checks if both lines are vertical
        if ((Math.abs(thisMaxX - thisMinX) < EPSILON)
                && (Math.abs(otherMaxX - otherMinX) < EPSILON)) {
            // checks if the lines don't have the same X coordinate.
            if (Math.abs(thisMaxX - otherMaxX) > EPSILON) {
                return false;
            }
            // checks if one of the end point is on the other line.
            return (thisMaxY >= otherMinY && thisMaxY <= otherMaxY)
                    || (thisMinY >= otherMinY && thisMinY <= otherMaxY)
                    || (otherMaxY >= thisMinY && otherMaxY <= thisMaxY)
                    || (otherMinY >= thisMinY && otherMinY <= thisMaxY);
        }
        // checks if the one of the lines is vertical and the second isn't.
        if ((Math.abs(thisMaxX - thisMinX) < EPSILON)
                && (Math.abs(otherMaxX - otherMinX) > EPSILON)) {
            // calculates the slope of the other line.
            otherSlope = calcSlope(other.start(), other.end());
            // calculates the slope of the other line.
            otherYIntercept = other.start().getY()
                              - otherSlope * other.start().getX();
            intersectionX = thisMaxX;
            // calculates the Y coordinate of the intersection.
            intersectionY = otherSlope * intersectionX + otherYIntercept;
        } else if ((Math.abs(otherMaxX - otherMinX) < EPSILON)
                    && (Math.abs(thisMaxX - thisMinX) > EPSILON)) {
            // calculates the slope of this line.
            thisSlope = calcSlope(this.start, this.end);
            // calculates the Y-intercept of this line.
            thisYIntercept = this.start.getY() - thisSlope * this.start.getX();
            intersectionX = otherMaxX;
            // calculates the Y coordinate of the intersection.
            intersectionY = thisSlope * intersectionX + thisYIntercept;
        } else {
            // calculates the slope of this line.
            thisSlope = calcSlope(this.start, this.end);
            // calculates the Y coordinate of the intersection.
            thisYIntercept = this.start.getY() - thisSlope * this.start.getX();
            // calculates the slope of the other line.
            otherSlope = calcSlope(other.start(), other.end());
            // calculates the Y-intercept of the other line.
            otherYIntercept = other.start().getY()
                    - otherSlope * other.start().getX();
            // checks if the two slopes are equal and the y intercepts aren't.
            if ((Math.abs(thisSlope - otherSlope) < EPSILON)
                    && (Math.abs(thisYIntercept - otherYIntercept) > EPSILON)) {
                return false;
            }
            // checks if the two slopes are equal and the y intercepts are equal
            if ((Math.abs(thisSlope - otherSlope) < EPSILON)
                    && (Math.abs(thisYIntercept - otherYIntercept) < EPSILON)) {
                // checks if one of the points is in the range of the other line
                return (thisMaxX >= otherMinX && thisMaxX <= otherMaxX)
                        || (thisMinX >= otherMinX && thisMinX <= otherMaxX)
                        || (otherMaxX >= thisMinX && otherMaxX <= thisMaxX)
                        || (otherMinX >= thisMinX && otherMinX <= thisMaxX);
            }
            // calculates the X coordinate of the intersection.
            intersectionX = (thisYIntercept - otherYIntercept)
                            / (otherSlope - thisSlope);
            // calculates the Y coordinate of the intersection.
            intersectionY = thisSlope * intersectionX + thisYIntercept;
        }
        // check if the intersection coordinates are in the range of the lines.
        return ((intersectionX >= thisMinX) && (intersectionX <= thisMaxX)
                && (intersectionX >= otherMinX) && (intersectionX <= otherMaxX)
                && (intersectionY >= thisMinY) && (intersectionY <= thisMaxY)
                && (intersectionY >= otherMinY) && (intersectionY <= otherMaxY))
                || ((this.start.equals(other.start()))
                || (this.start.equals(other.start()))
                || (this.end.equals(other.start()))
                || (this.end.equals(other.end())));
    }

    /**
     * Returns the intersection point of two line, null if they don't have one.
     *
     * @param other the other line.
     * @return the point of intersection, if there isn't return null.
     */
    public Point intersectionWith(Line other) {
        double thisMaxX = Math.max(this.start.getX(), this.end.getX());
        double thisMinX = Math.min(this.start.getX(), this.end.getX());
        double otherMaxX = Math.max(other.start().getX(), other.end().getX());
        double otherMinX = Math.min(other.start().getX(), other.end().getX());
        double thisMaxY = Math.max(this.start.getY(), this.end.getY());
        double thisMinY = Math.min(this.start.getY(), this.end.getY());
        double otherMaxY = Math.max(other.start().getY(), other.end().getY());
        double otherMinY = Math.min(other.start().getY(), other.end().getY());
        double thisSlope, otherSlope, thisYIntercept, otherYIntercept;
        double intersectionX, intersectionY;
        // checks if the lines intersect.
        if (!this.isIntersecting(other)) {
            return null;
        }
        // checks if the two lines are vertical.
        if ((Math.abs(thisMaxX - thisMinX) < EPSILON)
                && (Math.abs(otherMaxX - otherMinX) < EPSILON)) {
            // checks if one of the lines is above the other.
            if (Math.abs(thisMinY - otherMaxY) < EPSILON) {
                return new Point(thisMaxX, thisMinY);
            }
            // checks if one of the lines is below the other.
            if (Math.abs(thisMaxY - otherMinY) < EPSILON) {
                return new Point(thisMaxX, thisMaxY);
            }
            return null;
        }
        // checks if this line is vertical and the other isn't.
        if ((Math.abs(thisMaxX - thisMinX) < EPSILON)
                && (Math.abs(otherMaxX - otherMinX) > EPSILON)) {
            // calculates the slope of the other line.
            otherSlope = calcSlope(other.start(), other.end());
            // calculates the Y-intercept of the other line.
            otherYIntercept = other.start().getY()
                    - otherSlope * other.start().getX();
            intersectionX = thisMaxX;
            // calculates the Y coordinate of the intersection.
            intersectionY = otherSlope * intersectionX + otherYIntercept;
            return new Point(intersectionX, intersectionY);
        }
        // checks if the other line is vertical and this isn't
        if ((Math.abs(otherMaxX - otherMinX) < EPSILON)
                && (Math.abs(thisMaxX - thisMinX) > EPSILON)) {
            // calculates the slope of this line.
            thisSlope = calcSlope(this.start, this.end);
            // calculates the Y-intercept of this line.
            thisYIntercept = this.start.getY() - thisSlope * this.start.getX();
            intersectionX = otherMaxX;
            // calculates the Y coordinate of the intersection.
            intersectionY = thisSlope * intersectionX + thisYIntercept;
            return new Point(intersectionX, intersectionY);
        }
        // calculates the slope of this line.
        thisSlope = calcSlope(this.start, this.end);
        // calculates the Y-intercept of this line.
        thisYIntercept = this.start.getY() - thisSlope * this.start.getX();
        // calculates the slope of the other line.
        otherSlope = calcSlope(other.start(), other.end());
        // calculates the Y-intercept of the other line.
        otherYIntercept = other.start().getY()
                - otherSlope * other.start().getX();
        // checks if the two slopes are equal and the Y-intercepts are equal.
        if ((Math.abs(thisSlope - otherSlope) < EPSILON)
                && (Math.abs(thisYIntercept - otherYIntercept)
                < EPSILON)) {
            // checks if one of the lines continues the other.
            if (Math.abs(thisMinX - otherMaxX) < EPSILON) {
                // calculates the Y coordinate of the intersection.
                intersectionY = thisSlope * thisMinX + thisYIntercept;
                return new Point(thisMinX, intersectionY);
            }
            // checks if one of the lines continues the other.
            if (Math.abs(thisMaxX - otherMinX) < EPSILON) {
                // calculates the Y coordinate of the intersection.
                intersectionY = thisSlope * thisMaxX + thisYIntercept;
                return new Point(thisMaxX, intersectionY);
            }
            return null;
        }
        // calculates the X coordinate of the intersection.
        intersectionX = (thisYIntercept - otherYIntercept)
                / (otherSlope - thisSlope);
        // calculates the Y coordinate of the intersection.
        intersectionY = thisSlope * intersectionX + thisYIntercept;
        return new Point(intersectionX, intersectionY);
    }

    /**
     * Checks if the two lines are equal. True if they are, false if not.
     *
     * @param other is the other line.
     * @return boolean which is true if the lines are equal,false if not.
     */
    public boolean equals(Line other) {
        double thisMaxX = Math.max(this.start.getX(), this.end.getX());
        double thisMinX = Math.min(this.start.getX(), this.end.getX());
        double otherMaxX = Math.max(other.start().getX(), other.end().getX());
        double otherMinX = Math.min(other.start().getX(), other.end().getX());
        double thisMaxY = Math.max(this.start.getY(), this.end.getY());
        double thisMinY = Math.min(this.start.getY(), this.end.getY());
        double otherMaxY = Math.max(other.start().getY(), other.end().getY());
        double otherMinY = Math.min(other.start().getY(), other.end().getY());
        double thisSlope, otherSlope;
        // checks if both lines are vertical.
        if ((Math.abs(thisMaxX - thisMinX) < EPSILON)
                && (Math.abs(otherMaxX - otherMinX) < EPSILON)) {
            // checks if both lines don't have the same x coordinate.
            if (Math.abs(thisMaxX - otherMaxX) > EPSILON) {
                return false;
            }
            // checks if they have the same maximal and minimal y coordinates.
            return (Math.abs(thisMinY - otherMinY) < EPSILON)
                    && (Math.abs(thisMaxY - otherMaxY) < EPSILON);
        }
        // checks if one of the lines is vertical and the second isn't.
        if (((Math.abs(thisMaxX - thisMinX) < EPSILON)
                && (Math.abs(otherMaxX - otherMinX) > EPSILON))
                || ((Math.abs(otherMaxX - otherMinX) < EPSILON)
                && (Math.abs(thisMaxX - thisMinX) > EPSILON))) {
            return false;
        }
        // calculates the slope of this line.
        thisSlope = calcSlope(this.start, this.end);
        // calculates the slope of the other line.
        otherSlope = calcSlope(other.start(), other.end());
        // checks if the lines have the same slope and start and end points.
        return (Math.abs(thisSlope - otherSlope) < EPSILON)
                && ((this.start.equals(other.start())
                    && (this.end.equals(other.end())))
                    || ((this.start.equals(other.end()))
                    && (this.end.equals(other.start()))));
    }

    /**
     * Finds the closest intersection with the line start, null if there isn't.
     *
     * @param rect the rectangle we check the intersection with.
     * @return the point of intersection/
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> interPoints = rect.intersectionPoints(this);
        Point firstInter, secondInter;
        // checks if there are any collision points in the list.
        if (interPoints.isEmpty()) {
            return null;
        }
        // checks if there's only one collision point.
        if (interPoints.size() == 1) {
            return interPoints.get(0);
        }
        firstInter = interPoints.get(0);
        secondInter = interPoints.get(1);
        // checks if the second intersection point is further than the first.
        if (this.start.distance(firstInter)
                <= this.start.distance(secondInter)) {
            return firstInter;
        }
        return secondInter;
    }
}
