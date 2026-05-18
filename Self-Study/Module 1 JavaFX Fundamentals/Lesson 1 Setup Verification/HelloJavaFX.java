// Creation Date: April 20, 2026. at 10:58 PM
// Last Modified: April 25, 2026. at  1:06 AM

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class HelloJavaFX extends Application {

    @Override
    public void start(Stage PrimaryStage) {
        Label label = new Label("JavaFX is working!");

        StackPane root = new StackPane();
        root.getChildren().add(label);

        Scene scene = new Scene(root, 300, 200);

        PrimaryStage.setTitle("Setup Firefly");
        PrimaryStage.setScene(scene);
        PrimaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

// THIS IS A TEST CODE MADE BY PROFESSOR CLAUDE IN ORDER TO DETERMINE IF THE JAVAFX SETUP ACTUALLY WORKS OR NOT
