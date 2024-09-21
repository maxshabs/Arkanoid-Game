package sprites;

import biuoop.DrawSurface;
import game.LevelInformation;

/**
 * The type Level name, defined by level info.
 */
public class LevelName implements Sprite {
    private LevelInformation levelInfo;

    /**
     * Instantiates a new Level name.
     *
     * @param levelInfo the level info
     */
    public LevelName(LevelInformation levelInfo) {
        this.levelInfo = levelInfo;
    }

    /**
     * Draws a sprite on the surface.
     *
     * @param d surface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.drawText(600, 14, "Level Name: " + levelInfo.levelName(), 13);
    }

    /**
     * Notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
        return;
    }
}
