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
 * The level Green three.
 * @author Max Shabs
 */
public class GreenThree implements LevelInformation {
    private static final double MIN_BLOCK_X = 730;
    private static final double MIN_BLOCK_Y = 200;
    private static final double BLOCK_WIDTH = 50;
    private static final double BLOCK_HEIGHT = 20;
    private static final int NUM_OF_ROWS = 5;
    /**
     * Returns the number of balls.
     *
     * @return the number of balls
     */
    @Override
    public int numberOfBalls() {
        return 2;
    }

    /**
     * The initial velocities of each ball.
     *
     * @return the list of velocities
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(new Velocity(-5, -5));
        velocities.add(new Velocity(5, -5));
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
        return "Green 3";
    }

    /**
     * Gets background.
     *
     * @return a sprite with the background of the level
     */
    @Override
    public Sprite getBackground() {
        return new Block(new Rectangle(new Point(0, 0),
                800, 600), Color.GREEN);
    }

    /**
     * The Blocks in this level, each block contains size, color and position.
     *
     * @return the list of the blocks
     */
    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        Color blocksColor;
        // loops for each row to be created.
        for (int i = 0; i < NUM_OF_ROWS; i++) {
            // runs for the number of blocks in each row and adds them.
            for (int j = 0; j <= NUM_OF_ROWS + i; j++) {
                Point curUpperLeft = new Point(MIN_BLOCK_X - BLOCK_WIDTH * j,
                        MIN_BLOCK_Y - BLOCK_HEIGHT * i);
                Rectangle curRect = new Rectangle(curUpperLeft, BLOCK_WIDTH,
                        BLOCK_HEIGHT);
                // checks what row it is now and sets color accordingly.
                if (i == 0) {
                    blocksColor = Color.WHITE;
                } else if (i == 1) {
                    blocksColor = Color.BLUE;
                } else if (i == 2) {
                    blocksColor = Color.YELLOW;
                } else if (i == 3) {
                    blocksColor = Color.RED;
                } else {
                    blocksColor = Color.GRAY;
                }
                blocks.add(new Block(curRect, blocksColor));
            }
        }
        return blocks;
    }

    /**
     * Number of blocks to remove.
     *
     * @return the number of blocks to be removed.
     */
    @Override
    public int numberOfBlocksToRemove() {
        return 40;
    }
}
