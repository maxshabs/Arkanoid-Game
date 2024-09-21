package collision;

import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import sprites.Ball;

/**
 * The interface Collidable.
 * @author Max Shabs
 */
public interface Collidable {
    /**
     * Gets collision rectangle.
     *
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Changes the direction of the hit object after the hit with a collidable.
     *
     * @param hitter          the object that hit the collidable.
     * @param collisionPoint  the collision point with the other object.
     * @param currentVelocity the velocity before the hit.
     * @return the new velocity after the hit.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
