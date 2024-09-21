package game;

import animation.Animation;
import animation.AnimationRunner;
import animation.KeyPressStoppableAnimation;
import animation.LoseScreen;
import animation.WinScreen;
import biuoop.KeyboardSensor;
import java.util.List;

/**
 * The type Game flow, defined by animation runner, keyboard sensor and score.
 * @author Max Shabs
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private Counter score;

    /**
     * Instantiates a new Game flow.
     *
     * @param ar the animation runner
     * @param ks the keyboard sensor
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.score = new Counter();
    }

    /**
     * Runs the levels.
     *
     * @param levels the levels
     */
    public void runLevels(List<LevelInformation> levels) {
        boolean didWin = true;
        Animation keyPressStoppable;
        // goes over all the levels
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor,
                                            this.animationRunner, this.score);
            level.initialize();
            // runs the level until there are no blocks or balls.
            while (level.getNumOfBalls().getValue() > 0
                    && level.getNumOfBlocks().getValue() > 0) {
                level.run();
            }
            // checks if the user lost because he has no balls left.
            if (level.getNumOfBalls().getValue() == 0) {
                didWin = false;
                break;
            }
        }
        // checks if the user won.
        if (didWin) {
            Animation winScreen = new WinScreen(this.score);
            keyPressStoppable =
                    new KeyPressStoppableAnimation(this.keyboardSensor,
                            KeyboardSensor.SPACE_KEY, winScreen);
            this.animationRunner.run(keyPressStoppable);
            this.animationRunner.getGui().close();
        } else {
            Animation loseScreen = new LoseScreen(this.score);
            keyPressStoppable =
                    new KeyPressStoppableAnimation(this.keyboardSensor,
                            KeyboardSensor.SPACE_KEY, loseScreen);
            this.animationRunner.run(keyPressStoppable);
            this.animationRunner.getGui().close();
        }
    }
}
