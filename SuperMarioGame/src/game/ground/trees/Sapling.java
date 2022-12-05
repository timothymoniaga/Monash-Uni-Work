package game.ground.trees;

import edu.monash.fit2099.engine.positions.Location;
import game.Utils;
import game.actors.Status;
import game.ground.Dirt;
import game.items.Coin;

/**
 * Class for Sapling Trees
 * @author Peter Roberts
 * @author Sara Hopkins
 * @version Assignment 3
 */

public class Sapling extends Tree {
    /**
     * Probability of coin drop
     */
    private static final double COIN_ODDS = 0.1;
    /**
     * Value of coin dropped
     */
    private static final int COIN_VALUE = 20;
    /**
     * Age at which to grow up into a mature
     */
    private static final int GROW_UP_AGE = 10;
    /**
     * Probability of successful jump
     */
    private static final double JUMP_ODDS = 0.8;
    /**
     * Fall damage for failed jump
     */
    private static final int FALL_DAMAGE = 20;

    /**
     * Constructor
     */
    public Sapling() {
        super('t'); // 't' is the display character for Saplings
    }

    /**
     * Tick method for Sapling
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location){
        if(this.hasCapability(Status.RESET)){
            location.setGround(new Dirt());
        }
        super.incrementAge();
        this.dropCoin(location);
        this.growUp(location);
        this.destroyedByPowerStar(location);
        this.addCapability(Status.CAN_SPAWN);
        this.fire_flower_spawn(location);
    }

    /**
     * Allows Sapling to drop a coin
     * @param location of current Sapling
     */
    public void dropCoin(Location location){
        if(Utils.probReturn(COIN_ODDS) && this.hasCapability(Status.CAN_SPAWN)){
            location.addItem(new Coin(COIN_VALUE,true));
        }
    }

    /**
     * Allows Sapling to mature into a Mature
     * @param location of current Sapling
     */
    public void growUp(Location location){
        if(super.age == GROW_UP_AGE){
            location.setGround(new Mature());
        }
    }

    /**
     * Method called when mature is jumped at
     * @return 0 if jump succeeds, fall damage if jump fails.
     */
    public int didJumpSucceed() {
        if(Utils.probReturn(JUMP_ODDS)){
            return 0;
        }
        return FALL_DAMAGE;
    }

}
