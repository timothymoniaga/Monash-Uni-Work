package game.reset;

/**
 * Resettable interface
 * @author Tim Moniaga
 * @version Assignment 2
 */
public interface Resettable {
    /**
     * Allows any classes that use this interface to reset abilities, attributes, and/or items.
     */
    void resetInstance();

    /**
     * a default interface method that register current instance to the Singleton manager.
     * It allows corresponding class uses to be affected by global reset
     */
    default void registerResetInstance(){
        ResetManager.getInstance().appendResetInstance(this);
    }

}
