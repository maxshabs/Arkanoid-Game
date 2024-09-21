package animation;

import biuoop.DrawSurface;

/**
 * The interface Animation.
 * @author Max Shabs
 */
public interface Animation {
    /**
     * Plays one frame.
     *
     * @param d the draw surface
     */
    void doOneFrame(DrawSurface d);

    /**
     * Checks if the animation should stop.
     *
     * @return the boolean that represents whether the animation should stop.
     */
    boolean shouldStop();
}
