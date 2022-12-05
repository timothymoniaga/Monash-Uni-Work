package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.SpeakAction;
import game.actions.UnlockCuffs;

import java.util.Random;
/**
 * Koopa Class
 * @author Peter Roberts
 * @author Sara Hopkins
 * @version Assignment 3
 */
public class PrincessPeach extends Actor implements Speakable{
    /**
     * array of string speak prompts for peach
     */
    private final String[] dialogue = {"Dear Mario, I'll be waiting for you...",
            "Never gonna give you up!",
            "Release me, or I will kick you!"
    };

    /**
     * boolean to count every second turn to speak
     */
    private boolean count = false;

    /**
     * Constructor for Peach Actor
     */
    public PrincessPeach(){
        super("Princess Peach", 'P',999999);
    }


    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        // She can only be rescued by keyholders that are hostile to enemies.
        if(otherActor.hasCapability(Status.KEYHOLDER) && otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new UnlockCuffs()); // Unlock cuffs action
        }
        return actions;
    }

    /**
     * Peach play turn
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the action peach takes
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if(this.count){
            this.count = false;
            display.println(this.speak(this));
        }

        this.count = true;
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
        return this.name + ": " + dialogue[r.nextInt(3)];
    }

}
