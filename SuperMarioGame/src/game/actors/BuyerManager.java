package game.actors;

import java.util.ArrayList;

/**
 * Singleton class to manage actors that implement the buyer interface
 * @author Sara Hopkins
 * @version Assignment 3
 */
public class BuyerManager {
    /**
     * List of actors that implement the buyer interface
     */
    private final ArrayList<Buyer> buyers;

    /**
     * Single instance of this class
     */
    private static BuyerManager instance;

    /**
     * Private Constructor
     */
    private BuyerManager() {
        buyers = new ArrayList<>();
    }

    /**
     * Get instance method
     * @return the instance
     */
    public static BuyerManager getInstance() {
        if (instance == null) {
            instance = new BuyerManager();
        }
        return instance;
    }

    /**
     * Method for adding an actor instance to the buyers list
     * @param buyer buyer actor to be added to the list
     */
    public void appendBuyer(Buyer buyer) {
        this.buyers.add(buyer);
    }

    /**
     * Getter for the buyers list
     * @return the list
     */
    public ArrayList<Buyer> buyers() {
        return new ArrayList<>(this.buyers);
    }

}
