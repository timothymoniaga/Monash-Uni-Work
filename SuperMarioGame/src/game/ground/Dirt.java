package game.ground;

import edu.monash.fit2099.engine.positions.Ground;
import game.actors.Status;

/**
 * A class that represents bare dirt.
 */
public class Dirt extends Ground {
	/**
	 * Constructor
	 */
	public Dirt() {
		super('.');
		super.addCapability(Status.FERTILE);
	}

}
