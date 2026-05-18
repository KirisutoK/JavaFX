package Week12;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;

/**
 * @author Carrie Brewer
 * @version 11-18-25
 * Sample code - Ch. 14 JavaFX practice
 */

public class FaceCards extends Application {

    /**
     *
     * @param stage: holds graphics
     * @throws Exception
     * Displays graphics
     */
    @Override
    public void start(Stage stage) throws Exception {

        Scene scene = new Scene(getScene(), 300, 300);
        stage.setScene(scene);
        stage.show();
    }

    /**
     *
     * @return pane containing a face and images
     */
    private Pane getScene() {
        BorderPane mainPane = new BorderPane();
        mainPane.setLeft(getFace());
        mainPane.setRight(getRightPanel());
        return mainPane;
    }

    /**
     *
     * @return VBox with two cards and a flag
     */
    private VBox getRightPanel() {
        VBox panel = new VBox();

        for(int i = 0; i < 2; i++) {
            int randNum = (int) (Math.random() * 52) + 1;
            Image image = new Image("file:image/card/" + randNum + ".png");
            panel.getChildren().add(new ImageView(image));
        }

        Image image = new Image("file:image/us.gif");
        panel.getChildren().add(new ImageView(image));

        return panel;
    }

    /**
     *
     * @return pane with a face drawn
     */
    private Pane getFace() {
        Pane facePane = new Pane();
        Circle face = new Circle(100, 100, 100);
        face.setFill(Color.BLUE);
        face.setStroke(Color.BLACK);  // outline

        Ellipse leftEye = new Ellipse(60, 80, 20, 15);
        leftEye.setFill(Color.GREEN);
        leftEye.setStroke(Color.BLACK);

        Ellipse rightEye = new Ellipse(140, 80, 20, 15);
        rightEye.setFill(Color.GREEN);
        rightEye.setStroke(Color.BLACK);

        facePane.getChildren().addAll(face, leftEye, rightEye);
        return facePane;
    }
}
