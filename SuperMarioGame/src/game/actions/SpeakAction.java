package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Speakable;

/**
 * Action for speaking
 * @author Sara Hopkins
 * @version Assignment 3
 */
public class SpeakAction extends Action {
    /**
     * speakable actor type
     */
    private final Speakable speakable;

    /**
     * actor instance for toad class only
     */
    private Actor actor;

    /**
     * default constructor for all actors who can speak
     * @param speakable the actor doing the speaking
     */
    public SpeakAction(Speakable speakable) {
        this.speakable = speakable;
    }

    /**
     * special constructor for speak with toad action
     * @param speakable Toad
     * @param actor the player who selected the talk with toad action
     */
    public SpeakAction(Speakable speakable, Actor actor) {
        this.speakable = speakable;
        this.actor = actor;
    }

    /**
     * the execute action method run when hotkey selected
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the interface general speak method (which returns specific speak string for each actor)
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return this.speakable.speak(this.actor);
    }

    /**
     * made super specific because Toad action is the only one that has a menu option
     * @param actor The actor performing the action.
     * @return Toad specific speak with toad menu option
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Speak with Toad"; //technically a cheat here but the only actor that the action will come up for is Toad
    }

}
