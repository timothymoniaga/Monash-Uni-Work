package game.ground.trees;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Utils;
import game.actions.JumpAction;
import game.actors.Status;
import game.ground.JumpOnAble;
import game.items.magical_items.FireFlower;
import game.reset.Resettable;


/**
 * Abstract Class for handling Trees
 * @author Peter Roberts
 * @author Sara Hopkins
 * @version Assignment 3
 */
public abstract class Tree extends Ground implements JumpOnAble, Resettable {
    /**
     * Probability that the tree dies on reset.
     */
    private static final double RESET_ODDS = 0.5;
    /**
     * the odds of a fire flower spawning each turn
     */
    public static final double FIRE_FLOWER_ODDS = 0.5;
    /**
     * The tree's age (in turns)
     */
    protected int age;

    /**
     * The tree's constructor
     * @param displayChar gets display character from subclasses
     */
    public Tree(char displayChar) {
        super(displayChar);
        this.age = 0;
        this.addJumpInstance();// All trees can be jumped on
        this.registerResetInstance(); // All trees can be reset
        this.addCapability(Status.CAN_SPAWN);
    }

    /**
     * Method that allows the tree to age every turn
     */
    public void incrementAge(){
        this.age++;
    }

    /**
     *
     * @param actor the Actor to check
     * @return true if actor can enter tree, false otherwise.
     * @see JumpOnAble replaceCanActorEnter(actor)
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return replaceCanActorEnter(actor);
    }

    /**
     * method to spawn fire flower
     * @param location of the tree
     */
    public void fire_flower_spawn(Location location){
        if(Utils.probReturn(FIRE_FLOWER_ODDS)){
            location.addItem(new FireFlower());
        }
    }

    /**
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return The actions that an actor can perform when standing next to a tree
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction){
        ActionList actions =  new ActionList();
        if(!location.containsAnActor() && !replaceCanActorEnter(actor)){
            actions.add(new JumpAction(location, direction));
        }
        return actions;
    }

    /**
     * Reset method for the tree
     */
    @Override
    public void resetInstance(){
        if(Utils.probReturn(RESET_ODDS))
        {
            this.addCapability(Status.RESET);
        }
        this.removeCapability(Status.CAN_SPAWN);
    }

}
