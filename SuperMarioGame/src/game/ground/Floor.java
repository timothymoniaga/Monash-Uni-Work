package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.actors.Status;

/**
 * A class that represents the floor inside a building.
 */
public class Floor extends Ground {
	/**
	 * Constructor.
	 */
	public Floor() {
		super('_');
	}

	/**
	 * Method to prevent enemies from entering the floor
	 * @param actor the Actor to check
	 * @return true if actor is not an enemy, false otherwise.
	 */
	@Override
	public boolean canActorEnter(Actor actor){
		return !actor.hasCapability(Status.FLOOR_BANNED);
	}

}

