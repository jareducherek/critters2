package assignment5;
import java.awt.Image;
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
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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

            pane.setTop(addHBox());
            pane.setBottom(addVBox());
//            addStackPane(hbox);         // Add stack to HBox in top region
            pane.setCenter(addGridPane());
            
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
      stepAmount.getValue();
      
      
      
      
  }
  private void setSeed(){
      
  }
  private void getStats(String critter){
      
  }
  
  private HBox addHBox() {
    HBox hbox = new HBox();
    hbox.setPadding(new Insets(20, 12, 20, 12));
    hbox.setSpacing(20);
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
  
  private VBox addVBox(){
    VBox vbox = new VBox();
    vbox.setPadding(new Insets(10));
    vbox.setSpacing(8);

    Text title = new Text("Data");
    title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
    vbox.getChildren().add(title);



    return vbox;
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
    
    Pane gridSquares = makeGrid(20,20);
    grid.add(gridSquares,0,0);
    

    return grid;
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
  
  public static Pane makeGrid(int h, int w){

        Pane p = new Pane();

        double squareWidth = width/w;
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
}
