package game.ground;

import edu.monash.fit2099.engine.positions.Location;


/**
 * Singleton class to manage the Teleporting.
 * @author Peter Roberts
 * @version Assignment 3
 */
public class PipesManager {

    /**
     * Single instance of this class
     */
    private static PipesManager instance;

    /**
     * Location for the warp pipe to warp to.
     */
    private static Location warpTo;

    /**
     * Get instance method
     * @return the instance
     */
    public static PipesManager getInstance(){
        if (instance == null) {
            instance = new PipesManager();
        }
        return instance;
    }

    /**
     * Getter for the warp to location
     * @return the location to which the actor will teleport
     */
    public Location getWarpTo(){
        return PipesManager.warpTo;
    }

    /**
     * Setter for the warp to location
     * @param location current location of the actor, allows them to warp back to where they came from.
     */
    public void setWarpTo(Location location){
        PipesManager.warpTo = location;
    }

}
