package sprites;

import biuoop.DrawSurface;
import collision.Collidable;
import collision.HitListener;
import collision.HitNotifier;
import game.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * Represents a block, defined by a rectangle surface, color and hit listeners.
 * @author Max Shabs
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private static final double EPSILON = 0.00001;
    private Rectangle blockSurface;
    private Color color;
    private List<HitListener> hitListeners;

    /**
     * Instantiates a new block.
     *
     * @param blockRect the block rect
     * @param color     the color
     */
    public Block(Rectangle blockRect, Color color) {
        this.blockSurface = blockRect;
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }
    /**
     * Gets the rectangle surface of the block.
     *
     * @return A rectangle which is the surface of the block.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.blockSurface;
    }
    /**
     * Changes the direction of the object that hit the block.
     *
     * @param collisionPoint  which is the collision point.
     * @param currentVelocity which is the current velocity of the hit object.
     * @return the new velocity of the hit object.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint,
                        Velocity currentVelocity) {
        Velocity curVelocity = currentVelocity;
        double leftX = this.blockSurface.getUpperLeft().getX();
        double rightX = this.blockSurface.getUpperRight().getX();
        double upperY = this.blockSurface.getUpperLeft().getY();
        double lowerY = this.blockSurface.getLowerLeft().getY();
        // checks if the collision point's X is the same as the block's side.
        if ((Math.abs(collisionPoint.getX() - leftX) < EPSILON)
                || (Math.abs(collisionPoint.getX() - rightX) < EPSILON)) {
            curVelocity = new Velocity(currentVelocity.getDx() * (-1),
                                            currentVelocity.getDy());
            this.notifyHit(hitter);
            return curVelocity;
        }
        // checks if the collision point's Y is the same as the block's side.
        if ((Math.abs(collisionPoint.getY() - upperY) < EPSILON)
                || (Math.abs(collisionPoint.getY() - lowerY) < EPSILON)) {
            curVelocity = new Velocity(currentVelocity.getDx(),
                    currentVelocity.getDy() * (-1));
            this.notifyHit(hitter);
            return curVelocity;
        }
        return curVelocity;
    }
    /**
     * Notify the block that time has passed.
     */
    @Override
    public void timePassed() {
        return;
    }
    /**
     * Draws the block on the surface.
     *
     * @param surface the surface which the block will be drawn on.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) this.blockSurface.getUpperLeft().getX(),
                                (int) this.blockSurface.getUpperLeft().getY(),
                                (int) this.blockSurface.getWidth(),
                                (int) this.blockSurface.getHeight());
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) this.blockSurface.getUpperLeft().getX(),
                                (int) this.blockSurface.getUpperLeft().getY(),
                                (int) this.blockSurface.getWidth(),
                                (int) this.blockSurface.getHeight());
    }

    /**
     * Adds the block to the game.
     *
     * @param g the game object.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Remove from game.
     *
     * @param game the game
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }
    /**
     * Notifies the listeners that there has been a hit.
     *
     * @param hitter is the ball that made the hit.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(
                                                            this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
    /**
     * Adds hit listener.
     *
     * @param hl the hit listener.
     */
    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }
    /**
     * Removes hit listener.
     *
     * @param hl the hit listener.
     */
    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }
}
