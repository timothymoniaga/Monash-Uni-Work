package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Status;

/**
 * Key for Bowser to drop when dead and for mario to pick up
 * @author Peter Roberts
 * @version Assignment 3
 */
public class Key extends Item {

    /**
     * Constructor
     */
    public Key(){
        super("Key", '%',true);
    }


    /**
     * Tick method for the Key when in inventory.
     * Actor is buffed with the Keyholder status simply for holding the Key
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor){
        actor.addCapability(Status.KEYHOLDER);
    }


}
