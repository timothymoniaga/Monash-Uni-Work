package game.reset;
import java.util.ArrayList;
import java.util.List;

/**
 * A global Singleton manager that does soft-reset on the instances.
 */
public class ResetManager {
    /**
     * A list of resettable instances (any classes that implements Resettable,
     * such as Player implements Resettable will be stored in here)
     */
    private final List<Resettable> resettableList;

    /**
     * A singleton reset manager instance
     */
    private static ResetManager instance;

    /**
     * Get the singleton instance of reset manager
     * @return ResetManager singleton instance
     */
    public static ResetManager getInstance(){
        if(instance == null){
            instance = new ResetManager();
        }
        return instance;
    }

    /**
     * Constructor
     */
    private ResetManager(){
        resettableList = new ArrayList<>();
    }

    /**
     * Reset the game by traversing through all the list
     * By doing this way, it will avoid using `instanceof` all over the place.
     */
    public void run(){
        for (Resettable resettable : resettableList) {
            try {
                resettable.resetInstance();
            } catch (Exception e) {
                System.out.println("Error resetting list");
            }
        }
    }

    /**
     * Add a resettable instance to the list
     * @param reset Resettable instance to be added
     */
    public void appendResetInstance(Resettable reset){
        this.resettableList.add(reset);
    }

    /**
     * Remove a Resettable instance from the list
     * @param resettable resettable object
     * not used because the iterator only needs to run once per game
     * and list does not need to be cleaned up
     */
    public void cleanUp(Resettable resettable){
        resettableList.remove(resettable);
    }

}
