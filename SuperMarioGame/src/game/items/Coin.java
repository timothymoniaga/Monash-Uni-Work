package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Buyer;
import game.actors.BuyerManager;
import game.actors.Status;
import game.reset.Resettable;
import game.reset.ResetManager;

/**
 * Class for the coin
 * @author Sara Hopkins
 * @version Assignment 3
 */
public class Coin extends ConsumableItem implements Resettable {
    private final int value;

    /**
     * coin item constructor
     * adds coin to resettable manager array
     * @param value    the coin instance value (integer)
     * @param portable a boolean variable that describes if the coin is portable.
     */
    public Coin(int value, boolean portable) {
        super("Coin $" + value, '$', portable);
        this.value = value;
        this.registerResetInstance();
    }

    /**
     * a getter for private value attribute of coin
     * @return integer coin value
     */
    public int getValue() {
        return this.value;
    }

    /**
     * remove Action from coin allowableActionList
     * @param action Action type action
     */
    public void removeActionCoin(Action action){
        ResetManager.getInstance().cleanUp(this);
        this.removeAction(action);
    }

    /**
     * execute method for consumableItem abstract
     * @param actor actor consuming the coin
     * @param map the map on which the actor is on
     */
    @Override
    public void toExecute(Actor actor, GameMap map){
        int check = BuyerManager.getInstance().buyers().indexOf(actor);
        if (check!= -1){
            Buyer buyer = BuyerManager.getInstance().buyers().get(check);
            buyer.editBalance(this.value);
        }
        actor.removeItemFromInventory(this);
        this.removeActionCoin(this.getConsumeAction());
        map.locationOf(actor).removeItem(this);
    }

    /**
     * if an item has the reset status then it needs to be removed
     * @param location location of the item
     */
    @Override
    public void tick(Location location){
        if(this.hasCapability(Status.RESET)){
            location.removeItem(this);
        }
    }

    /**
     * Status of RESET is added so that the tick funtion runs the reset function
     */
    @Override
    public void resetInstance() {
        this.addCapability(Status.RESET);
    }

}
