package game.actors.enemy;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.AttackAction;
import game.actions.DestroyShell;
import game.actions.SpeakAction;
import game.actors.Speakable;
import game.actors.Status;
import game.behaviour.AttackBehaviour;
import game.behaviour.Behaviour;
import game.behaviour.FollowBehaviour;
import game.behaviour.WanderBehaviour;
import game.items.magical_items.SuperMushroom;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;


/**
 * Koopa Class
 * @author Peter Roberts
 * @author Sara Hopkins
 * @version Assignment 3
 */
public class Koopa extends Enemy implements Speakable {
    /**
     * Tree Map for storing behaviours
     */
    protected final Map<Integer, Behaviour> behaviours = new TreeMap<>(); // priority, behaviour

    /**
     * array of strings that Koopa can speak
     */
    private final String[] dialogue = {"Never gonna make you cry!",
            "Koopi koopi koopii~!"
    };

    /**
     * boolean to count every second turn to speak
     */
    private boolean count = false;

    private final int bound = 2;

    /**
     * Constructor.
     */
    public Koopa() {
        super("Koopa", 'K', 100);
        this.behaviours.put(10, new WanderBehaviour());
        this.behaviours.put(1, new AttackBehaviour());
        this.addCapability(Status.VALID_CORPSE);
        // Give Koopa a Super Mushroom to drop when it is destroyed
        this.addItemToInventory(new SuperMushroom(true));
    }

    /**
     * Second constructor, allows child classes to have different name, char and hp.
     * @param name Name of the actor
     * @param displayChar display character of the actor
     * @param hitPoints hit points of the actor
     */
    protected Koopa(String name, char displayChar, int hitPoints){
        super(name, displayChar, hitPoints);
        this.behaviours.put(10, new WanderBehaviour());
        this.behaviours.put(1, new AttackBehaviour());
        this.addCapability(Status.VALID_CORPSE);
        // Give Koopa a Super Mushroom to drop when it is destroyed
        this.addItemToInventory(new SuperMushroom(true));
    }

    /**
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return actions list that actors near Koopa can have
     */
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            if(this.hasCapability(Status.VALID_CORPSE)){
                // Koopa can be attacked if it isn't dormant
                actions.add(new AttackAction(this,direction));
            }
            else if(otherActor.hasCapability(Status.WRENCH)){
                // Once dormant, Koopa can no longer be attacked, only destroyed
                actions.add(new DestroyShell(this,direction));
            }
        }
        return actions;
    }

    /**
     * Figures out what Koopa should do next
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return Koopa's next action
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if(this.count){ //speak action every second turn
            this.count = false;
            display.println(this.speak(this));

        }

        this.count = true;
        // Remove the actor on reset
        if(this.hasCapability(Status.RESET)){
            map.removeActor(this);
            return new DoNothingAction();
        }
        // Turn into a shell if dead
        if(!this.isConscious()){
            this.becomeDormant();
        }
        // Allows Koopa to follow whatever attacks it
        Actor attacker = this.startFollowFromExit(map);
        if(attacker != null && this.hasCapability(Status.VALID_CORPSE)){
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
        return new DoNothingAction();
    }

    /**
     * Method for turning Koopa into a shell
     */
    public void becomeDormant(){
        this.setDisplayChar('D');
        this.behaviours.clear(); // Removes all behaviours
        this.removeCapability(Status.VALID_CORPSE);
        this.addCapability(Status.DORMANT);
    }
    /**
     * Method for getting Koopa's intrinsic weapon
     * @return Koopa's intrinsic weapon
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon(){
        // We can use intrinsic weapon here because it automatically has a 50% hit rate.
        return new IntrinsicWeapon(30, "punch"); //
    }

    /**
     * method to pick random prompt for Koopa to speak each turn
     * @param actor default actor, included in interface for speak with Toad
     * @return the string spoken
     */
    @Override
    public String speak(Actor actor) {
        Random r = new Random();
        return this.name + ": " +dialogue[r.nextInt(this.bound)];
    }
}
