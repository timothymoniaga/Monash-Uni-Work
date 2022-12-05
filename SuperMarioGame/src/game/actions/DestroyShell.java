package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Utils;

/**
 * Action for destroying Koopa shells
 * @author Peter Roberts
 * @version Assignment 2
 */
public class DestroyShell extends Action {
    /**
     * Actor that is destroyed
     */
    protected Actor target;
    /**
     * Direction of incoming destroy
     */
    protected String direction;

    /**
     * Constructor.
     * @param target Actor that is to be destroyed
     * @param direction Direction of incoming destroy
     */
    public DestroyShell(Actor target, String direction) {
        this.target = target;
        this.direction = direction;
    }

    /**
     * Execute Method
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return The result of the action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if(Utils.probReturn(actor.getWeapon().chanceToHit())){
            ActionList dropActions = new ActionList();
            // drop all items
            for (Item item : target.getInventory())
                dropActions.add(item.getDropAction(actor));
            for (Action drop : dropActions)
                drop.execute(target, map);
            // remove actor
            map.removeActor(target);
            return "Shell is destroyed";
        }
        else{
            return actor + "misses";
        }
    }

    /**
     * Menu Description Method
     * @param actor The actor performing the action.
     * @return Menu Description of the action.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks shell at " + direction + " with wrench";
    }

}
