package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.ground.fountains.Fountain;
import game.items.magic_water.Bottle;
import game.items.magic_water.Water;
/**
 * Action for Bottle item
 * @author Sara Hopkins
 * @version Assignment 3
 */
public class FillAction extends Action {
    /**
     * instance of bottle to be filled
     */
    private final Bottle bottle;

    /**
     * Water to fill the bottle with
     */
    private final Water water;

    /**
     * fountain so that accurate fountain capacity can be tracked
     */
    private final Fountain fountain;

    /**
     * constructor for fillAction
     * @param bottle bottle instance of player
     * @param water either power water or health water
     * @param fountain either power fountain or health fountain
     */
    public FillAction(Bottle bottle, Water water, Fountain fountain){
        this.bottle = bottle;
        this.water = water;
        this.fountain = fountain;
    }

    /**
     * the action execute method, run when player selects action hotkey from menu
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String of what fill action occurred
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        this.bottle.addToBottle(this.water);
        this.fountain.fillBottleFromFountain();
        return menuDescription(actor);
    }

    /**
     * concatenates a string description of menu actions
     * @param actor The actor performing the action.
     * @return the menu description string
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " refill " + this.water.toString() + '(' + this.fountain.getCount() + "\\10)";
    }

}
