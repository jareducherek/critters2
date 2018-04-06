package assignment5;
import java.util.Optional;
import javafx.scene.shape.Rectangle;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import java.util.concurrent.atomic.AtomicBoolean;
import javafx.concurrent.Service;
import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;


public class Main extends Application {

    public static void main(String[] args) {
      launch(args);
    }

    public static final int width = 1000;
    public static final int height = 800;
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
    private ChoiceBox<String> critterChoices;
    private Button one;
    private Button five;
    private Button fifty;
    private String currentSelectedCritter = "Critter";
    private ArrayList<String> critterList = new ArrayList<String>() {{
        add("Critter");
    }};
    private BorderPane pane;
    public static GridPane modelGrid = new GridPane();
    private Service<Void> backgroundThread;
    
    @Override
    public void start(Stage stage) throws Exception {
        critterList.addAll(getCritters());
        CritterWorld.initialize();
        getCritters();
        Painter.setSize();
        Painter.paint();
        
        window = stage;
        window.setTitle("Critter World");
        pane = new BorderPane();

        pane.setBottom(addHBoxBot(currentSelectedCritter));
        pane.setTop(addHBoxTop());
        
        makeGrid();
        pane.setCenter(modelGrid);
 //       pane.setCenter(makeGrid(Params.world_height, Params.world_width));

        scene = new Scene(pane);
        window.setScene(scene);
        window.show();
    }

    private void startAnimation(ChoiceBox<Integer> anmSpeed){
    AtomicBoolean running = new AtomicBoolean(true);

    double tempHeight = pane.getTop().getLayoutBounds().getHeight();

    HBox hBoxTop = new HBox();
    hBoxTop.setPadding(new Insets(20, 12, 20, 12));
    hBoxTop.setSpacing(20);

    hBoxTop.setStyle("-fx-background-color: #336699;");
    stop = new Button("stop animation");
    
    hBoxTop.getChildren().addAll(stop);

    pane.setTop(hBoxTop);
    pane.getTop().prefHeight(tempHeight);
    
    Timer t = new Timer();

    Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {
            for(int i = 0; i < anmSpeed.getValue()-1; i++){
                Critter.worldTimeStep();
                Critter.worldFightStep();
            }
            Critter.worldTimeStep();
            Critter.worldFightStep();
            pane.setCenter(makeGrid(Params.world_height, Params.world_width));
            pane.setBottom(addHBoxBot(currentSelectedCritter));
        }
    }));
    stop.setOnAction(new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {
            fiveSecondsWonder.stop();
            pane.setTop(addHBoxTop());
            pane.setBottom(addHBoxBot(currentSelectedCritter));
        }
    });
    fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
    fiveSecondsWonder.play();

  }

    private void step(ChoiceBox<Integer> stepAmount){
        int steps = stepAmount.getValue();

        for(int i = 0; i < steps; i++){
            Critter.worldTimeStep();                        
            Critter.worldFightStep();
        }    
        pane.setCenter(makeGrid(Params.world_height, Params.world_width));
        pane.setBottom(addHBoxBot(currentSelectedCritter));

    }

    private void setSeed(){

      Dialog dialog = new TextInputDialog("Enter a seed: ");
      dialog.setTitle("Seed Entry Window");
      if(Critter.getSeed() == -1){
          dialog.setHeaderText("Seed is currently unset");
      } else {
        dialog.setHeaderText("Current seed: " + Critter.getSeed());
      }
      Optional<String> result = dialog.showAndWait();
      String entered = "none.";
      if (result.isPresent() && isLong(result.get())) {
          Critter.setSeed(Long.valueOf(result.get()));
          System.out.println("Current seed: " + Critter.getSeed());
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

        one = new Button("1");
        five = new Button("5");
        fifty = new Button("50");
        ChoiceBox<String> critterChoices = new ChoiceBox<>();
        
        for(String s : critterList){
            if(!s.equals("Critter")){
                critterChoices.getItems().add(s);
            }
        }
        
        anmSpeed.getItems().addAll(1,5,10,50);
        anmSpeed.setValue(1);
        stepAmount.getItems().addAll(1,5,10,50);
        stepAmount.setValue(1);
        
        start.setOnAction(e -> startAnimation(anmSpeed));
        seedButton.setOnAction(e -> setSeed());
        stepButton.setOnAction(e -> step(stepAmount));
        one.setOnAction(e ->{
            try {
                Critter.makeCritter(critterChoices.getValue(), 1);
                pane.setBottom(addHBoxBot(currentSelectedCritter));
                pane.setCenter(makeGrid(Params.world_height, Params.world_width));
            } catch (InvalidCritterException ex) {
                System.out.println("invalid critter creation attempt");
            }
        });
        five.setOnAction(e ->{
            try {
                Critter.makeCritter(critterChoices.getValue(), 5);
                pane.setBottom(addHBoxBot(currentSelectedCritter));
                pane.setCenter(makeGrid(Params.world_height, Params.world_width));
            } catch (InvalidCritterException ex) {
                System.out.println("invalid critter creation attempt");
            }
        });
        fifty.setOnAction(e ->{
            try {
                Critter.makeCritter(critterChoices.getValue(), 50);
                pane.setBottom(addHBoxBot(currentSelectedCritter));
                pane.setCenter(makeGrid(Params.world_height, Params.world_width));
            } catch (InvalidCritterException ex) {
                System.out.println("invalid critter creation attempt");
            }
        });
        
        
        VBox anm = new VBox(5); // 5 is the spacing between elements in the VBox
        anm.getChildren().addAll(start, stepButton);
        anm.setAlignment(Pos.CENTER_LEFT);

        VBox stepper = new VBox(5); // 5 is the spacing between elements in the VBox
        stepper.getChildren().addAll(anmSpeed, stepAmount);
        stepper.setAlignment(Pos.CENTER);
        
        HBox makeCritters = new HBox(5);
        makeCritters.getChildren().addAll(one, five, fifty, critterChoices);
        makeCritters.setAlignment(Pos.CENTER_RIGHT);
        
        VBox misc = new VBox(5); // 5 is the spacing between elements in the VBox
        misc.getChildren().addAll(seedButton, makeCritters);
        misc.setAlignment(Pos.CENTER_RIGHT);


        hBoxTop.getChildren().addAll(anm, stepper, misc);
        HBox.setHgrow(misc, Priority.ALWAYS);

        return hBoxTop;
  }

    private HBox addHBoxBot(String stats){
        currentSelectedCritter = stats;
        HBox hBoxBot = new HBox();
        hBoxBot.setPadding(new Insets(10));
        hBoxBot.setSpacing(8);
        hBoxBot.setStyle("-fx-background-color: #336699;");

        Text title = new Text("Stats");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        hBoxBot.getChildren().add(title);
        
        try {
          Text body = new Text(Critter.runStats(Critter.getInstances(stats)));
          body.setFont(Font.font("Arial", 14));
          hBoxBot.getChildren().add(body);
        } catch (InvalidCritterException ex) {
          System.out.println("Critter class no longer exists");
        } 
        
        getStats = new ChoiceBox<>();
        for(String s : critterList){
            getStats.getItems().add(s);
        }
        getStats.setValue(stats);
        getStats.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> pane.setBottom(addHBoxBot(newValue)));
        HBox misc = new HBox(5);
        misc.getChildren().addAll(getStats);
        misc.setAlignment(Pos.CENTER_RIGHT);
        
        hBoxBot.getChildren().add(misc);
        HBox.setHgrow(misc, Priority.ALWAYS);

        return hBoxBot;
  }
    
    public static void makeGrid(){
        for(int y=0; y<Params.world_height; y++){
              for(int x=0; x<Params.world_width; x++){
                  if(CritterWorld.crittersArr[y][x] == null){
                      Painter.paintCritter(x, y, Critter.CritterShape.SQUARE, Color.WHITE, Color.BLACK, Color.WHITE);
                  } else {
                    Painter.paintCritter(x, y, CritterWorld.crittersArr[y][x].viewShape(), CritterWorld.crittersArr[y][x].viewColor(), 
                            CritterWorld.crittersArr[y][x].viewOutlineColor(), CritterWorld.crittersArr[y][x].viewFillColor());
                  }
              }
        }
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
                  
                  if (CritterWorld.critterGrid[i][j].equals("1")) {
                      rec[i][j].setStroke(CritterWorld.crittersArr[i][j].viewColor());
                      text[i][j] = new Text("♆");
                  } else if (CritterWorld.critterGrid[i][j].equals("2")) {
                      rec[i][j].setStroke(CritterWorld.crittersArr[i][j].viewColor());
                      text[i][j] = new Text("❆"); 
                  } else if (CritterWorld.critterGrid[i][j].equals("3")) {
                      rec[i][j].setStroke(CritterWorld.crittersArr[i][j].viewColor());
                      text[i][j] = new Text("ↂ"); 
                  } else if (CritterWorld.critterGrid[i][j].equals("4")) {
                      rec[i][j].setStroke(CritterWorld.crittersArr[i][j].viewColor());
                      text[i][j] = new Text("☀"); 
                  } else if (CritterWorld.critterGrid[i][j].equals("@")) {
                      text[i][j] = new Text("♣");
                  } else if (CritterWorld.critterGrid[i][j].equals("C")) {
                      text[i][j] = new Text("❀");
                  }
                                  
                  s.getChildren().addAll(rec[i][j],text[i][j]);
                  p.add(s, j, i);

              }
          }
          
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
                
                if(CritterWorld.occupied[i][j] == 2){
                    rec[i][j].setFill(Color.RED);
                    text[i][j] = new Text(" ");
                }
                else if(CritterWorld.occupied[i][j] > 2){
                    rec[i][j].setFill(Color.DARKRED);
                    text[i][j] = new Text(" ");
                } else {
                    text[i][j] = new Text(CritterWorld.critterGrid[i][j]);
                }
                
                s.getChildren().addAll(rec[i][j],text[i][j]);
                p.add(s, j, i);
                
            }
        }
        return p;
    }

    public static boolean isLong(String str) {
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
    
    public static ArrayList<String> getCritters(){
        ArrayList<String> critterNames = new ArrayList<>();
        
        String path = "./src/assignment5";
        File f = new File(path);
        File[] fileArray = f.listFiles();
        for(File names : fileArray){
            if(names.getName().contains(".java") && !names.getName().equals("Main.java") && !names.getName().equals("Params.java")
                    && !names.getName().equals("InvalidCritterException.java") && !names.getName().equals("Header.java") && !names.getName().equals("CritterWorld.java") &&
                    !names.getName().equals("Critter.java")){
                critterNames.add(names.getName().split("\\.")[0]);
            }
            
        }
        return critterNames;
    }

    
}
