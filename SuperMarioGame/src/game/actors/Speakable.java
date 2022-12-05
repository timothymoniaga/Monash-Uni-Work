package game.actors;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * Speakable Interface
 * @author Sara Hopkins
 * @version Assignment 3
 */
public interface Speakable {
    /**
     * speak action general so that speak action can have specific prompts
     * @param actor if speak with toad action, this contains the player
     * @return String spoken
     */
    String speak(Actor actor);

}
