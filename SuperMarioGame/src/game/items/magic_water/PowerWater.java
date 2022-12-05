package game.items.magic_water;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
/**
 * PowerWater Class
 * @author Sara Hopkins
 * @version Assignment 3
 */
public class PowerWater extends Water{
    /**
     * PowerWater constructor
     */
    public PowerWater(){
        super("PowerWater", 'P');
    }

    /**
     * consume execute method
     * @param actor actor who consumed the power water
     * @param map map the actor is on
     */
    @Override
    public void toExecute(Actor actor, GameMap map){
        int prev_damage = actor.getWeapon().damage();
        new IntrinsicWeapon(prev_damage+15, "punches");
    }

}
