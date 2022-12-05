package game.ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.JumpAction;
import game.actions.WarpAction;
import game.actors.Status;
import game.actors.enemy.PiranhaPlant;
import game.reset.Resettable;


public class WarpPipe extends Ground implements JumpOnAble, Resettable {

    /**
     * Age of the WarpPipe (since last reset).
     */
    private int age = 0;

    /**
     * Constructor.
     */
    public WarpPipe(){
        super('C');
        this.addJumpInstance();
        this.registerResetInstance();
    }

    /**
     * Tick method for the Warp Pipe
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location){
        this.age++;
        spawnPiranhaPlant(location);
    }

    /**
     * Allowable Actions for the Warp Pipe
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return actions that the actor standing on/next to the warp pipe cn do
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction){
        ActionList actions =  new ActionList();

        // Jumping
        if(!location.containsAnActor() && !replaceCanActorEnter(actor)){
            actions.add(new JumpAction(location, direction));
        }

        // Teleporting
        if(location.getActor() == actor && actor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            actions.add(new WarpAction());
        }

        return actions;
    }

    /**
     *
     * @param actor the Actor to check
     * @return whether or not the actor can enter
     * @see JumpOnAble replaceCanActorEnter(actor)
     */
    @Override
    public boolean canActorEnter(Actor actor){
        return replaceCanActorEnter(actor);
    }

    /**
     * Implementation of the JumpOnAble interface method
     * @return 0, because all jumps to Warp Pipe's are successful.
     */
    @Override
    public int didJumpSucceed() {
        return 0;
    }

    /**
     * Method for handling piranha plant spawns.
     * @param location location of the current ground.
     */
    public void spawnPiranhaPlant(Location location){
        if((this.age == 1 || this.hasCapability(Status.RESET))  && !location.containsAnActor()){
            location.addActor(new PiranhaPlant());
            this.removeCapability(Status.RESET);
        }
    }

    /**
     * Buffs with reset to handle in spawnPiranhaPlant
     */
    @Override
    public void resetInstance() {
        this.addCapability(Status.RESET);
    }
}
