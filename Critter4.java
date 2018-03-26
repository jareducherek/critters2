/*
 * Critters Assignment
 * Jared Ucherek, JMU329
 * Michael Lanham, ML42972
 */
package assignment5;

//4's will not fight other 4's or bother with plants. When unencumbered,
//it walks around and stays in place.

/**
 *
 * @author Michael
 */
public class Critter4 extends Critter{
    @Override
    public String toString() { return "4"; }
    /**
     * 4's fight method, 4 chooses to avoid algae but fights everything else
     * @param other
     * @return 
     */
    public boolean fight(String other) { 
        if(other.equals("@")){
            return false;
        }
        if(other.equals("S")){
            return false;
        }
        return true; 
    }

    @Override
    /**
     * 4's time step, 4 chooses to stay in place if something has moved on top of it.
     */
    public void doTimeStep() {
        int direction = Critter.getRandomInt(7);
        if(CritterWorld.occupied[this.getY_coord()][this.getX_coord()] > 1){
            return;
        }
        if (Critter.getRandomInt(9) >= 5) {   
                run(Critter.getRandomInt(7));
                setHasMoved(true);
        } 
    }

    @Override
    public CritterShape viewShape() {
        return(Critter.CritterShape.CURVE);
    }
    
    @Override
    public javafx.scene.paint.Color viewColor() { return javafx.scene.paint.Color.BROWN; }
}
