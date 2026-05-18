// Creation Date: April 18, 2026. at 2:00 AM
// Last Modified: April 19, 2026. at  9:25 PM

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args); // THIS DOES THE BACKEND STUFF (IN ORDER TO START THE APPLICATION)
    }

    // --- @OVERRIDE ---
    @Override public void start(Stage stage) throws Exception { // THIS IS LIKE THE SCREEN OR BOX TO PUT STUFF (THIS STARTS THE APPLICATION)
        // ======== NODES ======== \\
        Group root = new Group(); // This a root node (A layout manager for children nodes i.e visual elements)
        Scene scene = new Scene(root, Color.GREEN); // THIS CREATES A SCENE (A BOX);
        Image icon = new Image("WUWA_Encore.png"); // CREATES AN IMAGE

        // ======== SCENE ======== \\
        stage.setScene(scene); // this sets the scene

        stage.getIcons().add(icon); // Sets the icon
        stage.setTitle("ENCORE ON THE WAY!!!!!!!!!!!"); // this changes the title of the screen
        stage.setWidth(500); // Changes the width [When Showing]
        stage.setHeight(500); // Changes the height [When Showing]
        stage.setResizable(false); // Sets the size to be fixed (unadjustable)
        // stage.setY(50); // Changes the position of the Screen (y-axis) [When Showing]
        // stage.setX(50); // Changes the position of the Screen (x-axis) [When Showing]

        // +[FULLSCREEN]+
        stage.setFullScreen(true); // Makes the Stage fullscreen
        stage.setFullScreenExitHint("Press Esc Bro"); // gives a hint when on fullscreen
        stage.setFullScreenExitKeyCombination(KeyCombination.valueOf("B")); // changes the keybind for exiting fullscreen

        // ======== STAGE ======== \\
        stage.show(); // Displays the screen or box
    }
}