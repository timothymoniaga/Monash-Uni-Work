package game.behaviour;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;
import game.actors.Status;

/**
 * Behaviour implementing class to handle automatic attacks
 */
public class AttackBehaviour implements Behaviour {
    /**
     *
     * @param actor the Actor attacking
     * @param map the GameMap containing the Actor
     * @return AttackAction for the actor, null when there is no hostile actors nearby
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location here = map.locationOf(actor);
        for (Exit exit : here.getExits()){
            Location destination = exit.getDestination();
            // Check if there is an actor at exit.
            if(destination.containsAnActor()){
                Actor target = destination.getActor();
                // Check if the actor at the exit is hostile to enemy (i.e. a player)
                if(target.hasCapability(Status.HOSTILE_TO_ENEMY)){
                    return new AttackAction(target, exit.getName());
                }
            }
        }
        return null;
    }
}
