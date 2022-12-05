package game.ground.trees;

import edu.monash.fit2099.engine.positions.Location;
import game.actors.Status;
import game.actors.enemy.Goomba;
import game.Utils;
import game.ground.Dirt;

/**
 * Class for Tree Sprouts
 * @author Peter Roberts
 * @author Sara Hopkins
 * @version Assignment 3
 */
public class Sprout extends Tree {
    /**
     * Probability of spawning Goomba
     */
    private static final double GOOMBA_ODDS = 0.1;
    /**
     * Age at which to mature into a Sapling
     */
    private static final int GROW_UP_AGE = 10;
    /**
     * Probability of jump success
     */
    private static final double JUMP_ODDS = 0.9;
    /**
     * Fall damage incurred when jump fails
     */
    private static final int FALL_DAMAGE = 10;

    /**
     * Constructor
     */
    public Sprout() {
        super('+'); // '+' is the display character for Sprouts
    }

    /**
     * Tick method for Sprout
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location){
        if(this.hasCapability(Status.RESET)){
            location.setGround(new Dirt());
        }
        super.incrementAge();
        this.spawnGoomba(location);
        this.growUp(location);
        this.destroyedByPowerStar(location);
        this.addCapability(Status.CAN_SPAWN);
        this.fire_flower_spawn(location);
    }

    /**
     * Allows Sprout to spawn Goomba
     * @param location of current Sprout
     */
    public void spawnGoomba(Location location){
        if(Utils.probReturn(GOOMBA_ODDS) && !location.containsAnActor() && this.hasCapability(Status.CAN_SPAWN)){
            location.addActor(new Goomba());
        }
    }

    /**
     * Allows sprout to mature into a Sapling
     * @param location of current Sprout
     */
    public void growUp(Location location){
        if(super.age == GROW_UP_AGE){
            location.setGround(new Sapling());
        }
    }

    /**
     * Method called when sprout is jumped at
     * @return 0 if jump succeeds, fall damage if jump fails.
     */
    public int didJumpSucceed() {
        if(Utils.probReturn(JUMP_ODDS)){
            return 0;
        }
        return FALL_DAMAGE;
    }

}
