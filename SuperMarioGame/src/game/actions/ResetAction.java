package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.reset.ResetManager;

/**
 * Action for Reset
 * @author Peter Roberts
 * @author Tim Moniaga
 * @version Assignment 2
 */
public class ResetAction extends Action{
    /**
     * Reset manager to process the reset
     */
    ResetManager reset;

    /**
     * Constructor.
     * Gets instance of the ResetManager
     */
    public ResetAction() {
        this.reset = ResetManager.getInstance();
    }

    /**
     * Runs the reset through the reset manager
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String description of action result
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        reset.run();
        return "Reset complete!";
    }

    /**
     * Menu Description
     * @param actor The actor performing the action.
     * @return Menu Description
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Reset the map (you can only do this once!)";
    }

    /**
     * Forces the hotkey() to be "r" for reset.
     * @return hotkey
     */
    @Override
    public String hotkey(){return "r";}

}
