// Creation Date: April 29, 2026. at 11:17 PM
// Last Modified: April 29, 2026. at 11:31 PM

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // ======== ROOT NODES ======== \\
        // +[LAYOUTS]+
        StackPane WelcomeLayout = new StackPane();
        StackPane InfoLayout = new StackPane();

        // +[SCENES]+
        Scene WelcomeScene = new Scene(WelcomeLayout, 400, 300); // Root, Height, Width
        Scene InfoScene = new Scene(InfoLayout, 400, 300); // Root, Height, Width

        // ======== STUFF (NODES) ======== \\
        // +[SCENE01]+
        // Welcome Label
        Label WelcomeLabel = new Label("Welcome to JavaFX Learning!"); 
        WelcomeLayout.getChildren().add(WelcomeLabel);

        // Button
        Button GoToInfoButton = new Button("Go to Info Screen");
        WelcomeLayout.getChildren().add(GoToInfoButton);
        GoToInfoButton.setOnAction(e -> stage.setScene(InfoScene));

        // +[SCENE02]+
        // Information Label
        Label InfoLabel = new Label("This is the Info Screen");
        InfoLayout.getChildren().add(InfoLabel);

        // Button
        Button GoBackButton = new Button("Go back to Welcome");
        InfoLayout.getChildren().add(GoBackButton);
        GoBackButton.setOnAction(e -> stage.setScene(WelcomeScene));
        

        // ======== STAGE>SCENE ======== \\
        stage.setTitle("Scene Switcher Demo");
        stage.setScene(WelcomeScene);
        stage.show(); // Displays the screen or box
    }

    // ======== METHODS ========= \\
    public static void main (String[] args) {
        launch(args);
    }
}
