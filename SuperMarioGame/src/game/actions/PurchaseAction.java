package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Buyer;
import game.items.Purchasable;

/**
 * Action for purchasing items
 * @author Sara Hopkins
 * @version Assignment 3
 */
public class PurchaseAction extends Action {
    /**
     * instance of purchasable item type
     */
    private final Purchasable purchasable;

    /**
     * instance of buyer purchasing the item
     */
    private final Buyer buyer;

    /**
     * PurchaseItem Constructor, assigns the purchasable item type and the buyer
     * @param item general purchasable item type
     * @param buyer buyer interface type (atm just player)
     */
   public PurchaseAction(Purchasable item, Buyer buyer) {
        this.purchasable = item;
        this.buyer = buyer;
    }

    /**
     * overriding the execute method from the Action class as ut us n=necessary for any extension of an abstract class
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a statement either indicating success or failure of purchase.
     * @see Action class - for execute method
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        return purchasable.purchase(this.buyer,map);
    }

    /**
     * overriding method menu description which is responsible for creating a string about the actors action
     * @param actor The actor performing the action.
     * @return a string about the action preformed.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " buys " + purchasable + " ($" + purchasable.getPrice() + ")";
    }

}

