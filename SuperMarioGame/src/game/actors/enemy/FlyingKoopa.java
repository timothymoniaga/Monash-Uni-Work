package game.actors.enemy;

import edu.monash.fit2099.engine.actors.Actor;
import game.actors.Status;

import java.util.Random;

/**
 * Flying Koopa Class
 * @author Peter Roberts
 * @author Sara Hopkins
 * @version Assignment 3
 */
public class FlyingKoopa extends Koopa {
    /**
     * array of strings that FlyingKoopa can speak
     */
    private final String[] dialogue = {"Never gonna make you cry!",
            "Koopi koopi koopii~!",
            "Pam pam pam!"
    };

    private final int bound = 3;

    /**
     * Constructor
     */
    public FlyingKoopa(){
        super("Flying Koopa", 'F', 150); // Should be 150 as per assignment 3
        this.addCapability(Status.FLY);
    }

    @Override
    public String speak(Actor actor) {
        Random r = new Random();
        return this.name + ": " +dialogue[r.nextInt(this.bound)];
    }

}
