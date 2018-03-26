package assignment5;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class Main extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  private static final int width = 900;
  private static final int height = 900;
  private GraphicsContext gc;
  private ColorPicker colorPicker;
  private Color color = Color.BLACK;
  private Button clrButton;
  
  Stage window;
  Scene scene;
  private Button start;
  private Button stop;
  private Button setSeed;
  private Button makeCritters;
  BorderPane pane;

  @Override
  public void start(Stage stage) throws Exception {
	    window = stage;
            window.setTitle("Critter World");
            
            start = new Button("start animation");
            stop = new Button("stop animation");
            setSeed = new Button("Set Seed");
            makeCritters = new Button("Create Critters");
            
            VBox layout = new VBox(10);
            layout.setPadding(new Insets(20, 20, 20, 20));
            layout.getChildren().addAll(start);
            layout.getChildren().addAll(stop);
            layout.getChildren().addAll(setSeed);
            layout.getChildren().addAll(makeCritters);
            
            scene = new Scene(layout, 900, 900);
            window.setScene(scene);
            window.show();
            
//	    MouseHandler handler = new MouseHandler();
//
//	    colorPicker = new ColorPicker();
//            colorPicker.setValue(Color.AQUAMARINE);
//            pane.setTop(colorPicker);
//            
//            
//            
//	    Scene scene = new Scene(pane, width, height);
//	    stage.setScene(scene);
//	    stage.show();
  }
  private class ColorChanger implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent t) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
      
  }
  private class MouseHandler implements EventHandler<MouseEvent> {
	  
	  boolean drawing;
	  double newX;
	  double newY;
	  double oldX;
	  double oldY;
	  
	  public MouseHandler() {
		  drawing = false;
	  }
	
    @Override
    public void handle(MouseEvent event) {
    	if (event.getEventType() == MouseEvent.MOUSE_CLICKED)
    	{
    		drawing = !drawing;
    		newX = event.getX();
    		newY = event.getY();
                System.out.println(newX + "   " + newY);
    	}
    	
    	if (event.getEventType() == MouseEvent.MOUSE_MOVED)
    	{
    		if (drawing) {
    			oldX = newX;
    			oldY = newY;
        		newX = event.getX();
        		newY = event.getY();
    			gc.strokeLine(oldX, oldY, newX, newY);
    		}
    		


    	}
   
    		
 
   
    }

  }
}
