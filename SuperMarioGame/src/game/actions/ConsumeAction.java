package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.ConsumableItem;

/**
 * Action for consuming items
 * @author Sara Hopkins
 * @version Assignment 3
 */
public class ConsumeAction extends Action {

    /**
     * Instance of consumable item interface so that execute can access specific class methods.
     */
    ConsumableItem consumableItem;

    /**
     * ConsumeAction constructor
     * @param item consumable item to instantiate the consumable item
     */
    public ConsumeAction(ConsumableItem item) {
        this.consumableItem = item;
    }

    /**
     * overriding action class execute method. updates and calls methods according to which item type is fed through
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return menuDescription string "actor consumes ..."
     */
    @Override
    public String execute(Actor actor, GameMap map ) {
        consumableItem.toExecute(actor, map);
        return menuDescription(actor);
    }

    /**
     * creates a string about the actors action
     * @param actor The actor performing the action.
     * @return a string that describes the consumption that can be printed to the user
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes " + consumableItem.toString();
    }

}

