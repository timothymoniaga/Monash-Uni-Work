package game.items.magic_water;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.ConsumableItem;

/**
 * Water Class
 * @author Sara Hopkins
 * @version Assignment 3
 */
public abstract class Water extends ConsumableItem {
    /**
     * Water abstract constructor
     * @param name allows child classes to have their own water name
     * @param dispchar allows child classes to have their own display char
     */
    public Water(String name, char dispchar){
        super( name, dispchar,false);

    }

    /**
     * empty execute consume action method
     * @param actor Actor that is consuming the item8246848226r826377
     * @param map GameMap that the actor is on
     */
    @Override
    public void toExecute(Actor actor, GameMap map){}

}
