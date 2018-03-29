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
import java.util.concurrent.atomic.AtomicBoolean;

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
            
//            pane.getBottom().getLayoutBounds().getHeight();
//            System.out.println(pane.getTop().getLayoutBounds().getHeight());
//            System.out.println(pane.getCenter().getLayoutBounds().getHeight());
            
            CritterWorld.initialize();
            pane.setCenter(makeGrid(Params.world_height, Params.world_width));
            
//            BorderPane.setAlignment(pane, Pos.CENTER_RIGHT);
            
            getStats = new ChoiceBox<>();
            getStats.getSelectionModel().selectedItemProperty().addListener((v, oldItem, newItem) -> getStats(newItem));
            

            
            

//            layout.getChildren().addAll(stop);
//            layout.getChildren().addAll(setSeed);
//            layout.getChildren().addAll(makeCritters);
            
            scene = new Scene(pane);
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
//    AtomicBoolean running = new AtomicBoolean(true);
    
    boolean running = true;
    double tempHeight = pane.getTop().getLayoutBounds().getHeight();
    
    HBox hBoxTop = new HBox();
    hBoxTop.setPadding(new Insets(20, 12, 20, 12));
    hBoxTop.setSpacing(20);
    
    hBoxTop.setStyle("-fx-background-color: #336699;");
    stop = new Button("stop animation");
    //stop.setOnAction(e -> running.compareAndSet(true, false));
    hBoxTop.getChildren().addAll(stop);
    
    pane.setTop(hBoxTop);
    pane.getTop().prefHeight(tempHeight);
    
    
//    while(running.get()){
//        for(int i = 0; i < anmSpeed.getValue() && running.get(); i++){
//            Critter.worldTimeStep();
//            pane.setCenter(makeFightGrid(Params.world_height, Params.world_width));
//            Critter.worldFightStep();
//            pane.setCenter(makeGrid(Params.world_height, Params.world_width));
//        }
//    }
    
    while(running){
//        for(int i = 0; i < anmSpeed.getValue(); i++){
//            Critter.worldTimeStep();
//            pane.setCenter(makeFightGrid(Params.world_height, Params.world_width));
//            Critter.worldFightStep();
//            pane.setCenter(makeGrid(Params.world_height, Params.world_width));
//        }
    }
    
    //pane.setTop(addHBoxTop());
      
  }
  
  private void step(ChoiceBox<Integer> stepAmount){
      int steps = stepAmount.getValue();
      
      for(int i = 0; i < steps; i++){
            Critter.worldTimeStep();
      }
      
      pane.setCenter(makeGrid(Params.world_height, Params.world_width));
      
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
    stepButton = new Button("step");
    seedButton = new Button("Set Seed");
    makeCritters = new Button("Create Critters");
    
    anmSpeed.getItems().addAll(1,5,10,50);
    anmSpeed.setValue(1);
    stepAmount.getItems().addAll(1,5,10,50);
    stepAmount.setValue(1);
    
    start.setOnAction(e -> startAnimation(anmSpeed));
    seedButton.setOnAction(e -> setSeed());
    makeCritters.setOnAction(e -> makeCritters());
    stepButton.setOnAction(e -> step(stepAmount));
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
  
  public static GridPane makeGrid(int h, int w){

        GridPane p = new GridPane();
        p.setVgap(0);
        p.setHgap(0);
        
        double squareWidth = ((width)/w < (height)/h) ? (width)/w : (height)/h;
        
        Text[][] text = new Text[h][w];
        Rectangle[][] rec = new Rectangle[h][w];
        StackPane s;
              
        
        for(int i=0; i<h; i++){
            for(int j=0; j<w; j++){
                s = new StackPane();
                s.setPadding(new Insets(0,0,0,0));
                rec[i][j] = new Rectangle();
                rec[i][j].setX(j * squareWidth);
                rec[i][j].setY(i * squareWidth);
                rec[i][j].setWidth(squareWidth);
                rec[i][j].setHeight(squareWidth);
                rec[i][j].setFill(null);
                rec[i][j].setStroke(Color.BLACK);
                                
                text[i][j] = new Text(CritterWorld.critterGrid[i][j]);
                s.getChildren().addAll(rec[i][j],text[i][j]);
                
                p.add(s, j, i);
                
            }
        }
        
        
//        Text text = new Text("$");
//        Rectangle rec = new Rectangle();
//        StackPane stack = new StackPane();
//        
//        stack.getChildren().addAll(rec,text);
//        p.getChildren().add(stack);

        return p;
    }
  
  public static GridPane makeFightGrid(int h, int w){

        GridPane p = new GridPane();
        p.setVgap(0);
        p.setHgap(0);
        
        double squareWidth = ((width)/w < (height)/h) ? (width)/w : (height)/h;
        
        Text[][] text = new Text[h][w];
        Rectangle[][] rec = new Rectangle[h][w];
        StackPane s;
              
        
        for(int i=0; i<h; i++){
            for(int j=0; j<w; j++){
                s = new StackPane();
                s.setPadding(new Insets(0,0,0,0));
                rec[i][j] = new Rectangle();
                rec[i][j].setX(j * squareWidth);
                rec[i][j].setY(i * squareWidth);
                rec[i][j].setWidth(squareWidth);
                rec[i][j].setHeight(squareWidth);
                rec[i][j].setFill(null);
                rec[i][j].setStroke(Color.BLACK);
                
                //to be updated...
                if(CritterWorld.occupied[i][j] == 2){
                    text[i][j] = new Text(CritterWorld.critterGrid[i][j]);
                }
                else if(CritterWorld.occupied[i][j] > 2){
                    text[i][j] = new Text(CritterWorld.critterGrid[i][j]);
                } else {
                    text[i][j] = new Text(CritterWorld.critterGrid[i][j]);
                }
                s.getChildren().addAll(rec[i][j],text[i][j]);
                
                p.add(s, j, i);
                
            }
        }
        
        
//        Text text = new Text("$");
//        Rectangle rec = new Rectangle();
//        StackPane stack = new StackPane();
//        
//        stack.getChildren().addAll(rec,text);
//        p.getChildren().add(stack);

        return p;
    }
  
}
