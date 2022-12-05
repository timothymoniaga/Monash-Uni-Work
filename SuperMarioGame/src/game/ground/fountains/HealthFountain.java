package game.ground.fountains;

import game.items.magic_water.HealthWater;

/**
 * Child Class for HealthFountain Ground Type
 * @author Sara Hopkins
 * @version Assignment 3
 */
public class HealthFountain extends Fountain{
    /**
     * constructor for healthFountain
     */
    public HealthFountain(){
        super('H', new HealthWater());
    }

}
