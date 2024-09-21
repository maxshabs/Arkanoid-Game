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
 * The level Wide easy.
 * @author Max Shabs
 */
public class WideEasy implements LevelInformation {
    /**
     * Returns the number of balls.
     *
     * @return the number of balls
     */
    @Override
    public int numberOfBalls() {
        return 10;
    }

    /**
     * The initial velocities of each ball.
     *
     * @return the list of velocities
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        // goes over the balls.
        for (int i = 0; i < numberOfBalls(); i++) {
            if (i < numberOfBalls() / 2) {
                velocities.add(Velocity.fromAngleAndSpeed(-70 + i * 20, 6));
            } else {
                velocities.add(Velocity.fromAngleAndSpeed(-110 + i * 20, 6));
            }
        }
        return velocities;
    }

    /**
     * The paddle speed.
     *
     * @return the speed of the paddle
     */
    @Override
    public int paddleSpeed() {
        return 2;
    }

    /**
     * The paddle width.
     *
     * @return the width of the paddle.
     */
    @Override
    public int paddleWidth() {
        return 650;
    }

    /**
     * The Level name.
     *
     * @return the level's name.
     */
    @Override
    public String levelName() {
        return "Wide Easy";
    }

    /**
     * Gets background.
     *
     * @return a sprite with the background of the level
     */
    @Override
    public Sprite getBackground() {
        return new Block(new Rectangle(new Point(0, 0),
                800, 600), Color.WHITE);
    }

    /**
     * The Blocks in this level, each block contains size, color and position.
     *
     * @return the list of the blocks
     */
    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        for (int i = 0; i < numberOfBlocksToRemove(); i++) {
            Rectangle thisRect = new Rectangle(new Point(20 + i * 50, 200),
                                            50, 20);
            if (i < 2) {
                blocks.add(new Block(thisRect, Color.RED));
            } else if (i < 4) {
                blocks.add(new Block(thisRect, Color.ORANGE));
            } else if (i < 6) {
                blocks.add(new Block(thisRect, Color.YELLOW));
            } else if (i < 9) {
                blocks.add(new Block(thisRect, Color.GREEN));
            } else if (i < 11) {
                blocks.add(new Block(thisRect, Color.BLUE));
            } else if (i < 13) {
                blocks.add(new Block(thisRect, Color.PINK));
            } else {
                blocks.add(new Block(thisRect, Color.CYAN));
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
        return 15;
    }
}
