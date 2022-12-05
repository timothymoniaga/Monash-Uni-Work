package game.items;

import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Buyer;

/**
 * Interface implemented by items that are purchasable
 * @author Sara Hopkins
 * @version Assignment 3
 */
public interface Purchasable {

    /**
     * general purchase method to allow purchaseAction to call general execute
     * @param buyer Buyer purchasing the item
     * @param map GameMap the buyer is on
     * @return string menuDescription
     */
    String purchase(Buyer buyer, GameMap map);

    /**
     * price getter (all purchasable items will have price)
     * @return int price amount
     */
    int getPrice();

}