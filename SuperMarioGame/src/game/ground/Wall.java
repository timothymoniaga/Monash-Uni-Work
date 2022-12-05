package game.ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Utils;
import game.actions.JumpAction;
import game.actors.Status;

/**
 * Class for Walls
 * @author Peter Roberts
 * @version Assignment 3
 */
public class Wall extends Ground implements JumpOnAble{

	/**
	 * Probability of successful jump
	 */
	private static final double JUMP_ODDS = 0.8;
	/**
	 * Fall damage incurred upon failed jump
	 */
	private static final int FALL_DAMAGE = 20;

	/**
	 * Constructor.
	 */
	public Wall() {
		super('#');
		this.addJumpInstance(); // Walls can be jumped on
	}

	/**
	 *
	 * @param actor the Actor to check
	 * @return false, unless actor has powerstar
	 * @see JumpOnAble replaceCanActorEnter(actor)
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return replaceCanActorEnter(actor);
	}
	
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}

	/**
	 * Method to call when Wall is jumped at
	 * @return 0 if jump is successful, fall damage otherwise
	 */
	public int didJumpSucceed() {
		if(Utils.probReturn(JUMP_ODDS)){
			return 0;
		}
		return FALL_DAMAGE;
	}

	/**
	 * Gives actor standing next to wall actions that can be chosen
	 * @param actor the Actor acting
	 * @param location the current Location
	 * @param direction the direction of the Ground from the Actor
	 * @return actions list actor can complete
	 */
	@Override
	public ActionList allowableActions(Actor actor, Location location, String direction){
		ActionList actions =  new ActionList();
		if(!location.containsAnActor() && !replaceCanActorEnter(actor)){
			actions.add(new JumpAction(location, direction));
		}
		return actions;
	}

	/**
	 * Tick Method for wall
	 * @param location The location of the Ground
	 */
	@Override
	public void tick(Location location) {
		this.destroyedByPowerStar(location);
	}
}
