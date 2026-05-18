package ch16audioproject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import java.net.URL;

public class SoundApp extends Application {

    @Override
    public void start(Stage primaryStage) {

        // 1. Create the "End Game" Button
        Button btn = new Button("Click to End Game");

        // 2. Load the Sound
        // This looks for the file in the same folder as this file (SoundApp)
        URL resource = getClass().getResource("game-over.wav");

        if (resource == null) {
            System.out.println("Error: Could not find game-over.wav");
        } else {
            AudioClip clip = new AudioClip(resource.toExternalForm());

            // 3. Set the button action
            btn.setOnAction(event -> {
                if (!clip.isPlaying()) { //... THIS IS TO STOP MAKING THE PLAY SPAM
                    clip.play();
                }
            });
        }

        // 4. Set up the layout & scene
        StackPane root = new StackPane();
        root.getChildren().add(btn);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("JavaFX Button and Sound Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}