package game.actions;

import java.util.Random;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.actors.Status;
import game.items.magical_items.Fire;

/**
 * Special Action for attacking other Actors.
 */
public class AttackAction extends Action {
	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;

	/**
	 * The direction of incoming attack.
	 */
	protected String direction;

	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	/**
	 * Constructor.
	 * 
	 * @param target Actor being attacked
	 * @param direction Direction of incoming attack
	 */
	public AttackAction(Actor target, String direction) {
		this.target = target;
		this.direction = direction;
	}

	/**
	 * Execute Method
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return Result of the action, to print to console
	 */

	@Override
	public String execute(Actor actor, GameMap map) {

		Weapon weapon = actor.getWeapon();

		// Causes actor to follow target and target to follow actor
		actor.addCapability(Status.ENGAGED);
		target.addCapability(Status.ENGAGED);
		String result = "";
		// PowerStar causes target to die when it gets attacked
		if(actor.hasCapability(Status.POWERSTAR)){
			target.hurt(99999);
			result += actor + " attacks with PowerStar";
		}
		else if(target.hasCapability(Status.POWERSTAR)){
			return actor + " attack fails due to PowerStar!";
		}
		else{
			// Regular weapon attack
			if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
				return actor + " misses " + target + ".";
			}
			int damage = weapon.damage();

			// Throw fire if necessary
			if(actor.hasCapability(Status.FIRE_ATTACK)){
				map.locationOf(target).addItem(new Fire());
			}

			result += actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
			target.hurt(damage);
			target.removeCapability(Status.TALL);
		}
		// Runs if the target dies, unless the target should leave its corpse on the ground (like Koopa turning dormant)
		if (!target.isConscious() && !target.hasCapability(Status.VALID_CORPSE)) {
			ActionList dropActions = new ActionList();
			// drop all items
			for (Item item : target.getInventory())
				dropActions.add(item.getDropAction(actor));
			for (Action drop : dropActions)
				drop.execute(target, map);
			// remove actor
			map.removeActor(target);
			result += System.lineSeparator() + target + " is killed.";
		}

		return result;
	}

	/**
	 * Menu Description Method
	 * @param actor The actor performing the action.
	 * @return The menu description of the action
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target + " at " + direction;
	}

}
