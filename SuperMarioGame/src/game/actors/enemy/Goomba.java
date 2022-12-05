package game.actors.enemy;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Utils;
import game.actions.AttackAction;
import game.actions.SpeakAction;
import game.actors.Speakable;
import game.actors.Status;
import game.behaviour.AttackBehaviour;
import game.behaviour.Behaviour;
import game.behaviour.FollowBehaviour;
import game.behaviour.WanderBehaviour;
import java.util.Map;
import java.util.TreeMap;

import java.util.Random;

/**
 * Class for the Goomba Enemy
 * @author Peter Roberts
 * @author Sara Hopkins
 * @version Assignment 3
 */
public class Goomba extends Enemy implements Speakable {
	/**
	 * Behaviours treemap
	 */
	private final Map<Integer, Behaviour> behaviours = new TreeMap<>(); // priority,
	/**
	 * Probability of Suicide
	 */
	private static final double SUICIDE_ODDS = 0.1; // Should be 0.1 as per assignment 1

	//collection of strings that Goomba can speak
	private final String[] dialogue = {"Mugga mugga!",
			"Ugha ugha... (Never gonna run around and desert you...)",
			"Ooga-Chaka Ooga-Ooga!"
	};

	////bool used for counting every second turn for speak action
	private boolean count = false;

	/**
	 * Constructor.
	 */
	public Goomba() {
		super("Goomba", 'g', 50);
		this.behaviours.put(10, new WanderBehaviour());
		this.behaviours.put(1, new AttackBehaviour());
	}

	/**
	 * Allows other actors to attack the enemy
	 * @param otherActor the Actor that might perform an action.
	 * @param direction  String representing the direction of the other Actor
	 * @param map        current GameMap
	 * @return list of actions
	 * @see Status#HOSTILE_TO_ENEMY
	 */
	@Override
	public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
		ActionList actions = new ActionList();
		// it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
		if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
			actions.add(new AttackAction(this,direction));
		}
		return actions;
	}

	/**
	 * Figures out what Goomba should do next
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return Goomba's next action
	 */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		if(this.count){ //run all turn code in this if-else loop so that every 2nd turn Goomba speaks
			this.count = false; //update so next turn won't speak
			display.println(this.speak(this));
		}

		this.count = true; //update so next turn will speak
		// Removes itself if reset has been run
		if(this.hasCapability(Status.RESET)){
			map.removeActor(this);
			return new DoNothingAction();
		}
		// Follows the actor that it is engaged with
		Actor attacker = this.startFollowFromExit(map);
		if(attacker != null){
			try{
				this.behaviours.put(2, new FollowBehaviour(attacker));
			}
			catch(Exception ignored){}
		}
		for(Behaviour behaviour : behaviours.values()) {
			Action action = behaviour.getAction(this, map);
			if (action != null)
				return action;
		}
		this.maybeSuicide(map);
		return new DoNothingAction();

	}

	/**
	 * Method for getting Goomba's intrinsic weapon
	 * @return Goomba's intrinsic weapon
	 */
	@Override
	protected IntrinsicWeapon getIntrinsicWeapon(){
		// We can use intrinsic weapon here because it automatically has a 50% hit rate.
		return new IntrinsicWeapon(10, "kick"); //
	}

	/**
	 * Method for deciding whether the Goomba should suicide
	 * @param map current GameMap
	 */
	public void maybeSuicide(GameMap map){
		if(Utils.probReturn(SUICIDE_ODDS)){
			map.removeActor(this);
		}
	}

	/**
	 * method to pick random prompt for Goomba to speak each turn
	 * @param actor default actor, included in interface for speak with Toad
	 * @return the string spoken
	 */
	@Override
	public String speak(Actor actor) {
		Random r = new Random();
		return this.name + ": " +dialogue[r.nextInt(3)];
	}

}
