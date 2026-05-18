// Creation Date: April 19, 2026. at 2:48 PM
// Last Modified: April 19, 2026. at  9:29 PM

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    // --- OVERRIDE ---
    @Override public void start(Stage primaryStage) throws Exception {
        // ======== NODES ======== \\
        Group root = new Group(); // A Root Node (Layout)
        Scene scene = new Scene(root, 500, 500, Color.GREEN); // Creates a Scene [Root Node, SizeX, SizeY, Color]
        Stage stage = new Stage(); // Creates a Stage

        // +[TEXT01]+
        Text Text01 = new Text(); // THIS IS A NODE (A TEXT)
        Text01.setText("TESTTESTTEST"); // THIS SETS THE TEXT
        Text01.setX(50); // THIS SETS THE POSITION (x-axis)
        Text01.setY(50); // THIS SETS THE POSITION (y-axis)
        Text01.setFont(Font.font("Times New Roman", 50)); // THIS CHANGES THE FONT AND SIZE
        Text01.setFill(Color.BLUE); // THIS CHANGES THE FONT COLOR

        // +[TEXT02]+
        Text Text02 = new Text(20, 50,"Start(20, 50)"); // THIS IS A NODE (A TEXT)
        // Position(20, 50)

        // +[LINE01]+
        Line Line01 = new Line(); // THIS IS A NODE (A LINE)
        Line01.setStartX(0);
        Line01.setStartY(0);
        Line01.setEndX(500);
        Line01.setEndY(200);
        Line01.setStroke(Color.WHITE); // THIS CHANGES THE LINE COLOR
        Line01.setStrokeWidth(5); // THIS CHANGES THE WIDTH SIZE OF THE LINE
        Line01.setOpacity(0.5); // THIS CHANGES HOW VISIBLE THE LINE IS
        // Line01.setRotate(45); // THIS ROTATES THE LINE
        
        // +[LINE02]+
        Line Line02 = new Line(20, 50, 0, 0); // THIS IS A NODE (A LINE)
        Line02.setStroke(Color.RED);
        // Start(500, 200) -> End(0, 0)

        // +[RECTANGLE01]+
        Rectangle Rectangle01 = new Rectangle(50, 50); // THIS IS A NODE (A RECTANGLE) [Width, Height, Color]
        Rectangle01.setX(300);
        Rectangle01.setY(200);
        Rectangle01.setStroke(Color.PINK);
        Rectangle01.setStrokeWidth(5); // THIS ADDS AN OUTLINE

        // +[TRIANGLE01]+
        Polygon Triangle01 = new Polygon(); // THIS IS A NODE (A POLYGON) [Width, Height, Color]
        Triangle01.getPoints().setAll( // THIS SETS WHERE THE POINTS ARE FOR THE SHAPE
                200.0, 200.0, // FIRST POINT
                300.0, 300.0, // SECOND POINT
                200.0, 300.0 // THIRD POINT
                );
        Triangle01.setFill(Color.YELLOW);
        Triangle01.setStrokeWidth(4);
        Triangle01.setStroke(Color.BLUE);

        // +[CIRCLE01]+
        Circle Circle01 = new Circle(70, 90, 30, Color.VIOLET); // THIS IS A NODE (A CIRCLE) [x-axis, y-axis, Radius ,Color]

        // +[IMAGE01]+
        Image Image01 = new Image("WUWA_Encore.png"); // THIS UPLOADS THE IMAGE
        ImageView ImageView01 = new ImageView(Image01); // THIS VIEWS THE IMAGE
        ImageView01.setX(10); // SETS THE POSITION (X-AXIS)
        ImageView01.setY(50); // SETS THE POSITION (Y-AXIS)

        // ======== SCENE ======== \\
        root.getChildren().add(ImageView01); // THIS ADDS A NODE TO THE ROOT NODE
        root.getChildren().add(Circle01); // THIS ADDS A NODE TO THE ROOT NODE
        root.getChildren().add(Rectangle01); // THIS ADDS A NODE TO THE ROOT NODE
        root.getChildren().add(Triangle01); // THIS ADDS A NODE TO THE ROOT NODE
        root.getChildren().add(Line02); // THIS ADDS A NODE TO THE ROOT NODE
        root.getChildren().add(Line01); // THIS ADDS A NODE TO THE ROOT NODE
        root.getChildren().add(Text02); // THIS ADDS A NODE TO THE ROOT NODE
        root.getChildren().add(Text01); // THIS ADDS A NODE TO THE ROOT NODE
        stage.setScene(scene);

        // ======== STAGE ======== \\
        stage.show();
    }
}
