package game.items.magic_water;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.ConsumableItem;

import java.util.Iterator;
import java.util.Stack;

/**
 * Bottle Class
 * @author Sara Hopkins
 * @version Assignment 3
 */
public class Bottle extends ConsumableItem {
    /**
     * stack of waters that the bottle can hold
     */
    private final Stack<Water> stack;

    /**
     * Bottle constructor, creates new stack
     */
    public Bottle(){
        super("Bottle", 'B', false);
        stack = new Stack<>();
    }

    /**
     * adds water to bottle
     * @param water water type/ instance to be added to stack
     */
    public void addToBottle(Water water){
        this.stack.push(water);
    }

    /**
     * method to remove water from bottle
     * @return the removed element
     */
    public Water removeFromBottle(){
        return this.stack.pop();
    }

    /**
     * consume method
     * @param actor Actor that is consuming the item
     * @param map GameMap that the actor is on
     */
    @Override
    public void toExecute(Actor actor, GameMap map){
        Water water = removeFromBottle();
        water.toExecute(actor, map);
    }

    /**
     * method that overrides default to string method to return all the elements in the bottle in the action string
     * @return concatenated string with water in bottle listed
     */
    @Override
    public String toString() {
        Iterator<Water> iterator = stack.iterator();
        String str = "[ ";
        int count = 0;
        while(iterator.hasNext()){
            Water water = iterator.next();
            if (count == 0){
                str += water.toString();
                count += 1;
            }
            else{
                str += ", ";
                str += water.toString();
            }
        }
        str += " ]";
        return "Bottle " + str;
    }

}
