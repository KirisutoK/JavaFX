// Creation Date: April 23, 2026. at 11:17 AM
// Last Modified: April 23, 2026. at 12:04 PM

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Chapter14Assignment_2 extends Application {
    @Override public void start(Stage stage) throws Exception {
        // ======== NODES ======== \\
        GridPane GridPane01 = new GridPane();
        Scene Scene01 = new Scene(GridPane01);

        // ======== SCENE ======== \\

        // +[SQUARES]+
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                Rectangle Square = new Rectangle(50, 50);

                // Black (Odd) and White (Even)
                if ((row + column) % 2 == 0) { // if it's divided by 2 and result is not a decimal
                    Square.setFill(Color.WHITE);
                } else {
                    Square.setFill(Color.BLACK);
                }

                GridPane01.add(Square, column, row);  // Add to grid at (col, row)
            }
        }

        // ======== STAGE ======== \\
        stage.setScene(Scene01);
        stage.setTitle("Chapter 14 Assignment #2");
        stage.setHeight(440);
        stage.setWidth(415);
        stage.setResizable(false);
        stage.show();
    }

    public static void main (String[] args) {
        launch(args);
    }
}
