package game;

import collision.HitListener;
import sprites.Ball;
import sprites.Block;

/**
 * The type Score tracking listener, defined by the score of the game.
 * @author Max Shabs
 */
public class ScoreTrackingListener implements HitListener {
    private static final int POINTS_PER_BLOCK = 5;
    private Counter currentScore;

    /**
     * Instantiates a new Score tracking listener.
     *
     * @param scoreCounter the score counter of the game.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    /**
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit the object that is being hit
     * @param hitter   is the ball that made the hit.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        this.currentScore.increase(POINTS_PER_BLOCK);
    }
}
