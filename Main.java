package assignment5;
import static com.sun.javafx.css.FontFace.FontFaceSrcType.URL;
import java.util.Optional;
import javafx.scene.shape.Rectangle;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;
import javafx.concurrent.Service;
import java.awt.TextArea;
import java.io.File;
import java.io.IOException;
import java.net.URLClassLoader;
//import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.animation.AnimationTimer;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ChoiceDialog;
import javafx.stage.WindowEvent;
import javax.print.DocFlavor.URL;
//import static javafx.scene.input.DataFormat.URL;


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
    private ChoiceBox<String> critterChoices;
    private Button one;
    private Button five;
    private Button fifty;
    private String currentSelectedCritter = "Critter";
    private ArrayList<String> critterList = new ArrayList<String>() {{
        add("Critter");
    }};
    BorderPane pane;
    private Service<Void> backgroundThread;
    
    @Override
    public void start(Stage stage) throws Exception {
        critterList.addAll(getCritters());
        CritterWorld.initialize();
        getCritters();
        
        window = stage;
        window.setTitle("Critter World");
        pane = new BorderPane();

        pane.setBottom(addHBoxBot(currentSelectedCritter));
        pane.setTop(addHBoxTop());
        
        getStats.getValue();
        pane.setCenter(makeGrid(Params.world_height, Params.world_width));

        getStats = new ChoiceBox<>();
        getStats.getSelectionModel().selectedItemProperty().addListener((v, oldItem, newItem) -> getStats(newItem));

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
    stop.setOnAction(e -> running.compareAndSet(true, false));
    hBoxTop.getChildren().addAll(stop);

    pane.setTop(hBoxTop);
    pane.getTop().prefHeight(tempHeight);
    
    Timer t = new Timer();

    t.scheduleAtFixedRate(
        new TimerTask()
        {
            public void run()
            {
                for(int i = 0; i < anmSpeed.getValue()-1; i++){
                    Critter.worldTimeStep();
                    Critter.worldFightStep();
                }
                Critter.worldTimeStep();
                pane.setCenter(makeGrid(Params.world_height, Params.world_width));
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                Critter.worldFightStep();
                pane.setCenter(makeGrid(Params.world_height, Params.world_width));
                System.out.println("3 seconds passed");
                
            }
        },
        0,      // run first occurrence immediately
        5000);
//    backgroundThread = new Service<Void>() {
//        @Override
//        protected Task<Void> createTask() {
//            return new Task<Void>(){
//            
//                @Override
//                protected Void call() throws Exception{
//                    while(running.get()){
//                        if(anmSpeed.getValue()==1){                           
//                            Critter.worldTimeStep();
//                            System.out.println("update fight anim");    
//                            TimeUnit.SECONDS.sleep(1);
//    //                        pane.setCenter(makeGrid(Params.world_height, Params.world_width));
//                            Critter.worldFightStep();
//                            System.out.println("update walk");
//                            TimeUnit.SECONDS.sleep(1);
//    //                        pane.setCenter(makeGrid(Params.world_height, Params.world_width));
//                        } else {
//                            for(int i = 0; i < anmSpeed.getValue(); i++){
//                                Critter.worldTimeStep();
//                                Critter.worldFightStep();
//                            }
//                            Critter.worldTimeStep();
//                            System.out.println("update fight anim");    
//                            TimeUnit.SECONDS.sleep(1);
// //                           pane.setCenter(makeFightGrid(Params.world_height, Params.world_width));
//                            Critter.worldFightStep();
//                            System.out.println("update walk");
//                            TimeUnit.SECONDS.sleep(1);
//   //                         pane.setCenter(makeGrid(Params.world_height, Params.world_width));
//                        }
//                    }
//                    
//                    return null;
//                }
//            };  
//        }
//    };    
//    backgroundThread.setOnSucceeded((WorkerStateEvent t) -> {
//        pane.setTop(addHBoxTop());
//        System.out.println("Done!");
//    });
//    
//    backgroundThread.restart(); 
    

  }

    private void step(ChoiceBox<Integer> stepAmount){
        int steps = stepAmount.getValue();

        for(int i = 0; i < steps; i++){
              Critter.worldTimeStep();
              Critter.worldFightStep();
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
            } catch (InvalidCritterException ex) {
                System.out.println("invalid critter creation attempt");
            }
        });
        five.setOnAction(e ->{
            try {
                Critter.makeCritter(critterChoices.getValue(), 5);
                pane.setBottom(addHBoxBot(currentSelectedCritter));
            } catch (InvalidCritterException ex) {
                System.out.println("invalid critter creation attempt");
            }
        });
        fifty.setOnAction(e ->{
            try {
                Critter.makeCritter(critterChoices.getValue(), 50);
                pane.setBottom(addHBoxBot(currentSelectedCritter));
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
        getStats.getItems().addAll(critterList);
        getStats.setValue(stats);
        getStats.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> pane.setBottom(addHBoxBot(newValue)));
        HBox misc = new HBox(5);
        misc.getChildren().addAll(getStats);
        misc.setAlignment(Pos.CENTER_RIGHT);
        
        hBoxBot.getChildren().add(misc);
        HBox.setHgrow(misc, Priority.ALWAYS);

        return hBoxBot;
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
    
    public static ArrayList<String> getCritters(){
        ArrayList<String> critterNames = new ArrayList<>();
        
        String path = "./src/assignment5";
        File f = new File(path);
        File[] fileArray = f.listFiles();
        for(File names : fileArray){
            if(names.getName().contains(".java") && !names.getName().equals("Main.java") && !names.getName().equals("Params.java")
                    && !names.getName().equals("InvalidCritterException.java") && !names.getName().equals("Header.java") && !names.getName().equals("CritterWorld.java")){
                critterNames.add(names.getName().split("\\.")[0]);
            }
            
        }
        return critterNames;
    }

    public class AnimationHandler extends Application {
      @Override
      public void start(Stage primaryStage) {

          final BlockingQueue<String> messageQueue = new ArrayBlockingQueue<>(1);

          TextArea console = new TextArea();

          Button startButton = new Button("Start");
          startButton.setOnAction(event -> {
              MessageProducer producer = new MessageProducer(messageQueue);
              Thread t = new Thread(producer);
              t.setDaemon(true);
              t.start();
          });

          final LongProperty lastUpdate = new SimpleLongProperty();

          final long minUpdateInterval = 0 ; // nanoseconds. Set to higher number to slow output.

          AnimationTimer timer = new AnimationTimer() {

              @Override
              public void handle(long now) {
                  if (now - lastUpdate.get() > minUpdateInterval) {
                      final String message = messageQueue.poll();
                      if (message != null) {
                          console.appendText("\n" + message);
                      }
                      lastUpdate.set(now);
                  }
              }

          };

          timer.start();

          HBox controls = new HBox(5, startButton);
          controls.setPadding(new Insets(10));
          controls.setAlignment(Pos.CENTER);

  //        BorderPane root = new BorderPane(console, null, null, controls, null);
     //     Scene scene = new Scene(root,600,400);
          primaryStage.setScene(scene);
          primaryStage.show();
      }

      private class MessageProducer implements Runnable {
          private final BlockingQueue<String> messageQueue ;

          public MessageProducer(BlockingQueue<String> messageQueue) {
              this.messageQueue = messageQueue ;
          }

          @Override
          public void run() {
              long messageCount = 0 ;
              try {
                  while (true) {
                      final String message = "Message " + (++messageCount);
                      messageQueue.put(message);
                  }
              } catch (InterruptedException exc) {
                  System.out.println("Message producer interrupted: exiting.");
              }
          }
      }

    }
}
