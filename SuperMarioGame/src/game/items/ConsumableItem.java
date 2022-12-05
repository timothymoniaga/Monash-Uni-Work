package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.ConsumeAction;

/**
 * Abstract Class for items that are consumable
 * @author Sara Hopkins
 * @version Assignment 3
 */
public abstract class ConsumableItem extends Item {
    /**
     * instance of consume Action for the chosen consumable item
     */
    ConsumeAction consumeAction;

    /**
     * consumable item constructor adds consume action to item
     * @param name consumable item type name
     * @param displayChar display char
     * @param portable boolean
     */
    public ConsumableItem(String name, char displayChar, boolean portable){
        super(name, displayChar, portable);
        consumeAction = new ConsumeAction(this);
        this.addAction(consumeAction);
    }

    /**
     * general toExecute method to allow any consumable item to have a consume action
     * @param actor Actor that is consuming the item8246848226r826377
     * @param map GameMap that the actor is on
     */
    public void toExecute(Actor actor, GameMap map){}

    /**
     * getter for ConsumeAction222
     * @return consumeAction
     */
    public ConsumeAction getConsumeAction(){
        return consumeAction;
    }

}
