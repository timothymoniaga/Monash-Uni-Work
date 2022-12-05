package game;
import java.util.Random;

/**
 * Utils class for handling some probabilities
 */
public class Utils {
    /**
     * Calculates a result randomly based on inputted odds
     * @param odds the probability of returning true
     * @return true if yes, false if no
     */
    public static boolean probReturn(double odds) {
        Random rand = new Random();
        return rand.nextDouble() < odds;
    }

}
