package game.items.magical_items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Status;
import game.items.ConsumableItem;

/**
 * FireFlower Class
 * @author Sara Hopkins
 * @version Assignment 3
 */
public class FireFlower extends ConsumableItem {
    /**
     * turns counter for fire flower ability
     */
    private int turns;

    /**
     * Fire flower constructor
     */
    public FireFlower() {
        super("FireFlower", 'f', false);
        this.turns = 0;
    }

    /**
     * consumable item consume action execute method
     * @param actor Actor that is consuming the item
     * @param map GameMap that the actor is on
     */
    @Override
    public void toExecute(Actor actor, GameMap map){
        actor.addCapability(Status.FIRE_ATTACK);
        this.removeAction(this.getConsumeAction());
        actor.addItemToInventory(this);
    }

    /**
     * ITEM ON GROUND TICK METHOD
     * @param location location of item on ground
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location location, Actor actor) {
        this.turns++;
        if (this.turns == 20) {
            actor.removeItemFromInventory(this);
            actor.removeCapability(Status.FIRE_ATTACK);
        }
    }

    /**
     * getter for turns
     * @return int amount of turns
     */
    public int getTurns(){
        return this.turns;
    }

}
