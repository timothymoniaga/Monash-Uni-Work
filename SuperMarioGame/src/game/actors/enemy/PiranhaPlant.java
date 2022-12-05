package game.actors.enemy;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.AttackAction;
import game.actions.SpeakAction;
import game.actors.Speakable;
import game.actors.Status;
import game.behaviour.AttackBehaviour;
import game.behaviour.Behaviour;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * Class for the Piranha Plant
 * @author Peter Roberts
 * @version Assignment 3
 */
public class PiranhaPlant extends Enemy implements Speakable{

    /**
     * The amount PiranhaPlant's HP is increased by on reset.
     */
    private static final int RESET_HP_INCREASE = 50;
    /**
     * Tree Map for storing behaviours.
     */
    private final Map<Integer, Behaviour> behaviours = new TreeMap<>(); // priority, behaviour

    /**
     * array of strings that Piranha Plant can speak
     */
    private final String[] dialogue = {"Slsstssthshs~! (Never gonna say goodbye~)",
            "Ohmnom nom nom nom."
    };

    /**
     * Age of Piranha, used to allow the plant to speak every second turn
     */
    private int age;

    /**
     * Constructor.
     *
     */
    public PiranhaPlant(){
        super("Piranha Plant",'Y',1); // Should be 150 as per assignment 3
        this.behaviours.put(1, new AttackBehaviour());
        this.registerResetInstance();
        this.age = 0;
    }

    /**
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return actions   list of actions the otherActor can do to the actor
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this,direction));
        }

        return actions;
    }

    /**
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return Action    The action that the actor will complete this turn
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        this.age++;

        if(this.hasCapability(Status.RESET)){
            this.increaseMaxHp(RESET_HP_INCREASE);
            this.heal(99999);
            this.removeCapability(Status.RESET);
            return new DoNothingAction();
        }

        if(this.age % 2 == 0){
            display.println(this.speak(this));
        }

        for(Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }

    /**
     * Getter for intrinsic weapon.
     * @return gets the intrinsic weapon for the Piranha Plant.
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(90, "chomps"); // Should be 90 as per assignment 3
    }

    /**
     * method to pick random prompt for Piranha Plant to speak each turn
     * @param actor default actor, included in interface for speak with Toad
     * @return the string spoken
     */
    @Override
    public String speak(Actor actor) {
        Random r = new Random();
        return this.name + ": " + dialogue[r.nextInt(2)];
    }

}
