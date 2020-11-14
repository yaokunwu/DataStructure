/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication5;

import java.time.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 *
 * @author ozbirn
 */
public class TimelineDemo extends Application {
    Line line1;
    Line line2;
    
    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        EventHandler<ActionEvent> handler = e ->  {
             
            root.getChildren().remove(line1);
    
        };
  
        Timeline tm = new Timeline(new KeyFrame(javafx.util.Duration.seconds(1), handler));
        
        Button btn = new Button();
        btn.setText("Draw Line");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
             
                       line1 = new Line(10, 125, 50, 125);
                              
                       root.getChildren().add(line1);
                       tm.play();                    
            }
        });
        
        root.setRight(btn);
        root.setAlignment(btn,Pos.CENTER);
 
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}