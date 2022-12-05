package game.actors;

import edu.monash.fit2099.engine.items.Item;
import game.items.magic_water.Bottle;

/**
 * Interface implemented by actors that can buy
 * @author Sara Hopkins
 * @version Assignment 3
 */
public interface Buyer {
    /**
     * getter for buyer wallet balance
     * @return int amount of wallet balance
     */
    int getWalletBalance();

    /**
     * change balance by pos/neg amount
     * @param amount int amount to edit balance by
     */
    void editBalance(int amount);

    /**
     * adding the item to the buyers inventory
     * @param item Item type
     */
    void addItemToInventoryBuyer(Item item);

    /**
     * allows purchase method to add wrench status capability, overrides for all but ok.
     * @param capability player status
     */
    void addCapability(Enum<?> capability);

    /**
     * get instance of bottle from player
     */
    Bottle getBottle();

    /**
     * assign new bottle instance to player
     * @param bottle instance of bottle
     */
    void setBottle(Bottle bottle);

    /**
     * checks whether the player has a bottle or not
     * @return boolean
     */
    boolean hasBottle();

    /**
     * adds the buyer to the array list of buyers in buyerManager class
     */
    default void addInstance(){
        BuyerManager.getInstance().appendBuyer(this);
    }

}
