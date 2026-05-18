// Creation Date: April 23, 2026. at 10:29 AM
// Last Modified: April 23, 2026. at 11:14 AM

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.Random;
import java.util.SortedMap;

public class Chapter14Assignment extends Application {

    @Override public void start(Stage stage) throws Exception { // THIS IS LIKE THE SCREEN OR BOX TO PUT STUFF (THIS STARTS THE APPLICATION)
        Random random = new Random();

        // ======== NODES ======== \\
        Pane Pane01 = new Pane();
        Scene Scene01 = new Scene(Pane01);

        // ======== SCENE ======== \\

        // +[IMAGE01]+
        int RandomNumber01 = random.nextInt(52)+1;
        Image Image01 = new Image("image/card/"+RandomNumber01+".png");
        ImageView ImageView01 = new ImageView(Image01);
        ImageView01.setX(0);
        ImageView01.setY(0);
        ImageView01.setFitWidth(230);
        ImageView01.setFitHeight(250);
        Pane01.getChildren().add(ImageView01);

        // +[IMAGE02]+
        int RandomNumber02 = random.nextInt(52)+1;
        Image Image02 = new Image("image/card/"+RandomNumber02+".png");
        ImageView ImageView02 = new ImageView(Image02);
        ImageView02.setX(235);
        ImageView02.setY(0);
        ImageView02.setFitWidth(230);
        ImageView02.setFitHeight(250);
        Pane01.getChildren().add(ImageView02);

        // +[IMAGE03]+
        int RandomNumber03 = random.nextInt(52)+1;
        Image Image03 = new Image("image/card/"+RandomNumber03+".png");
        ImageView ImageView03 = new ImageView(Image03);
        ImageView03.setX(470);
        ImageView03.setY(0);
        ImageView03.setFitWidth(230);
        ImageView03.setFitHeight(250);
        Pane01.getChildren().add(ImageView03);

        // ======== STAGE ======== \\
        stage.setScene(Scene01);
        stage.setTitle("Chapter 14 Programming Assignment #1");
        stage.setHeight(300);
        stage.setWidth(700);
        stage.setResizable(false);
        stage.show(); // Displays the screen or box
    }

    public static void main(String[] args) {
        launch(args);
    }
}
