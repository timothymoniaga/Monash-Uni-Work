package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Status;

/**
 * Class for the Lava Ground type
 */
public class Lava extends Ground {
    /**
     * Damage sustained by actor for spending a turn on the lava
     */
    private static final int DAMAGE = 15;

    /**
     * Constructor.
     */
    public Lava(){
        super('L');
    }

    /**
     * Tick method for the Lava
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location){
        hurtActor(location);
    }

    /**
     * Stops enemies from entering the lava
     * @param actor the Actor to check
     * @return boolean whether or not the actor can enter
     */
    @Override
    public boolean canActorEnter(Actor actor){
        return !actor.hasCapability(Status.LAVA_BANNED);
    }

    /**
     * Method for hurting the actor, and then removing it if it is dead
     * @param location location of the ground
     */
    public void hurtActor(Location location){
        Actor actor = location.getActor();
        if(actor != null){
            actor.hurt(DAMAGE);
            if(!actor.isConscious()){
                location.map().removeActor(actor);
            }
        }
    }

}
