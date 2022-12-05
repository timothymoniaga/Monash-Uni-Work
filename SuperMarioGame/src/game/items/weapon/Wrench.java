package game.items.weapon;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actors.Buyer;
import game.actors.Status;
import game.items.Purchasable;

/**
 * Class for the Wrench
 * @author Sara Hopkins
 * @version Assignment 3
 */
public class Wrench extends WeaponItem implements Purchasable {

    /**
     * Price of Wrench
     */
    public static final int PRICE = 200;

    /**
     * constructor for wrench weaponItem
     * sets it to be portable
     */
    public Wrench() {
        super("Wrench", '%', 50, "Wrench Attack!", 80);
        this.togglePortability(); // Wrench cannot be dropped
    }

    /**
     * public getter for private class price int
     * @return private class instance attribute integer wrench purchase price
     */
    public int getPrice(){return Wrench.PRICE;}

    /**
     * wrench purchase method calls
     * @param buyer Buyer purchasing the item
     * @param map GameMap the buyer is on
     * @return player balance and/or purchase success statement
     */
    @Override
    public String purchase(Buyer buyer, GameMap map) {
        if (buyer.getWalletBalance()> PRICE){
            buyer.addItemToInventoryBuyer(this);
            buyer.editBalance(-PRICE);
            buyer.addCapability(Status.WRENCH);
            return "Successfully purchased Wrench! Remaining Balance: " + buyer.getWalletBalance();
        }
        return "insufficient Balance :(";
    }


}
