package sprites;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collision.Collidable;
import game.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;

import java.awt.Color;

/**
 * Represents a paddle, defined by keyboard, paddle surface and color.
 * @author Max Shabs
 */
public class Paddle implements Sprite, Collidable {
    private static final double EPSILON = 0.00001;
    private static final double HEIGHT = 15.0;
    private static final double LEFT_BORDER = 20.0;
    private static final double RIGHT_BORDER = 780.0;
    private biuoop.KeyboardSensor keyboard;
    private Rectangle paddleSurface;
    private Color color;
    private int paddleSpeed;
    private int paddleWidth;

    /**
     * Instantiates a new paddle.
     *
     * @param keyboard the keyboard that gives commands.
     * @param paddleSpeed the speed of the paddle.
     * @param paddleWidth the width of the paddle.
     */
    public Paddle(KeyboardSensor keyboard, int paddleSpeed, int paddleWidth) {
        this.keyboard = keyboard;
        this.paddleSurface =
                new Rectangle(new Point(400 - ((double) paddleWidth / 2),
                580 - HEIGHT), paddleWidth, HEIGHT);
        color = Color.ORANGE;
        this.paddleSpeed = paddleSpeed;
        this.paddleWidth = paddleWidth;
    }

    /**
     * Moves the paddle to the left.
     */
    public void moveLeft() {
        double curUpLeftX = this.paddleSurface.getUpperLeft().getX();
        double curUpLeftY = this.paddleSurface.getUpperLeft().getY();
        // checks if the paddle hasn't reached the border.
        if (LEFT_BORDER <= curUpLeftX - this.paddleSpeed) {
            this.paddleSurface = new Rectangle(new Point(curUpLeftX
                                                        - this.paddleSpeed,
                                                curUpLeftY), this.paddleWidth,
                                                HEIGHT);
        }
    }

    /**
     * Moves the paddle to the right.
     */
    public void moveRight() {
        double curUpRightX = this.paddleSurface.getUpperRight().getX();
        double curUpLeftX = this.paddleSurface.getUpperLeft().getX();
        double curUpLeftY = this.paddleSurface.getUpperLeft().getY();
        // checks if the paddle hasn't reached the border.
        if (RIGHT_BORDER >= curUpRightX + this.paddleSpeed) {
            this.paddleSurface = new Rectangle(new Point(curUpLeftX
                                                + this.paddleSpeed,
                                                curUpLeftY), this.paddleWidth,
                                                HEIGHT);
        }
    }

    /**
     * Notify the paddle that time has passed and invokes a movement.
     */
    @Override
    public void timePassed() {
        // checks if the key pressed was the left key.
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        // checks if the key pressed was the right key.
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }
    /**
     * Draws the paddle on the surface.
     *
     * @param d the surface which the paddle will be drawn on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.paddleSurface.getUpperLeft().getX(),
                (int) this.paddleSurface.getUpperLeft().getY(),
                (int) this.paddleSurface.getWidth(),
                (int) this.paddleSurface.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.paddleSurface.getUpperLeft().getX(),
                (int) this.paddleSurface.getUpperLeft().getY(),
                (int) this.paddleSurface.getWidth(),
                (int) this.paddleSurface.getHeight());
    }
    /**
     * Gets the rectangle surface of the paddle.
     *
     * @return A rectangle which is the surface of the paddle.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.paddleSurface;
    }
    /**
     * Changes the direction of the object that hit the paddle.
     *
     * @param collisionPoint  which is the collision point.
     * @param currentVelocity which is the current velocity of the hit object.
     * @return the new velocity of the hit object.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint,
                        Velocity currentVelocity) {
        Velocity curVelocity = currentVelocity;
        double newDy;
        double regionWidth = (double) this.paddleWidth / 5;
        int curRegion;
        double hitAngle, hitSpeed;
        double leftX = this.paddleSurface.getUpperLeft().getX();
        double rightX = this.paddleSurface.getUpperRight().getX();
        double upperY = this.paddleSurface.getUpperLeft().getY();
        // checks if the collision point's Y is the same as the paddles top.
        if (Math.abs(collisionPoint.getY() - upperY) < EPSILON) {
            curRegion =  (int) ((collisionPoint.getX() - leftX) / regionWidth);
            // checks in what part of the paddle the collision occurred.
            if (curRegion == 0) {
                hitAngle = 300;
            } else if (curRegion == 1) {
                hitAngle = 330;
            } else if (curRegion == 2) {
                newDy = currentVelocity.getDy() * (-1);
                curVelocity = new Velocity(currentVelocity.getDx(), newDy);
                return curVelocity;
            } else if (curRegion == 3) {
                hitAngle = 30;
            } else {
                hitAngle = 60;
            }
            hitSpeed = Math.sqrt(Math.pow(currentVelocity.getDx(), 2)
                                + Math.pow(currentVelocity.getDy(), 2));
            curVelocity = Velocity.fromAngleAndSpeed(hitAngle, hitSpeed);
        }
        // checks if the collision point's X is the same as the paddle's side.
        if ((Math.abs(collisionPoint.getX() - leftX) < EPSILON)
                || (Math.abs(collisionPoint.getX() - rightX) < EPSILON)) {
            curVelocity = new Velocity(currentVelocity.getDx() * (-1),
                    currentVelocity.getDy());
        }
        return curVelocity;
    }

    /**
     * Adds the paddle to the game.
     *
     * @param g the game object.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}
