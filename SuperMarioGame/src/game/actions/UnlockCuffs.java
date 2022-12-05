package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Action for unlocking Princess Peach's cuffs and ending the game.
 * @author Peter Roberts
 * @version Assignment 3
 */
public class UnlockCuffs extends Action {

    /**
     * Execute method
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String description to end the game
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor); // This causes the engine to end the game, because it cannot find player on the map
        return "Congratulations! You have saved Princess Peach from Bowser. They can now live happily ever after!";
    }

    /**
     *
     * @param actor The actor performing the action.
     * @return String description of the action for the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Unlock Princess Peach's handcuffs";
    }
}
