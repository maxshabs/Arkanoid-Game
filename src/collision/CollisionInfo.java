package collision;

import geometry.Point;

/**
 * Represents collision info, defined by the collision point and object.
 * @author Max Shabs
 */
public class CollisionInfo {
    private Point colPoint;
    private Collidable colObject;

    /**
     * Instantiates a new Collision info.
     *
     * @param colPoint  the collision point.
     * @param colObject the collision object.
     */
    public CollisionInfo(Point colPoint, Collidable colObject) {
        this.colPoint = colPoint;
        this.colObject = colObject;
    }

    /**
     * Gets the collision point.
     *
     * @return the collision point
     */
    public Point collisionPoint() {
        return this.colPoint;
    }

    /**
     * Gets the collision object.
     *
     * @return the collidable object.
     */
    public Collidable collisionObject() {
        return this.colObject;
    }
}