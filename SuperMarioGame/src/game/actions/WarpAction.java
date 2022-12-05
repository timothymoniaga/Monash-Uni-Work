package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.ground.PipesManager;

/**
 * Action for teleporting back and forth between maps.
 * @author Peter Roberts
 * @version Assignment 3
 */
public class WarpAction extends Action {

    /**
     * Executes the move
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String description of the action that has just occurred.
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        Location oldLocation = map.locationOf(actor);

        // Try to kill the PiranhaPlant at the pipe if it is there.
        if(PipesManager.getInstance().getWarpTo().containsAnActor()){
            map.removeActor(PipesManager.getInstance().getWarpTo().getActor());
        }

        map.moveActor(actor,PipesManager.getInstance().getWarpTo());
        PipesManager.getInstance().setWarpTo(oldLocation);
        return actor.toString() + "teleported successfully";

    }

    /**
     * Description of the action for the menu
     * @param actor The actor performing the action.
     * @return String description of the action for the menu.
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Teleport through WarpPipe";
    }
}
