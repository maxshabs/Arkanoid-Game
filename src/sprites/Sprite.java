package sprites;

import biuoop.DrawSurface;

/**
 * The interface sprite.
 * @author Max Shabs
 */
public interface Sprite {
    /**
     * Draws a sprite on the surface.
     *
     * @param d surface.
     */
    void drawOn(DrawSurface d);

    /**
     * Notify the sprite that time has passed.
     */
    void timePassed();
}
