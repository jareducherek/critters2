/*
 * Critters Assignment
 * Jared Ucherek, JMU329
 * Michael Lanham, ML42972
 */
package assignment5;

//A 2 is a critter that generally avoids conflict. It will walk 
//around but runs if threatened by a 3 or a 4. Otherwise, it 
//will fight.


/**
 *
 * @author Michael
 */
public class Critter2 extends Critter {
    @Override
    public String toString() { return "2"; }
    /**
     * 2's fight method, 2 runs from 3's and 4's, but fights everything else
     * @param enemy
     * @return 
     */
    public boolean fight(String enemy) { 
        int direction = Critter.getRandomInt(7);

            
        if (getHasMoved() == false) {
            if (enemy.equals("3") || enemy.equals("4")) {
                String temp = look(direction, false);

                if (temp != null) {
                    return true;
                } 
                return false;  
            } 
        }
        return true;
    }

    @Override
    public void doTimeStep() {
        for(int i = 0; i < 8; i++){
            String temp = look(i, false);
            if(temp != null && temp.equals("@")){
                walk(i);
                setHasMoved(true);
            }
        }
 
    }

    @Override
    public CritterShape viewShape() {
        return(Critter.CritterShape.DIAMOND);
    }
    
    @Override
    public javafx.scene.paint.Color viewFillColor() { return javafx.scene.paint.Color.BLUE; }
}
