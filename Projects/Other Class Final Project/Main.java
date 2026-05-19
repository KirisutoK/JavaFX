// Creation Date: May 18, 2026. at 11:20 PM
// Last Modified: May 18, 2026. at 11:33 PM

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Main extends Application {
    // ======== LAYOUTS (ROOT NODES) ======== \\
    Pane Pane01 = new Pane();
    Scene Scene01 = new Scene(Pane01);

    // ======== METHODS ========= \\
    private void FirstPhase() {
        
        Button ClickMe = new Button("Click Me");
        Pane01.getChildren().add(ClickMe);
    }

    // ======== OTHER METHODS ========= \\
    public static void main(String[] args) {
        launch(args);
    }
    @Override public void start(Stage stage) throws Exception {
        // ======== LAYOUTS (ROOT NODES) ======== \\


        // ======== OBJECTS (NODES) ======== \\

        // +[STUFF]+
        FirstPhase();

        // ======== STAGE<SCENE ======== \\
        stage.setScene(Scene01);
        stage.setWidth(1100);
        stage.setHeight(800);
        stage.setTitle("I did this all in one single night");
        stage.setResizable(false);
        stage.show(); // Displays the screen or box
    }
}
