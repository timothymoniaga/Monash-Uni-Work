package game.items.magic_water;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * HealthWater Class
 * @author Sara Hopkins
 * @version Assignment 3
 */
public class HealthWater extends Water{
    /**
     * Health Water constructor
     */
    public HealthWater(){
        super("HealthWater", 'H');
    }

    /**
     * consume execute method
     * @param actor actor who consumed the water
     * @param map default parameter for other consume methods
     */
    @Override
    public void toExecute(Actor actor, GameMap map){
        actor.heal(50);
    }

}
