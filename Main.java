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
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.HBox;
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
  private Button seedButton;
  private Button makeCritters;
  private ChoiceBox<Integer> anmSpeed;
  private ChoiceBox<Integer> stepAmount;
  private ChoiceBox<String> getStats;
  BorderPane pane;

  @Override
  public void start(Stage stage) throws Exception {
	    window = stage;
            window.setTitle("Critter World");
            pane = new BorderPane();
            HBox hbox = addHBox();
            
            
            getStats = new ChoiceBox<>();
            getStats.getSelectionModel().selectedItemProperty().addListener((v, oldItem, newItem) -> getStats(newItem));
            
//            pane.setTop(start);
            
            

//            layout.getChildren().addAll(stop);
//            layout.getChildren().addAll(setSeed);
//            layout.getChildren().addAll(makeCritters);
            
            scene = new Scene(hbox, 900, 900);
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
  
  private void startAnimation(ChoiceBox<Integer> anmSpeed){
      anmSpeed.getValue();
  }
  private void step(ChoiceBox<Integer> stepAmount){
      stepAmount.getValue();
  }
  private void setSeed(){
      
  }
  private void getStats(String critter){
      
  }
  
  public HBox addHBox() {
    HBox hbox = new HBox();
    hbox.setPadding(new Insets(15, 12, 15, 12));
    hbox.setSpacing(10);
    hbox.setStyle("-fx-background-color: #336699;");
    
    anmSpeed = new ChoiceBox<>();
    stepAmount = new ChoiceBox<>();
    anmSpeed.getItems().addAll(1,5,10,50);
    anmSpeed.setValue(1);
    stepAmount.getItems().addAll(1,5,10,50);
    stepAmount.setValue(1);
    
    start = new Button("start animation");
    stop = new Button("stop animation");
    seedButton = new Button("Set Seed");
    makeCritters = new Button("Create Critters");
    start.setOnAction(e -> startAnimation(anmSpeed));
    stop.setOnAction(e -> step(stepAmount));
    seedButton.setOnAction(e -> setSeed());
    
    hbox.getChildren().addAll(start, stop, seedButton, makeCritters, anmSpeed, stepAmount);

    return hbox;
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
