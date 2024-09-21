package game;

/**
 * The type Counter, defined by a counter.
 * @author Max Shabs
 */
public class Counter {
    private int counter;

    /**
     * Instantiates a new Counter.
     */
    public Counter() {
        this.counter = 0;
    }

    /**
     * Add a given number to the counter.
     *
     * @param number the number
     */
    public void increase(int number) {
        this.counter += number;
    }

    /**
     * Subtracts a given number from the counter.
     *
     * @param number the number
     */
    public void decrease(int number) {
        this.counter -= number;
    }

    /**
     * Gets the value of the counter.
     *
     * @return the value
     */
    public int getValue() {
        return this.counter;
    }
}
