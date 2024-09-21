package levels;

import game.LevelInformation;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import sprites.Block;
import sprites.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The level Direct hit.
 * @author Max Shabs
 */
public class DirectHit implements LevelInformation {
    /**
     * Returns the number of balls.
     *
     * @return the number of balls
     */
    @Override
    public int numberOfBalls() {
        return 1;
    }

    /**
     * The initial velocities of each ball.
     *
     * @return the list of velocities
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(new Velocity(0, -6));
        return velocities;
    }

    /**
     * The paddle speed.
     *
     * @return the speed of the paddle
     */
    @Override
    public int paddleSpeed() {
        return 10;
    }

    /**
     * The paddle width.
     *
     * @return the width of the paddle.
     */
    @Override
    public int paddleWidth() {
        return 80;
    }

    /**
     * The Level name.
     *
     * @return the level's name.
     */
    @Override
    public String levelName() {
        return "Direct Hit";
    }

    /**
     * Gets background.
     *
     * @return a sprite with the background of the level
     */
    @Override
    public Sprite getBackground() {
        return new Block(new Rectangle(new Point(0, 0),
                    800, 600), Color.BLACK);
    }

    /**
     * The Blocks in this level, each block contains size, color and position.
     *
     * @return the list of the blocks
     */
    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        Block block = new Block(new Rectangle(new Point(385, 200),
                30, 30), Color.RED);
        blocks.add(block);
        return blocks;
    }

    /**
     * Number of blocks to remove.
     *
     * @return the number of blocks to be removed.
     */
    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
