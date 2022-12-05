package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.PurchaseAction;
import game.actions.SpeakAction;
import game.items.magical_items.PowerStar;
import game.items.magical_items.SuperMushroom;
import game.items.magic_water.Bottle;
import game.items.weapon.Wrench;

import java.util.List;
import java.util.Random;

/**
 * Class for Toad
 * @author Sara Hopkins
 * @version Assignment 3
 */
public class Toad extends Actor implements Speakable{


    private final String[] dialogue = {"You might need a wrench to smash Koopa's hard shells.",
            "You better get back to finding the Power Stars.",
            "The Princess is depending on you! You are our only hope.",
            "Being imprisoned in these walls can drive a fungus crazy :("
    };
    private boolean count = false;
    /**
     * toad constructor
     */
    public Toad() {
        super("Toad", '0', 999999999);
    }

    /**
     * toad play turn method, executed each round
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return doNothingAction
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        List<Exit> exits = map.locationOf(this).getExits();
        for(Exit exit : exits){
            if(exit.getDestination().containsAnActor()){
                if (exit.getDestination().getActor().hasCapability(Status.HOSTILE_TO_ENEMY)){
                    int check = BuyerManager.getInstance().buyers().indexOf(exit.getDestination().getActor());
                    if (check!= -1){
                        Buyer buyer = BuyerManager.getInstance().buyers().get(check);
                        if (!buyer.hasBottle()) {
                            buyer.setBottle(new Bottle());
                        }
                    }
                }
            }
        }
        if(this.count){
            this.count = false;
            return new SpeakAction(this);
        }
        else{
            this.count = true;
            return new DoNothingAction();
        }

    }

    /**
     * allowable actions of Toad, adding purchase actions if player is in range
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return allowableActionList
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        actions.add(new DoNothingAction());

        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            int check = BuyerManager.getInstance().buyers().indexOf(otherActor);
            if (check!= -1){
                Buyer buyer = BuyerManager.getInstance().buyers().get(check);
                actions.add(new PurchaseAction(new SuperMushroom(true), buyer));
                actions.add(new PurchaseAction(new PowerStar(true), buyer));
                actions.add(new PurchaseAction(new Wrench(), buyer));
                actions.add(new SpeakAction(this, otherActor));
            }
        }
        return actions;
    }

    @Override
    public String speak(Actor actor) {
        Random r = new Random();
        int n;
        String retVal;
        if (actor != null){
            if(actor.hasCapability(Status.WRENCH)){
                n = getRandomWithExclusion(r, 0, 3,0);
            }
            else if(actor.hasCapability(Status.POWERSTAR)){
                n = getRandomWithExclusion(r, 0, 3,1);
            }
            else{
                n = r.nextInt(4);
            }
        }
        else{
            n = r.nextInt(4);
        }
        retVal = dialogue[n];


        return this.name + ": " + retVal;
    }



    /**
     * @param rnd Random object
     * @param start minimum possible value inclusive
     * @param end highest possible value inclusive
     * @param exclude number/s to exclude
     * @return random integer
     */
    public int getRandomWithExclusion(Random rnd, int start, int end, int... exclude) {
        int random = start + rnd.nextInt(end - start + 1 - exclude.length);
        for (int ex : exclude) {
            if (random < ex) {
                break;
            }
            random++;
        }
        return random;
    }
}


