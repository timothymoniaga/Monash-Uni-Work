package game.ground.trees;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemy.FlyingKoopa;
import game.Utils;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import game.actors.Status;
import game.actors.enemy.Koopa;
import game.ground.Dirt;

/**
 * Class for Mature Trees
 * @author Peter Roberts
 * @author Sara Hopkins
 * @version Assignment 3
 */
public class Mature extends Tree {
    /**
     * Probability of Spawning Koopa
     */
    private static final double KOOPA_ODDS = 0.15;

    /**
     * Given the decision to spawn koopa, probability of said Koopa being able to fly.
     */
    private static final double KOOPA_FLY_ODDS = 0.5;
    /**
     * Probability of Suicide
     */
    private static final double SUICIDE_ODDS = 0.2;
    /**
     * Probability of Actor's jump succeeding
     */
    private static final double JUMP_ODDS = 0.7;
    /**
     * Fall damage incurred by actor upon jump fail
     */
    private static final int FALL_DAMAGE = 30;

    /**
     * Constructor.
     */
    public Mature() {
        super('T'); // 'T' is the display character for Matures
    }

    /**
     * Tick Method for Mature
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location){
        if(this.hasCapability(Status.RESET)){
            location.setGround(new Dirt());
        }
        super.incrementAge();
        this.spawnKoopa(location);
        this.spawnSprout(location);
        this.suicide(location);
        this.destroyedByPowerStar(location);
        this.addCapability(Status.CAN_SPAWN);
        this.fire_flower_spawn(location);

    }

    /**
     * Method for spawning Koopa
     * @param location location of the current Mature
     */
    public void spawnKoopa(Location location){
        if(Utils.probReturn(KOOPA_ODDS) && !location.containsAnActor() && this.hasCapability(Status.CAN_SPAWN)){
            if(Utils.probReturn(KOOPA_FLY_ODDS)){
                location.addActor(new FlyingKoopa());
            }
            else{
                location.addActor(new Koopa());
            }
        }
    }

    /**
     * Method for considering suicide
     * @param location location of the current Mature
     */
    public void suicide(Location location){
        if(Utils.probReturn(SUICIDE_ODDS)){
            location.setGround(new Dirt());
        }
    }

    /**
     * Method for spawning a sprout at a random nearby fertile location
     * @param location location of the current Mature
     */
    public void spawnSprout(Location location){
        Random rand = new Random();
        List<Exit> exits = location.getExits();
        List<Exit> dirtExits = new ArrayList<>();
        if(super.age%5 == 0 && this.hasCapability(Status.CAN_SPAWN)){
            for(Exit exit : exits){
                if(exit.getDestination().getGround().hasCapability(Status.FERTILE)){
                    dirtExits.add(exit);
                }
            }
            if(!dirtExits.isEmpty())
            {
                Exit chosenExit = dirtExits.get(rand.nextInt(dirtExits.size()));
                chosenExit.getDestination().setGround(new Sprout());
            }
        }
    }

    /**
     * Method called when mature is jumped at.
     * @return 0 if jump succeeds, fall damage if jump fails
     */
    @Override
    public int didJumpSucceed() {
        if(Utils.probReturn(JUMP_ODDS)){
            return 0;
        }
        return FALL_DAMAGE;
    }


}
