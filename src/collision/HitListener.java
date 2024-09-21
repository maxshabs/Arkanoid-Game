package collision;

import sprites.Ball;
import sprites.Block;

/**
 * The interface Hit listener.
 * @author Max Shabs
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit the object that is being hit.
     * @param hitter   is the ball that made the hit.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
