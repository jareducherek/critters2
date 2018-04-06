/* CRITTERS Main.java
 * EE422C Project 5 submission by
 * Gina Lu
 * gbl286
 * 16480
 * Jessica Slaughter
 * jts3329
 * 16470
 * Slip days used: <0>
 * Fall 2016
 */

package assignment5;

import javafx.scene.paint.*;
import javafx.scene.shape.*;

public class Painter {
	
	static double size = 10;
	/**
	 * This method converts a CritterShape to a Shape object
	 * @param shape is the CritterShape
	 * @return the Shape version of the CritterShape
	 */
	static Shape getIcon(Critter.CritterShape shape) {
		Shape s = drawTriangle();
		switch(shape) { 
			case SQUARE: s = new Rectangle(size, size); 
				break; 
			case CIRCLE: s = new Circle(size/2); 
				break;
			case TRIANGLE: s = drawTriangle();
				break;
			case DIAMOND: s = drawDiamond();
				break;
			case STAR: s = drawStar();
                                break;  
                        case PENTAGON: s = drawTriangle();
                            break;
                        case HOURGLASS: s = drawHourGlass();
                            break;
                        case CURVE: s = drawTriangle();
                            break;
		default:
			break;
		} 
		return s;
	}
	
	/**
	 * This method sets the size of each square in the grid
	 * based on the size of the world.
	 */
	public static void setSize() {
		// scale grid
		if (Params.world_height <= 20) {
				size = 20;
		} else {
                    size = ((Main.width)/Params.world_width < (Main.height)/Params.world_height) ? (Main.width)/Params.world_width 
                            : (Main.height)/Params.world_height;
                }
		
	}
	
	/**
	 * This method paints the grid.
	 */
	public static void paint() { 
		Main.modelGrid.getChildren().clear();
		paintGrid();
	}
	
	/**
	 * This method paints a Critter on the grid.
	 * @param x is the critter's x coordinate
	 * @param y is the critter's y coordinate
	 * @param shape is the critter's shape
	 * @param color is the critter's color
	 * @param outline is the critter's outline color
	 * @param fill is the critter's fill color
	 */
	public static void paintCritter(int x, int y, Critter.CritterShape shape, Color color, Color outline, Color fill) {
		Shape s = getIcon(shape);
		if (color.equals(fill)) {
			s.setFill(fill);
			if (outline.equals(Color.WHITE)) {
				s.setStroke(fill);
			}
			else {
				s.setStroke(outline);
			}
		}
		else if (color.equals(Color.WHITE)) {
			s.setFill(fill);
			if (outline.equals(Color.WHITE)) {
				s.setStroke(fill);
			}
			else {
				s.setStroke(outline);
			}
		}
		else {
			s.setFill(color);
			if (outline.equals(Color.WHITE)) {
				s.setStroke(color);
			}
			else {
				s.setStroke(outline);
			}
		}
		Main.modelGrid.add(s, x, y);
	}
	
	/**
	 * This method adds the grid lines.
	 */
	private static void paintGrid() {
		for (int i = 0; i < Params.world_width; i++) {
			for (int j = 0; j < Params.world_height; j++) {
				Shape s = new Rectangle(size, size); 
				s.setFill(Color.WHITE);
				s.setStroke(Color.BLACK); 
				Main.modelGrid.add(s, i, j);
			}
		}
	}
	
	/**
	 * This method draws a triangle shape.
	 * @return the triangle
	 */
	private static Shape drawTriangle() {
		Polygon triangle = new Polygon();
		triangle.getPoints().addAll(new Double[]{
			    (double)size/2.0, 1.0,
			    (double)size - 1.0, (double)size - 1.0,
			    1.0, (double)size-1.0
		});
		return triangle;
	}
	
	/**
	 * This method draws a diamond shape.
	 * @return the diamond
	 */
	private static Shape drawDiamond() {
		Polygon diamond = new Polygon();
		diamond.getPoints().addAll(new Double[]{
				(double)size/2.0, 1.0,
				(double)size - 1.0, (double)size/2.0,
				(double)size/2.0, (double)size - 1.0,
				1.0, (double)size/2.0
		});
		return diamond;
	}
	
	/**
	 * This method draws a star shape.
	 * @return the star
	 */
	private static Shape drawStar() {
		Polygon star = new Polygon();
		star.getPoints().addAll(new Double[]{
                            (double)size/2.0, 1.0,
                            (double)size - 1.0, (double)size/2.0,
                            (double)size/2.0 + (double)size/4.0, (double)size/2.0 + 1.0,
			    (double)size - 2.0, (double)size - 1.0,
			    (double)size/2.0, (double)size/2.0 + (double)size/4.0,
			    2.0, (double)size-1.0,
			    (double)size/2.0 - (double)size/4.0, (double)size/2.0 + 1.0,
			    1.0, (double)size/2.0
				
		});
		return star;
	}

    private static Shape drawPentagon() {
        Polygon pentagon = new Polygon();
		pentagon.getPoints().addAll(new Double[]{
                            (double)size/2.0, 1.0,
                            (double)size - 1.0, (double)size/2.0,
                            (double)size/2.0 + (double)size/4.0, (double)size/2.0 + 1.0,
			    (double)size - 2.0, (double)size - 1.0,
			    (double)size/2.0, (double)size/2.0 + (double)size/4.0,
			    2.0, (double)size-1.0,
			    (double)size/2.0 - (double)size/4.0, (double)size/2.0 + 1.0,
			    1.0, (double)size/2.0
				
		});
        return pentagon;    
    }

    private static Shape drawHourGlass() {
        Polygon hourGlass = new Polygon();
		hourGlass.getPoints().addAll(new Double[]{
                            0.0, 0.0,
                            (double)size - 1.0, 0.0,
                            0.0, (double)size - 1.0,
			    0.0, 0.0				
		});
        return hourGlass;   
    }

    private static Shape drawCurve() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}