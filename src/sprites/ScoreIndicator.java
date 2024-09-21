package sprites;

import biuoop.DrawSurface;
import game.Counter;

import java.awt.Color;

/**
 * The type Score indicator defined by score.
 * @author Max Shabs
 */
public class ScoreIndicator implements Sprite {
    private static final int BACKGROUND_WIDTH = 800;
    private static final int BACKGROUND_HEIGHT = 18;
    private static final int BACKGROUND_TOP_LEFT_X = 0;
    private static final int BACKGROUND_TOP_LEFT_Y = 0;
    private static final int SCORE_TOP_LEFT_X = 370;
    private static final int SCORE_TOP_LEFT_Y = 14;
    private static final int TEXT_SIZE = 13;
    private Counter score;

    /**
     * Instantiates a new Score indicator.
     *
     * @param score the score of the game.
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }
    /**
     * Draws a sprite on the surface.
     *
     * @param d surface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle(BACKGROUND_TOP_LEFT_X, BACKGROUND_TOP_LEFT_Y,
                        BACKGROUND_WIDTH, BACKGROUND_HEIGHT);
        d.setColor(Color.BLACK);
        d.drawText(SCORE_TOP_LEFT_X, SCORE_TOP_LEFT_Y,
                "Score:" + this.score.getValue(), TEXT_SIZE);
    }
    /**
     * Notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
        return;
    }
}
