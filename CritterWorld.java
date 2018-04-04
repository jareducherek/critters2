/*
 * Critters Assignment
 * Jared Ucherek, JMU329
 * Michael Lanham, ML42972
 */
package assignment5;

import java.util.Arrays;

/**
 *
 * @author Michael
 */
public class CritterWorld {
    public static int[][] occupied = new int[Params.world_height][Params.world_width];
    public static int numCritters;
    public static String[][] critterGrid = new String[Params.world_height][Params.world_width];  
    private static String myPackage;
    
    static {
        myPackage = Critter.class.getPackage().toString().split(" ")[1];
    }
    /**
     * initializes the critter world, mainly used for testing purposes, but initializes grid helper variables as well.
     */
    public static void initialize(){
        numCritters = 0;
        empty();

        try{
            Critter.makeCritter("Algae", 100);
            Critter.makeCritter("Craig", 25);
            Critter.makeCritter("Critter1", 25);
            Critter.makeCritter("Critter2", 25);
            Critter.makeCritter("Critter3", 25);
            Critter.makeCritter("Critter4", 25);

        } catch (InvalidCritterException ex) {
            System.out.println("Error in Initialization of Critter World");;
        }

    }
    /**
     * clears the grid helper variables, critterGrid and occupied
     */
     
    public static void empty(){
        numCritters = 0;
        for (String[] row : critterGrid) {
            Arrays.fill(row, "");
        }
        for (int[] row : occupied) {
            Arrays.fill(row, 0);
        }
    }
    
}
