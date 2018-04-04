/*
 * Critters Assignment
 * Jared Ucherek, JMU329
 * Michael Lanham, ML42972
 */
package assignment5;

//3's are fearless critters. They will fight any other critter on the map.
//It will walk around some of the time, choosing its direction randomly.

/**
 *
 * @author Michael
 */
public class Critter3 extends Critter {
    @Override
    public String toString() { return "3"; }
	
    /**
     * 3's fight method, 3 always fights.
     * @param not_used
     * @return 
     */
    public boolean fight(String not_used) { return true; }
    
    /**
     * 3's time step, 3 will walk occasionally if a high number is rolled.
     */
    @Override
    public void doTimeStep() {
        if (Critter.getRandomInt(10) >= 5) {   
            run(Critter.getRandomInt(7));
            setHasMoved(true);
        } 
    }

    @Override
    public CritterShape viewShape() {
        return(Critter.CritterShape.HOURGLASS);
    }
    
    @Override
    public javafx.scene.paint.Color viewFillColor() { return javafx.scene.paint.Color.GREEN; }
    
}
