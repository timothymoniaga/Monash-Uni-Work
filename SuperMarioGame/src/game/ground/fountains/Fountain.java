package game.ground.fountains;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.actions.FillAction;
import game.actors.Buyer;
import game.actors.Status;
import game.actors.BuyerManager;
import game.items.magic_water.Water;

/**
 * Abstract Class for Fountain Ground Type
 * @author Sara Hopkins
 * @version Assignment 3
 */
public abstract class Fountain extends Ground {
    /**
     * water available at fountain
     */
    private final Water water;

    /**
     * capacity of fountain
     */
    private int count;

    /**
     * recharging/refilling timer
     */
    private int timer = 0;

    /**
     * boolean to determine if fountain can be refilled
     */
    private boolean empty = false;

    /**
     * generic constructor to allow children to have their own display characters
     * @param dispchar child class can input their own display char
     * @param water water type of child classes
     */
    public Fountain(char dispchar, Water water){
        super(dispchar);
        this.water = water;
        this.count = 10; //set capacity as 10
    }

    /**
     * method to add actions to the grounds allowable actions
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return the action list
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();
        if (this.count>0){ //if there's water in the fountain
            if (location.containsAnActor()){ //only if actor is standing on fountain
                if(!this.empty){//if it's not being refilled
                    if (actor.hasCapability(Status.FLOOR_BANNED)) { //if its an enemy
                        actions.add(new ConsumeAction(this.water)); // it can consume straight from fountain
                    }
                }
                int check = BuyerManager.getInstance().buyers().indexOf(actor);
                if (check != -1) { //if the actor on the fountain is a buyer, i.e., player
                    Buyer buyer = BuyerManager.getInstance().buyers().get(check); //buyer = player
                    if(!this.empty){ //if the fountain isn't refilling
                        actions.add(new FillAction(buyer.getBottle(), this.water, this));
                    } // give player a fill bottle action
                }
            }
        }
        return actions;
    }

    /**
     * method to check fountain capacity and status of recharge
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        if (count == 0){//if empty
            this.empty = true;//update bool
        }
        if (this.empty){//when bool is true
            if (timer == 5){ //if 5 turns have passed
                timer = 0; //reset timer
                this.empty = false; //set to false bc fountain has finished refilling
            }
            else{
                this.refillFountain(); //refill fountain
            }
        }
    }

    /**
     * method to get the fountain capacity
     * @return int value of capacity
     */
    public int getCount() {
        return this.count;
    }

    /**
     * method called when player fills waterbottle to update fountain capacity
     */
    public void fillBottleFromFountain(){
        this.count = this.count - 5; //takes 3 water to fill a bottle
    }

    /**
     * method to be called each turn to refill the fountain capacity
     */
    public void refillFountain(){
        if((count + 3) > 10) {
            count = 10;
        }
        else{
            count = count + 2;
        }
        timer++;
    }

}


