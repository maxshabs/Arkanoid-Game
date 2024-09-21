package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The type Pause screen, defined by stop that says if it should run.
 * @author Max Shabs
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboardSensor;
    private boolean stop;

    /**
     * Instantiates a new Pause screen.
     *
     * @param k the keyboard sensor
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboardSensor = k;
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
                "paused -- press space to continue", 32);
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
