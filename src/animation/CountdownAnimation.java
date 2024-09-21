package animation;

import biuoop.DrawSurface;
import sprites.SpriteCollection;

import java.awt.Color;

/**
 * The type Countdown animation.
 * @author Max Shabs
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean running;
    private int frameNum;
    private int currentNum;
    private double frameDuration;

    /**
     * Instantiates a new Countdown animation.
     *
     * @param numOfSeconds the num of seconds
     * @param countFrom    the count from
     * @param gameScreen   the game screen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom,
                              SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.currentNum = this.countFrom;
        this.gameScreen = gameScreen;
        this.running = true;
        this.frameNum = 0;
        this.frameDuration = (this.numOfSeconds / this.countFrom) * 60;
    }
    /**
     * Plays one frame.
     *
     * @param d the draw surface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.CYAN);
        d.drawText(d.getWidth() / 2 - 10, d.getHeight() / 2 - 10,
                    String.valueOf(currentNum), 40);
        this.frameNum++;
        if (this.frameNum >= this.frameDuration) {
            currentNum--;
            this.frameNum = 0;
        }
        if (currentNum == 0) {
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
