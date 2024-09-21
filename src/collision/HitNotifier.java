package collision;

/**
 * The interface Hit notifier.
 * @author Max Shabs
 */
public interface HitNotifier {
    /**
     * Adds hit listener.
     *
     * @param hl the hit listener.
     */
    void addHitListener(HitListener hl);

    /**
     * Removes hit listener.
     *
     * @param hl the hit listener.
     */
    void removeHitListener(HitListener hl);
}