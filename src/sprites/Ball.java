package sprites;

import biuoop.DrawSurface;
import collision.CollisionInfo;
import game.GameEnvironment;
import game.GameLevel;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;

import java.awt.Color;

/**
 * Represents a ball defined by center, radius, color and velocity.
 * @author Max Shabs
 */
public class Ball implements Sprite {
    private static final double DEFAULT_SPEED = 0;
    private Point center;
    private int r;
    private Color color;
    private Velocity v = new Velocity(DEFAULT_SPEED, DEFAULT_SPEED);
    private DrawSurface surface;
    private GameEnvironment gameEnvironment;
    private static final double PROXIMITY = 5.0;

    /**
     * A constructor, instantiates a new ball.
     *
     * @param center the center of the ball.
     * @param r      the radius of the ball.
     * @param color  the color of the ball.
     */
    public Ball(Point center, int r, Color color) {
        this.center = center;
        // checks if the radius is negative and turns it to positive.
        if (r < 0) {
            this.r = r * (-1);
        } else {
            this.r = r;
        }
        this.color = color;
    }

    /**
     * A constructor, instantiates a new ball.
     *
     * @param xCenter the x value of the center of the ball.
     * @param yCenter the y value of the center of the ball.
     * @param r       the radius of the ball.
     * @param color   the color of the ball.
     */
    public Ball(double xCenter, double yCenter, int r, Color color) {
        this.center = new Point(xCenter, yCenter);
        // checks if the radius is negative and turns it to positive.
        if (r < 0) {
            this.r = r * (-1);
        } else {
            this.r = r;
        }
        this.color = color;
    }

    /**
     * Gets x coordinate of the center of the ball.
     *
     * @return the x value of the center.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Gets y coordinate of the center of the ball.
     *
     * @return the y value of the center.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Gets the radius of the ball.
     *
     * @return the size of the radius of the ball.
     */
    public int getSize() {
        return this.r;
    }

    /**
     * Gets color.
     *
     * @return the color of the ball.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Draws the ball on given surface.
     *
     * @param surface the surface which the ball will be drawn on.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        this.surface = surface;
        // checks if the radius is larger than the width of the surface.
        if (this.r > this.surface.getWidth() / 2) {
            this.r = this.surface.getWidth() / 2;
        }
        // checks if the radius is larger than the height of the surface.
        if (this.r > this.surface.getHeight() / 2) {
            this.r = this.surface.getHeight() / 2;
        }
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
        surface.setColor(Color.BLACK);
        surface.drawCircle(this.getX(), this.getY(), this.getSize());
    }

    /**
     * Notify the ball that time has passed and invokes moveOneStep.
     */
    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * Sets the velocity of the ball.
     *
     * @param v the velocity of the ball.
     */
    public void setVelocity(Velocity v) {
        this.v = v;
    }

    /**
     * Sets the velocity of the ball.
     *
     * @param dx the velocity on the x-axis of the ball.
     * @param dy the velocity on the y-axis of the ball.
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * Sets game environment.
     *
     * @param gameEnvironment the game environment
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Gets the velocity of the ball.
     *
     * @return the velocity of the ball
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * Moves the ball, if there is a collision changes the velocity of the ball.
     */
    public void moveOneStep() {
        double proximity = r / PROXIMITY;
        Point nextPoint = this.getVelocity().applyToPoint(this.center);
        Line trajectory = new Line(this.center, nextPoint);
        CollisionInfo thisCol =
                this.gameEnvironment.getClosestCollision(trajectory);
        // checks if the surface is null.
        if (this.surface == null) {
            this.center = nextPoint;
            return;
        }
        // checks if there is a collision incoming.
        if (thisCol != null) {
            Point colPoint = thisCol.collisionPoint();
            Rectangle colRect =
                    thisCol.collisionObject().getCollisionRectangle();
            Point nearCol = thisCol.collisionPoint();
            Velocity newVelocity = thisCol.collisionObject().hit(this,
                                                                    colPoint,
                                                                    this.v);
            // checks if the ball is going to the right.
            if ((this.v.getDx() > 0)
                    && (nextPoint.getX() >= colRect.getUpperLeft().getX())) {
                nearCol = new Point(colPoint.getX() - proximity,
                                        colPoint.getY());
            } else if ((this.v.getDx() < 0)
                    && (nextPoint.getX() <= colRect.getUpperRight().getX())) {
                nearCol = new Point(colPoint.getX() + proximity,
                                        colPoint.getY());
            }
            // checks if the ball is going down.
            if ((this.v.getDy() > 0)
                    && (nextPoint.getY() >= colRect.getUpperLeft().getY())) {
                nearCol = new Point(nearCol.getX(),
                                        colPoint.getY() - proximity);
            } else if ((this.v.getDy() < 0)
                    && (nextPoint.getY() <= colRect.getLowerLeft().getY())) {
                nearCol = new Point(nearCol.getX(),
                                        colPoint.getY() + proximity);
            }
            this.center = nearCol;
            this.setVelocity(newVelocity);
        } else {
            this.center = nextPoint;
        }
    }

    /**
     * Adds the ball into the game.
     *
     * @param g the game object.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.getNumOfBalls().increase(1);
    }

    /**
     * Removes this object from game.
     *
     * @param game the game.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}