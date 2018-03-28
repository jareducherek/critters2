package assignment5;
import java.awt.Image;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.shape.Rectangle;
import java.util.ResourceBundle.Control;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

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
  private Button stepButton;
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

            pane.setTop(addHBoxTop());
            pane.setBottom(addHBoxBot());
//            addStackPane(hbox);         // Add stack to HBox in top region
            pane.setCenter(makeGrid(Params.world_height, Params.world_width));
            BorderPane.setAlignment(pane, Pos.CENTER_RIGHT);
            
            getStats = new ChoiceBox<>();
            getStats.getSelectionModel().selectedItemProperty().addListener((v, oldItem, newItem) -> getStats(newItem));
            

            
            

//            layout.getChildren().addAll(stop);
//            layout.getChildren().addAll(setSeed);
//            layout.getChildren().addAll(makeCritters);
            
            scene = new Scene(pane, 900, 900);
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
      int steps = stepAmount.getValue();
      
      for(int i = 0; i < steps; i++){
            Critter.worldTimeStep();
      }
      
      
  }
  
  private void setSeed(){
   
    
    Dialog dialog = new TextInputDialog("Enter a seed: ");
    dialog.setTitle("Seed Entry Window");
    dialog.setHeaderText("Current seed: ");
    Optional<String> result = dialog.showAndWait();
    String entered = "none.";
    if (result.isPresent() && isInteger(result.get())) {
        Critter.setSeed(Integer.parseInt(result.get()));
        System.out.println("seed set!");
    }
   
  }
  
  private void getStats(String critter){
      
  }
  
  private void makeCritters(){
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle("Critter Creation Menu");
    alert.setHeaderText("Current Critters: ");
    alert.setContentText("Choose your option.");

    ButtonType one = new ButtonType("1");
    ButtonType two = new ButtonType("5");
    ButtonType three = new ButtonType("10");
    ButtonType four = new ButtonType("25");
    ButtonType buttonTypeFinish = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

    alert.getButtonTypes().setAll(one, two, three, four, buttonTypeFinish);

    Optional<ButtonType> result = alert.showAndWait();
    while(result.get() != buttonTypeFinish){
        if (result.get() == one){
            // ... user chose "One"
        } else if (result.get() == two) {
            // ... user chose "Two"
        } else if (result.get() == four) {
            // ... user chose "Three"
        }
        result = alert.showAndWait();
    }
  }
  private HBox addHBoxTop() {
    HBox hBoxTop = new HBox();
    hBoxTop.setPadding(new Insets(20, 12, 20, 12));
    hBoxTop.setSpacing(20);
    hBoxTop.setStyle("-fx-background-color: #336699;");
    
    
    
    anmSpeed = new ChoiceBox<>();
    stepAmount = new ChoiceBox<>();
    start = new Button("start animation");
    stop = new Button("stop animation");
    stepButton = new Button("step");
    seedButton = new Button("Set Seed");
    makeCritters = new Button("Create Critters");
    
    anmSpeed.getItems().addAll(1,5,10,50);
    anmSpeed.setValue(1);
    stepAmount.getItems().addAll(1,5,10,50);
    stepAmount.setValue(1);
    
    start.setOnAction(e -> startAnimation(anmSpeed));
    stop.setOnAction(e -> step(stepAmount));
    seedButton.setOnAction(e -> setSeed());
    makeCritters.setOnAction(e -> makeCritters());
    VBox anm = new VBox(5); // 5 is the spacing between elements in the VBox
    anm.getChildren().addAll(start, stepButton);
    anm.setAlignment(Pos.CENTER_LEFT);

    VBox stepper = new VBox(5); // 5 is the spacing between elements in the VBox
    stepper.getChildren().addAll(anmSpeed, stepAmount);
    stepper.setAlignment(Pos.CENTER);
    
    VBox misc = new VBox(5); // 5 is the spacing between elements in the VBox
    misc.getChildren().addAll(seedButton, makeCritters);
    misc.setAlignment(Pos.CENTER_RIGHT);

    
    hBoxTop.getChildren().addAll(anm, stepper, misc);
    HBox.setHgrow(misc, Priority.ALWAYS);
    
    return hBoxTop;
}
  
  private HBox addHBoxBot(){
    HBox hBoxBot = new HBox();
    hBoxBot.setPadding(new Insets(10));
    hBoxBot.setSpacing(8);
    hBoxBot.setStyle("-fx-background-color: #336699;");
    
    Text title = new Text("Data");
    title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
    hBoxBot.getChildren().add(title);



    return hBoxBot;
}
  
  private GridPane addGridPane() {
    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(0, 10, 0, 10));

    // Category in column 2, row 1
    Text category = new Text("Sales:");
    category.setFont(Font.font("Arial", FontWeight.BOLD, 20));
    grid.add(category, 1, 0); 

    // Title in column 3, row 1
    Text chartTitle = new Text("Current Year");
    chartTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
    grid.add(chartTitle, 2, 0);

    // Subtitle in columns 2-3, row 2
    Text chartSubtitle = new Text("Goods and Services");
    grid.add(chartSubtitle, 1, 1, 2, 1);


    // Left label in column 1 (bottom), row 3
    Text goodsPercent = new Text("Goods\n80%");
    GridPane.setValignment(goodsPercent, VPos.BOTTOM);
    grid.add(goodsPercent, 0, 2); 


    // Right label in column 4 (top), row 3
    Text servicesPercent = new Text("Services\n20%");
    GridPane.setValignment(servicesPercent, VPos.TOP);
    grid.add(servicesPercent, 3, 2);

    return grid;
}
  
  public BorderPane makeGrid(int w, int h){

        BorderPane p = new BorderPane();
        double squareWidth = (w > h) ? width/w : height/h;
        Rectangle[][] rec = new Rectangle[h][w];
        
        for(int i=0; i<h; i++){
            for(int j=0; j<w; j++){
                rec[i][j] = new Rectangle();
                rec[i][j].setX(i * squareWidth);
                rec[i][j].setY(j * squareWidth);
                rec[i][j].setWidth(squareWidth);
                rec[i][j].setHeight(squareWidth);
                rec[i][j].setFill(null);
                rec[i][j].setStroke(Color.BLACK);
                p.getChildren().add(rec[i][j]);
            }
        }

        return p;
    }
  
  public static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
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
