package game;

import collision.Collidable;
import collision.CollisionInfo;
import geometry.Line;
import geometry.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the game environment, defined by a collidables list.
 * @author Max Shabs
 */
public class GameEnvironment {
    private List<Collidable> collideObjects;

    /**
     * Instantiates a new game environment.
     */
    public GameEnvironment() {
        collideObjects = new ArrayList<>();
    }

    /**
     * Adds a collidable to the environment.
     *
     * @param c is the collidable we want to add.
     */
    public void addCollidable(Collidable c) {
        this.collideObjects.add(c);
    }

    /**
     * Gets a lis of the collidable objects.
     *
     * @return the collidable objects list.
     */
    public List<Collidable> getCollideObjects() {
        return this.collideObjects;
    }

    /**
     * Gets the closest collision to collidable, if there isn't returns null.
     *
     * @param trajectory the trajectory of an object.
     * @return the closest collision info.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<Point> colPoints = new ArrayList<>();
        List<Collidable> colObjects = new ArrayList<>();
        List<Collidable> collideObjectsCopy =
                                        new ArrayList<>(this.collideObjects);
        Point curInter;
        // goes over all the collidable objects.
        for (Collidable col: collideObjectsCopy) {
            curInter = trajectory.closestIntersectionToStartOfLine(
                                            col.getCollisionRectangle());
            // checks if there is an intersection.
            if (curInter != null) {
                colPoints.add(curInter);
                colObjects.add(col);
            }
        }
        // checks if there were any collisions.
        if (colPoints.isEmpty()) {
            return null;
        }
        Point closestPoint = colPoints.get(0);
        Collidable closestCol = colObjects.get(0);
        double minDistance = trajectory.start().distance(closestPoint);
        // goes over the rest of the list.
        for (int i = 1; i < colPoints.size(); i++) {
            // checks if the current collision point is closer to the trajectory
            if (minDistance > trajectory.start().distance(colPoints.get(i))) {
                closestPoint = colPoints.get(i);
                closestCol = colObjects.get(i);
                minDistance = trajectory.start().distance(closestPoint);
            }
        }
        return new CollisionInfo(closestPoint, closestCol);
    }
}