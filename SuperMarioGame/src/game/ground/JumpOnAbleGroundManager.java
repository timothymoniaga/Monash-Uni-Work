package game.ground;

import java.util.ArrayList;

/**
 * Singleton class for managing ground types that can be jumped on
 * @author Peter Roberts
 * @version Assignment 2
 */
public class JumpOnAbleGroundManager {
    /**
     * List of Ground Instances that implement the interface JumpOnAble
     */
    private final ArrayList<JumpOnAble> jumpOnAbles;

    /**
     * Single instance of this class
     */
    private static JumpOnAbleGroundManager instance;

    /**
     * Private constructor
     */
    private JumpOnAbleGroundManager() {jumpOnAbles = new ArrayList<>();}

    /**
     * Get instance method
     * @return the instance
     */
    public static JumpOnAbleGroundManager getInstance(){
        if (instance == null) {
            instance = new JumpOnAbleGroundManager();
        }
        return instance;
    }

    /**
     * Method for adding a ground instance to the jumpOnAbles list
     * @param ground jumpOnAble ground to be added to the list
     */
    public void appendJumpOnAbleGround(JumpOnAble ground){this.jumpOnAbles.add(ground);}

    /**
     * Getter for the jumpOnAbles list
     * @return the list
     */
    public ArrayList<JumpOnAble> getJumpOnAbles(){
        return new ArrayList<>(this.jumpOnAbles);
    }

}
