package geometry;

/**
 * Represents velocity, defined by dx, dy which are the y and x-axis velocities.
 * @author Max Shabs
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * The constructor, instantiates a new velocity.
     *
     * @param dx the x-axis velocity.
     * @param dy the y-axis velocity.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Apply the velocity to the point.
     *
     * @param p the point.
     * @return the point with added velocity.
     */
    public Point applyToPoint(Point p) {
        double pointX = p.getX() + this.dx;
        double pointY = p.getY() + this.dy;
        return new Point(pointX, pointY);
    }

    /**
     * Gets x-axis velocity.
     *
     * @return the dx which is the x-axis velocity.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Gets y-axis velocity.
     *
     * @return the dy which is the y-axis velocity.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Calculates the x and y-axis velocity given the angle and the speed.
     *
     * @param angle the angle of the speed
     * @param speed the speed
     * @return the velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double radians = (angle - 90) * Math.PI / 180;
        double dx = speed * Math.cos(radians);
        double dy = speed * Math.sin(radians);
        return new Velocity(dx, dy);
    }
}
