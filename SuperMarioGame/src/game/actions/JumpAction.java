package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Status;
import game.ground.JumpOnAble;
import game.ground.JumpOnAbleGroundManager;

/**
 * Action for Jumping
 * @author Peter Roberts
 * @version Assignment 2
 */
public class JumpAction extends Action {
    /**
     * Location being jumped to
     */
    protected Location jumpToLocation;
    /**
     * Direction to jumpToLocation
     */
    protected String direction;
    /**
     * Ground at the jumpToLocation (must implement JumpOnAble)
     */
    protected JumpOnAble jumpToGround;

    /**
     * Constructor
     * @param jumpToLocation Location being jumped to
     * @param direction Direction to jumpToLocation
     */
    public JumpAction(Location jumpToLocation, String direction) {
        this.direction = direction;
        this.jumpToLocation = jumpToLocation;
        // Gets the Ground from jumpToLocation from the JumpOnAbleGroundManager, so that JumpOnAble is definitely implemented.
        int check = JumpOnAbleGroundManager.getInstance().getJumpOnAbles().indexOf(jumpToLocation.getGround());
        if(check != -1){
            this.jumpToGround = JumpOnAbleGroundManager.getInstance().getJumpOnAbles().get(check);
        }

    }
    /**
     * Execute Method
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return Result of the jump
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        int jumpResult = jumpToGround.didJumpSucceed();
        if(jumpResult == 0 || actor.hasCapability(Status.TALL)){
            map.moveActor(actor, this.jumpToLocation);
            return "Jumped successfully to " + this.jumpToGround.getClass().getSimpleName() + "(" + this.jumpToLocation.x() + ", " + this.jumpToLocation.y() + ")";
        }
        actor.hurt(jumpResult);
        return "Jump failed. HP has been decreased by " + jumpResult;
    }

    /**
     * Menu Description Method
     * @param actor The actor performing the action.
     * @return menu description
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " jumps on " + this.jumpToGround.getClass().getSimpleName() + " to the " + direction;
    }

}
