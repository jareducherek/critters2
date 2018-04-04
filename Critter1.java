/*
 * Critters Assignment
 * Jared Ucherek, JMU329
 * Michael Lanham, ML42972
 */
package assignment5;



import java.util.logging.Level;
import java.util.logging.Logger;

//A 1 is a simple critter. It will not engage in any fights unless
//it encounters algae. It will walk away from a fight otherwise. When
//unprovoked, it will walk around the map some of the time and stay 
//in place other times.


/**
 *
 * @author Michael
 */
public class Critter1 extends Critter {
	
	@Override
	public String toString() { return "1"; }
	/**
         * 1's fight method, 1 attempts to run from anything but algae
         * @param enemy
         * @return 
         */		
	public boolean fight(String enemy) { 
            int direction = Critter.getRandomInt(7);
            
            if (getHasMoved() == false) {
                if (enemy.equals("@")) {
                    return true; 
                } else {
                    String temp = look(direction, false);
                    
                    if (temp != null) {
                        return true;
                    }                                         
                    return false; 
                }
            } else {
                return true;
            }
        }

	@Override
	public void doTimeStep() {
            if (Critter.getRandomInt(9) >= 5) {   
                walk(Critter.getRandomInt(7));
                setHasMoved(true);
            } 
            
	}

    @Override
    public CritterShape viewShape() {
        return(Critter.CritterShape.STAR);
    }
    
    @Override
    public javafx.scene.paint.Color viewColor() { return javafx.scene.paint.Color.RED; }

}