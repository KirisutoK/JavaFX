// Creation Date: April 23, 2026. at 7:34 PM
// Last Modified: May 16, 2026. at  2:25 AM

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;

public class TestFX extends Application {

    @Override public void start(Stage stage) throws Exception {
        // ======== LAYOUTS (ROOT NODES) ======== \\
        Pane Pane01 = new Pane();
        Scene Scene01 = new Scene(Pane01);

        // ======== SCENE ======== \\

        // +[SOMETHING]+
        Image TestGirl = new Image("Backward01.png");
        ImageView TestGirlView = new ImageView(TestGirl);

        Timeline TestGirlViewWalking = new Timeline(
                new KeyFrame(
                        Duration.seconds(0.5),
                        new KeyValue(TestGirlView.translateXProperty(), 10),
                        new KeyValue(TestGirlView.translateYProperty(), 10)
                ),
                new KeyFrame(
                        Duration.seconds(1),
                        new KeyValue(TestGirlView.translateXProperty(), 0),
                        new KeyValue(TestGirlView.translateYProperty(), 0)
                        ),
                new KeyFrame(
                        Duration.seconds(1.5),
                        new KeyValue(TestGirlView.translateXProperty(), -20),
                        new KeyValue(TestGirlView.translateYProperty(), 10)
                ),
                new KeyFrame(
                        Duration.seconds(2),
                        new KeyValue(TestGirlView.translateXProperty(), 0),
                        new KeyValue(TestGirlView.translateYProperty(), 0)
                )
        );

        TestGirlViewWalking.setCycleCount(TestGirlViewWalking. INDEFINITE); // LOOP
        TestGirlViewWalking.play();


        // LOAD FILE
        //            String GameMenuBackgroundFilePath = getClass().getResource("Medias/Videos/GameMenuBackground.mp4").toExternalForm();
        //            Media GameMenuBackgroundMedia = new Media(GameMenuBackgroundFilePath); // Makes the File Path into a Media Object
        //            MediaPlayer GameMenuBackgroundMediaPlayer = new MediaPlayer(GameMenuBackgroundMedia); // Makes MediaPlayer for Media (ENABLING TO GIVE FUNCTIONALITY)
        //            GameMenuBackgroundMediaView = new MediaView(GameMenuBackgroundMediaPlayer); // Makes the MediaPlayer visible

        Pane01.getChildren().add(TestGirlView);

        // ======== STAGE ======== \\
        stage.setScene(Scene01);
        stage.setWidth(1100);
        stage.setHeight(800);
        stage.show(); // Displays the screen or box
    }

    public static void main(String[] args) {
        launch(args);
    }
}
