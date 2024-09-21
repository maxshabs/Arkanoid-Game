// 212108641 Max Shabs

import animation.AnimationRunner;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.GameFlow;
import game.LevelInformation;
import levels.DirectHit;
import levels.GreenThree;
import levels.WideEasy;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Ass 6 game.
 * @author Max Shabs
 */
public class ArkanoidGame {
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", SCREEN_WIDTH, SCREEN_HEIGHT);
        AnimationRunner animationRunner = new AnimationRunner(gui, 60);
        KeyboardSensor keyboardSensor = gui.getKeyboardSensor();
        GameFlow gameFlow = new GameFlow(animationRunner, keyboardSensor);
        List<LevelInformation> levels = new ArrayList<>();
        // checks if there isn't any input.
        if (args.length == 0 || args[0].equals("${args}")) {
            levels.add(new DirectHit());
            levels.add(new WideEasy());
            levels.add(new GreenThree());
        } else {
            // goes over the input strings.
            for (String arg : args) {
                try {
                    int choice = Integer.parseInt(arg);
                    // adds levels accordingly.
                    switch (choice) {
                        case 1:
                            levels.add(new DirectHit());
                            break;
                        case 2:
                            levels.add(new WideEasy());
                            break;
                        case 3:
                            levels.add(new GreenThree());
                            break;
                        default:
                            break;
                    }
                } catch (Exception notNumber) {
                    continue;
                }
            }
        }
        // if there are no levels, it runs the 3 default levels.
        if (levels.isEmpty()) {
            levels.add(new DirectHit());
            levels.add(new WideEasy());
            levels.add(new GreenThree());
        }
        gameFlow.runLevels(levels);
    }
}
