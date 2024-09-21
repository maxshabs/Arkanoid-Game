package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The type Key press stoppable animation, defined by sensor, key and animation.
 * @author Max Shabs
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * Instantiates a new Key press stoppable animation.
     *
     * @param sensor    the keyboard sensor
     * @param key       the key
     * @param animation the animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key,
                                      Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }
    /**
     * Plays one frame.
     *
     * @param d the draw surface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        // checks if the key was already pressed.
        if (isAlreadyPressed) {
            // checks if the key is not pressed.
            if (!sensor.isPressed(key)) {
                this.isAlreadyPressed = false;
            }
        } else {
            // checks if the key is pressed.
            if (sensor.isPressed(key)) {
                this.stop = true;
            }
        }
        animation.doOneFrame(d);
    }
    /**
     * Checks if the animation should stop.
     *
     * @return the boolean that represents whether the animation should stop.
     */
    @Override
    public boolean shouldStop() {
        return stop;
    }
}
