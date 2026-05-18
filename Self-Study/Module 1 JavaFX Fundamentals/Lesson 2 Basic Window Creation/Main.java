// Creation Date: April 20, 2026. at 11:14 PM
// Last Modified: April 29, 2026. at 11:16 PM

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args); // Always need this to run the program
    }

    // --- @OVERRIDE ---
    @Override public void start(Stage stage) throws Exception {
        // ======== ROOT NODE ======== \\
        StackPane StackPane01 = new StackPane();
        Scene Scene01 = new Scene(StackPane01, 500, 400); // Root(Layout), Width, Height

        // ======== NODES ======== \\
        Label nameLabel = new Label("Student Name: Christ Aerjil Dampog");
        Label collegeLabel = new Label("College: Finger Lakes Community College");
        Label majorLabel = new Label("Major: Computer Information Systems");
        Label yearLabel = new Label("Year: Freshman");

        // ======== SCENE ======== \\
        StackPane01.getChildren().addAll(nameLabel, collegeLabel, majorLabel, yearLabel);
        // +[STUFF]+

        // ======== STAGE ======== \\
        stage.setScene(Scene01);
        stage.show(); // Displays the screen or box
    }
}
