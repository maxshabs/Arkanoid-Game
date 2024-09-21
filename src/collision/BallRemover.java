package collision;

import game.Counter;
import game.GameLevel;
import sprites.Ball;
import sprites.Block;

/**
 * The type Ball remover, defined by game and the number of remaining balls.
 * @author Max Shabs
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * Instantiates a new Ball remover.
     *
     * @param game           the game
     * @param remainingBalls the remaining balls
     */
    public BallRemover(GameLevel game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    /**
     * This method is called whenever there's a hit and removes the hitter.
     *
     * @param beingHit the object that is being hit.
     * @param hitter   is the ball that made the hit.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.remainingBalls.decrease(1);
    }
}
