package game.actors.enemy;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.AttackAction;
import game.actions.SpeakAction;
import game.actors.Speakable;
import game.actors.Status;
import game.behaviour.AttackBehaviour;
import game.behaviour.Behaviour;
import game.behaviour.FollowBehaviour;
import game.items.Key;


import java.util.Map;
import java.util.Random;
import java.util.TreeMap;


/**
 * Bowser class
 * @author Peter Roberts
 * @author Sara Hopkins
 * @version Assignment 3
 */
public class Bowser extends Enemy implements Speakable {

    /**
     * string array of speak string options
     */
    private final String[] dialogue = {"What was that sound? Oh, just a fire.",
            "Princess Peach! You are formally invited... to the creation of my new kingdom!",
            "Never gonna let you down!",
            "Wrrrrrrrrrrrrrrrryyyyyyyyyyyyyy!!!!"
    };

    /**
     * Behaviours treemap
     */
    private final Map<Integer, Behaviour> behaviours = new TreeMap<>(); // priority,

    /**
     * Allows the Bowser to return home on reset
     */
    private final Location home;

    /**
     * Age of bowser, used to allow the bowser to speak every second turn
     */
    private int age = 0;

    public Bowser(Location home){
        super("Bowser",'B',500); // Should be 500 as per assignment 3
        this.home = home;
        this.addItemToInventory(new Key()); // Key for Bowser to drop when dead
        this.addCapability(Status.FIRE_ATTACK);
        this.behaviours.put(1, new AttackBehaviour());
    }


    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            behaviours.put(2,new FollowBehaviour(otherActor));
            actions.add(new AttackAction(this,direction));
        }
        return actions;
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        this.age++;
        // Go home and heal if reset has been run
        if(this.hasCapability(Status.RESET)){
            map.moveActor(this,home);
            this.heal(99999);
            this.behaviours.clear();
            this.removeCapability(Status.RESET);
            return new DoNothingAction();
        }
        // Speak every second turn
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
     * method to pick random prompt for Bowser to speak each turn
     * @param actor default actor, included in interface for speak with Toad
     * @return the string spoken
     */
    @Override
    public String speak(Actor actor) {
        Random r = new Random();
        return this.name + ": " +dialogue[r.nextInt(4)];
    }

    /**
     * Gets the intrinsic weapon for bowser
     * @return IntrinsicWeapon with the characteristics of a bowser
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(80, "punches"); // Should be 80 as per assignment 3
    }

}
