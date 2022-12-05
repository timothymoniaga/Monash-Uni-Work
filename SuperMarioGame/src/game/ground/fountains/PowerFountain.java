package game.ground.fountains;

import game.items.magic_water.PowerWater;

/**
 * Child Class for PowerFountain Ground Type
 * @author Sara Hopkins
 * @version Assignment 3
 */
public class PowerFountain extends Fountain{
    /**
     * constructor for PowerFountain
     */
    public PowerFountain(){
        super('A', new PowerWater());
    }

}
