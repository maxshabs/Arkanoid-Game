package sprites;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a collection of sprites, defined by a list of sprites.
 * @author Max Shabs
 */
public class SpriteCollection {
    private List<Sprite> spritesList;

    /**
     * Instantiates a new collection of sprites.
     */
    public SpriteCollection() {
        spritesList = new ArrayList<>();
    }

    /**
     * Gets sprites list.
     *
     * @return the sprites list.
     */
    public List<Sprite> getSpritesList() {
        return this.spritesList;
    }

    /**
     * Adds sprite to the sprite collection.
     *
     * @param s the sprite we want to add.
     */
    public void addSprite(Sprite s) {
        spritesList.add(s);
    }

    /**
     * Calls timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spritesListCopy = new ArrayList<>(this.spritesList);
        for (Sprite sprite: spritesListCopy) {
            sprite.timePassed();
        }
    }

    /**
     * Draw all sprites on the surface.
     *
     * @param d the surface.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite: spritesList) {
            sprite.drawOn(d);
        }
    }
}
