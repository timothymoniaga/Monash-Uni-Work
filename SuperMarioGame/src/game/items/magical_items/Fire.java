package game.items.magical_items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
/**
 * Fire Class
 * @author Sara Hopkins
 * @author Peter Roberts
 * @version Assignment 3
 */
public class Fire extends Item {
    /**
     * turns counter for how long fire stays on floor
     */
    private int turns;
    /**
     * amount of damage
     */
    private static final int DAMAGE = 20;
    /**
     * Constructor.
     */
    public Fire() {
        super("Fire", 'v', false);
        this.turns = 0;
    }

    /**
     * tick method tracks passage of time for fire on the ground
     * will be removed if on ground for 3 turns
     * if not expired, check for an actor and deal 20 damage
     * @param location the location of the fire
     */
    @Override
    public void tick(Location location) {
        this.turns++;
        if (this.turns == 3) {
            location.removeItem(this);
        }
        else if(location.containsAnActor()){
            hurtActor(location);
        }
    }

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
