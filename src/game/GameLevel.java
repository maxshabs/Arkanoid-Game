package game;

import animation.Animation;
import animation.AnimationRunner;
import animation.CountdownAnimation;
import animation.KeyPressStoppableAnimation;
import animation.PauseScreen;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import collision.BallRemover;
import collision.BlockRemover;
import collision.Collidable;
import geometry.Point;
import geometry.Rectangle;
import sprites.Ball;
import sprites.Block;
import sprites.LevelName;
import sprites.Paddle;
import sprites.ScoreIndicator;
import sprites.Sprite;
import sprites.SpriteCollection;

import java.awt.Color;

/**
 * Represents the game, defined by sprites, collidable objects counters and gui.
 * @author Max Shabs
 */
public class GameLevel implements Animation {
    private static final int BALL_RADIUS = 6;
    private static final double UPPER_BOUNDARY_WIDTH = 800;
    private static final double WIDE_BOUNDARY_HEIGHT = 40;
    private static final double LOWER_BOUNDARY_WIDTH = 10000;
    private static final double LOWER_BOUNDARY_Y = 620;
    private static final double LOWER_BOUNDARY_X = -5000;
    private static final double LOWER_BOUNDARY_HEIGHT = 100;
    private static final double SIDE_BOUNDARY_HEIGHT = 580;
    private static final double SIDE_BOUNDARY_WIDTH = 20;
    private static final double SIDE_UPPER_LEFT_Y = 40;
    private static final double RIGHT_UPPER_LEFT_X = 780;
    private static final double MINIMAL_X = 0;
    private static final double MINIMAL_Y = 0;
    private static final int CLEAR_LEVEL_BONUS = 100;
    private static final int STARTING_BALL_X = 397;
    private static final int STARTING_BALL_Y = 550;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Counter numOfBlocks;
    private Counter numOfBalls;
    private Counter score;
    private AnimationRunner runner;
    private KeyboardSensor keyboard;
    private boolean running;
    private LevelInformation levelInfo;

    /**
     * Instantiates a new game.
     *
     * @param levelInfo is the information about the level.
     * @param keyboard  the keyboard
     * @param runner    the runner
     * @param score     the score
     */
    public GameLevel(LevelInformation levelInfo, KeyboardSensor keyboard,
                     AnimationRunner runner, Counter score) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.levelInfo = levelInfo;
        this.gui = runner.getGui();
        this.runner = runner;
        this.keyboard = keyboard;
        this.numOfBlocks = new Counter();
        this.numOfBalls = new Counter();
        this.score = score;
    }

    /**
     * Gets num of balls.
     *
     * @return the num of balls
     */
    public Counter getNumOfBalls() {
        return this.numOfBalls;
    }

    /**
     * Gets num of blocks.
     *
     * @return the num of blocks
     */
    public Counter getNumOfBlocks() {
        return this.numOfBlocks;
    }

    /**
     * Adds a collidable.
     *
     * @param c is the collidable.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Adds a sprite.
     *
     * @param s is the sprite.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Initializes a new game, creates blocks and ball and adds them to game.
     */
    public void initialize() {
        this.addSprite(levelInfo.getBackground());
        ScoreTrackingListener scoreListener = new ScoreTrackingListener(
                                                            this.score);
        BlockRemover blockRemover = new BlockRemover(this, this.numOfBlocks);
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score);
        Paddle paddle = new Paddle(gui.getKeyboardSensor(),
                        levelInfo.paddleSpeed(), levelInfo.paddleWidth());
        paddle.addToGame(this);
        // creates the balls and adds them to the game.
        for (int i = 0; i < this.levelInfo.numberOfBalls(); i++) {
            Ball thisBall = new Ball(new Point(STARTING_BALL_X,
                                STARTING_BALL_Y), BALL_RADIUS, Color.WHITE);
            thisBall.setVelocity(levelInfo.initialBallVelocities().get(i));
            thisBall.setGameEnvironment(environment);
            thisBall.addToGame(this);
        }
        Rectangle upperRect = new Rectangle(new Point(MINIMAL_X, MINIMAL_Y),
                                            UPPER_BOUNDARY_WIDTH,
                                            WIDE_BOUNDARY_HEIGHT);
        Rectangle deathRect = new Rectangle(new Point(LOWER_BOUNDARY_X,
                                                        LOWER_BOUNDARY_Y),
                                            LOWER_BOUNDARY_WIDTH,
                                            LOWER_BOUNDARY_HEIGHT);
        Block upperBoundary = new Block(upperRect, Color.GRAY);
        Block deathBoundary = new Block(deathRect, Color.GRAY);
        Rectangle leftRect = new Rectangle(new Point(MINIMAL_X,
                                                    SIDE_UPPER_LEFT_Y),
                                            SIDE_BOUNDARY_WIDTH,
                                            SIDE_BOUNDARY_HEIGHT);
        Rectangle rightRect = new Rectangle(new Point(RIGHT_UPPER_LEFT_X,
                                                        SIDE_UPPER_LEFT_Y),
                                            SIDE_BOUNDARY_WIDTH,
                                            SIDE_BOUNDARY_HEIGHT);
        Block leftBoundary = new Block(leftRect, Color.GRAY);
        Block rightBoundary = new Block(rightRect, Color.GRAY);
        upperBoundary.addToGame(this);
        deathBoundary.addToGame(this);
        deathBoundary.addHitListener(new BallRemover(this, this.numOfBalls));
        leftBoundary.addToGame(this);
        rightBoundary.addToGame(this);
        sprites.addSprite(scoreIndicator);
        // adds the blocks to the game.
        for (Block block : levelInfo.blocks()) {
            block.addToGame(this);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreListener);
            this.numOfBlocks.increase(1);
        }
        this.addSprite(new LevelName(levelInfo));
    }

    /**
     * Runs the game, starts the animation loop.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.running = true;
        this.runner.run(this);
    }

    /**
     * Removes collidable from the game.
     *
     * @param c the collidable.
     */
    public void removeCollidable(Collidable c) {
        environment.getCollideObjects().remove(c);
    }

    /**
     * Removes sprite from the game.
     *
     * @param s the sprite.
     */
    public void removeSprite(Sprite s) {
        sprites.getSpritesList().remove(s);
    }
    /**
     * Plays one frame.
     *
     * @param d the draw surface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        Animation keyPressStoppable;
        // checks if the user pressed the key p.
        if (this.keyboard.isPressed("p")) {
            Animation pauseScreen = new PauseScreen(this.keyboard);
            keyPressStoppable =
                    new KeyPressStoppableAnimation(this.keyboard,
                                                    KeyboardSensor.SPACE_KEY,
                                                    pauseScreen);
            this.runner.run(keyPressStoppable);
        }
        // checks if there are no balls left.
        if (this.numOfBalls.getValue() == 0) {
            this.running = false;
        }
        // checks if there are no blocks left.
        if (this.numOfBlocks.getValue() == 0) {
            score.increase(CLEAR_LEVEL_BONUS);
            this.running = false;
        }
    }
    /**
     * Checks if the animation should stop.
     *
     * @return the boolean that represents whether the animation should stop.
     */
    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}