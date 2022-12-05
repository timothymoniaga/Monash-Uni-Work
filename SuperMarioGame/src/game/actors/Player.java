package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import game.actions.ResetAction;
import game.items.magic_water.Bottle;
import game.reset.Resettable;

/**
 * Class representing the Player.
 */
public class Player extends Actor implements Resettable, Buyer {
	/**
	 * menu to be printed
	 */
	private final Menu menu = new Menu();

	/**
	 * players wallet balance
	 */
	private int wallet = 0;

	/**
	 * boolean if reset has been done
	 */
	private boolean resetFlag = false;

	/**
	 * players bottle attribute
	 */
	private Bottle bottle;

	/**
	 * boolean check to see if player has a bottle
	 */
	private boolean bottleHeld = false;

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addInstance();
		this.registerResetInstance();
	}

	/**
	 * responsible for logic and actions provided to player on turn
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return the menu display
	 */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		// Handle multi-turn Actions
		if(!resetFlag){
			actions.add(new ResetAction());
		}
		if(this.hasCapability(Status.POWERSTAR)){
			display.println("Mario is INVINCIBLE!");
		}
		if(this.hasCapability(Status.FIRE_ATTACK)){//fire flower power-up active
			display.println("Mario has FIREPOWER!");
		}
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();
		display.println("BALANCE: " + this.getWalletBalance());
		display.println("HP: " + this.printHp());
		return menu.showMenu(this, actions, display);
	}

	/**
	 * checks the display char of player
	 * @return the disp character of player
	 */
	@Override
	public char getDisplayChar(){
		return this.hasCapability(Status.TALL) ? Character.toUpperCase(super.getDisplayChar()): super.getDisplayChar();
	}

	/**
	 * getter for players wallet balance
	 * @return int wallet amount
	 */
	public int getWalletBalance(){return this.wallet;}

	/**
	 * allows balance to be edited without coin instance
	 * @param amount int value
	 */
	public void editBalance(int amount){
		this.wallet = this.wallet + amount;
	}

	/**
	 * after reset occurs, all player capabilities should be removed and HP restored to MaxHP
	 */
	@Override
	public void resetInstance() {
		resetMaxHp(getMaxHp());
		removeCapability(Status.POWERSTAR);
		removeCapability(Status.TALL);
		this.resetFlag = true;
	}

	/**
	 * allows player class to be accessed by global reset
	 */
	@Override
	public void registerResetInstance() {
		Resettable.super.registerResetInstance();
	}

	/**
	 * overrides buyerManager addItemToInventory
	 * @param item the item to be added to inventory
	 * @see BuyerManager
	 */
	@Override
	public void addItemToInventoryBuyer(Item item) {
		this.addItemToInventory(item);
	}

	/**
	 * overrides add capability method so that it can accessed through interface
	 * @param capability the Capability to add
	 */
	@Override
	public void addCapability(Enum<?> capability) {
		super.addCapability(capability);
	}

	/**
	 * interface method to retrieve players bottle instance
	 * @return players bottle
	 */
	@Override
	public Bottle getBottle() {
		return this.bottle;
	}

	/**
	 * setting the players instance of bottle
	 * @param bottle instance of bottle
	 */
	@Override
	public void setBottle(Bottle bottle) {
		this.bottle = bottle;
		this.addItemToInventory(this.bottle);
		this.bottleHeld = true;
	}

	/**
	 * checks if the player has a bottle already
	 * @return true bool if player has bottle
	 */
	@Override
	public boolean hasBottle() {
		return this.bottleHeld;
	}

}
