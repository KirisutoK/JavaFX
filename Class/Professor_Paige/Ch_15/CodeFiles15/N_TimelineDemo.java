package chapter15;

import javafx.animation.Animation;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class N_TimelineDemo extends Application {
  private int count = 0;
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    StackPane pane = new StackPane();
    Text text = new Text(20, 50, "Programming is fun");
    text.setFill(Color.RED);
    pane.getChildren().add(text); // Place text into the stack pane


    // Create an animation for alternating text
    Timeline animation = new Timeline(new KeyFrame(Duration.millis(500), e -> blink(text)));
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.play(); // Start animation

    // Pause and resume animation
    text.setOnMouseClicked(e -> {
      if (animation.getStatus() == Animation.Status.PAUSED) {
        animation.play();
      }
      else {
        animation.pause();
      }
    });
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 250, 50);
    primaryStage.setTitle("TimelineDemo"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
  }

  private void blink(Text text) {
    if (text.getText().length() != 0) {
      text.setText("");
    }
    else {
      count++;
      text.setText("Programming is fun " + count);
    }
  }

  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
