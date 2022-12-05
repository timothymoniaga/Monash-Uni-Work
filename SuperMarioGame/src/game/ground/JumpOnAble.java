package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Status;
import game.items.Coin;

public interface JumpOnAble {
    /**
     * Method that runs when Grounds are jumped on.
     * @return 0 if jump is successful, fall damage if jump fails.
     */
    int didJumpSucceed();

    /**
     * Method for destroying JumpOnAble ground when actor walks through it with a PowerStar
     * @param location location of the actor
     */
    default void destroyedByPowerStar(Location location){
        Actor actor = location.getActor();
        if(actor != null && actor.hasCapability(Status.POWERSTAR)){
            location.setGround(new Dirt());
            location.addItem(new Coin(5,true));
        }
    }

    /**
     * Default method to manage who can get to higher ground without jumping.
     * Currently, if you can fly, or if you have a powerstar, you can get to higher ground without jumping
     * @param actor the actor trying to get there without jumping
     * @return boolean whether they are allowed to or not
     */
    default boolean replaceCanActorEnter(Actor actor){
        return actor.hasCapability(Status.POWERSTAR) || actor.hasCapability(Status.FLY);
    }

    /**
     * Method for adding instances of Ground objects that implement JumpOnAble interface (to avoid casting)
     */
    default void addJumpInstance(){
        JumpOnAbleGroundManager.getInstance().appendJumpOnAbleGround(this);
    }

}
