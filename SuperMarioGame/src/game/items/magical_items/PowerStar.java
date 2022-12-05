package game.items.magical_items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Buyer;
import game.actors.Status;
import game.items.ConsumableItem;
import game.items.Purchasable;

/**
 * PowerStar Class
 * @author Sara Hopkins
 * @version Assignment 3
 */
public class PowerStar extends ConsumableItem implements Purchasable {
    /**
     * Price of the PowerStar
     */
    public static final int PRICE = 600;
    /**
     * Age of the PowerStar in turns
     */
    private int turns;

    /**
     * constructor for PowerStar item, initialises turns (age counter) to zero
     *
     * @param portable a boolean variable to indicate whether the item can be moved/ carried
     */
    public PowerStar(boolean portable) {
        super("PowerStar", '*', portable);
        turns = 0;
    }

    /**
     * getter for private class price attribute
     *
     * @return the static price attribute, references class instance not created object instance.
     */
    public int getPrice() {
        return PowerStar.PRICE;
    }

    /**
     * getter for turns counter, private 'age; attribute for PowerStar item
     *
     * @return an integer indicating item age
     */
    public int getTurns() {
        return this.turns;
    }

    /**
     * this is responsible for increasing the player HP by 200
     *
     * @param actor the player who has used the powerStar
     */
    public void healPlayer(Actor actor) {
        actor.heal(200);
    }

    /**
     * overridden tick method from item class. added a counter, turns attribute, which will track the amount of
     * time (turns) that have passed since the initialisation of the particular instance
     *
     * @param location the location at which the item is (on the floor)
     */
    @Override
    public void tick(Location location) {
        this.turns++;
        if (this.turns == 10) {
            location.removeItem(this);
        }
    }

    /**
     * overriding second tick method in item abstract class. this one tracks the passing of time of a carried
     * item (one in the actor's inventory). once it
     *
     * @param location the location that the actor is at
     * @param actor    The actor carrying this Item.
     */
    @Override
    public void tick(Location location, Actor actor) {
        this.turns++;
        if (this.turns == 10) {
            actor.removeItemFromInventory(this);
            actor.removeCapability(Status.POWERSTAR);
        }
    }

    /**
     * remove a certain action from the powerStar allowable action list
     * @param action action to be removed from allowable actions
     */
    public void removeActionPowerStar(Action action){
        this.removeAction(action);
    }

    /**
     * consume abstract class toExecute method, gives the powerstar a consume action.
     * @param actor Actor that is consuming the item
     * @param map GameMap that the actor is on
     */
    @Override
    public void toExecute(Actor actor, GameMap map){
        this.turns = 0;
        this.healPlayer(actor);
        if(!actor.getInventory().contains(this)){
            actor.addItemToInventory(this);
        }
        actor.addCapability(Status.POWERSTAR);
        this.removeActionPowerStar(this.getConsumeAction());
        map.locationOf(actor).removeItem(this);
        this.togglePortability();
    }

    /**
     * purchase method specific to powerstar.
     * @param buyer Buyer purchasing the item
     * @param map GameMap the buyer is on
     * @return string with success of purchase and remaining balance
     */
    @Override
    public String purchase(Buyer buyer, GameMap map) {
        if (buyer.getWalletBalance()> PRICE){
            buyer.addItemToInventoryBuyer(this);
            buyer.editBalance(-PRICE);
            return "Successfully purchased PowerStar! Remaining Balance: " + buyer.getWalletBalance();
        }
        return "insufficient Balance :(";
    }

    /**
     * changing toString to include remaining turns of powerstar instance
     * @return string concatenated with remaining turns
     */
    @Override
    public String toString(){
        return this.getClass().getSimpleName() + " (" + (10 - this.getTurns()) + " turns remaining)";
    }

}
