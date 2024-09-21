package game;

import geometry.Velocity;
import sprites.Block;
import sprites.Sprite;

import java.util.List;

/**
 * The interface Level information.
 * @author Max Shabs
 */
public interface LevelInformation {
    /**
     * Returns the number of balls.
     *
     * @return the number of balls
     */
    int numberOfBalls();

    /**
     * The initial velocities of each ball.
     *
     * @return the list of velocities
     */
    List<Velocity> initialBallVelocities();

    /**
     * The paddle speed.
     *
     * @return the speed of the paddle
     */
    int paddleSpeed();

    /**
     * The paddle width.
     *
     * @return the width of the paddle.
     */
    int paddleWidth();

    /**
     * The Level name.
     *
     * @return the level's name.
     */
    String levelName();

    /**
     * Gets background.
     *
     * @return a sprite with the background of the level
     */
    Sprite getBackground();

    /**
     * The Blocks in this level, each block contains size, color and position.
     *
     * @return the list of the blocks
     */
    List<Block> blocks();

    /**
     * Number of blocks to remove.
     *
     * @return the number of blocks to be removed.
     */
    int numberOfBlocksToRemove();
}
