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
            for(int i = 0; i < 100; i++){
                Critter.makeCritter("Algae");

            }
            for(int i = 0; i < 25; i++){
                Critter.makeCritter("Craig");

            }
            for(int i = 0; i < 25; i++){
                Critter.makeCritter("Critter1");

            }
            for(int i = 0; i < 25; i++){
                Critter.makeCritter("Critter2");

            }
            for(int i = 0; i < 25; i++){
                Critter.makeCritter("Critter3");

            }
            for(int i = 0; i < 25; i++){
                Critter.makeCritter("Critter4");

            }
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
