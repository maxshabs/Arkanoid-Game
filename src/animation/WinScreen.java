package animation;

import biuoop.DrawSurface;
import game.Counter;

/**
 * The type Win screen, defined by score and stop that says if it should run.
 * @author Max Shabs
 */
public class WinScreen implements Animation {
    private Counter currentScore;
    private boolean stop;

    /**
     * Instantiates a new Win screen.
     *
     * @param currentScore the current score
     */
    public WinScreen(Counter currentScore) {
        this.currentScore = currentScore;
        this.stop = false;
    }
    /**
     * Plays one frame.
     *
     * @param d the draw surface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2,
                "You Win! Your score is " + this.currentScore.getValue(),
                32);
    }
    /**
     * Checks if the animation should stop.
     *
     * @return the boolean that represents whether the animation should stop.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
