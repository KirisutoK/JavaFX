package ch16audioproject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import javafx.geometry.Pos;

//NOTE - check out examples of citations for audio & an image below
//NOTE - replace the sounds that are not working!

public class SoundPracticeApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // 1. Load audio clips
        //"mixkit-atm-cash-machine-key-press-2841" from https://mixkit.co/free-sound-effects/keyboard/
        AudioClip atm = loadSound("atm.wav");
        //"mixkit-cartoon-voice-laugh-343" from https://mixkit.co/free-sound-effects/laugh/
        AudioClip laugh = loadSound("mixkit-beautiful-dream-493.mp3"); //... CHANGED THIS FROM laugh.wav to china.mp3
        //"mixkit-happy-bells-notification-937" from https://mixkit.co/free-sound-effects/notification/
        AudioClip yay = loadSound("yay.wav");
        //"mixkit-car-ignition-1535.wav" from https://mixkit.co/free-sound-effects/car/
        AudioClip carSound = loadSound("car.wav");

        // 2. Button (haha)
        Button btn = new Button("Haha");
        btn.setOnAction(e -> play(laugh));

        // 3. Hover event (car)
        // car image: https://www.pexels.com/photo/red-sports-car-in-scenic-mountain-landscape-31192013/
        Image carImg = new Image(getClass().getResourceAsStream("car.jpg"));
        ImageView carView = new ImageView(carImg);
        carView.setFitWidth(200);
        carView.setPreserveRatio(true);
        // play sound when mouse enters the image area
        carView.setOnMouseEntered(e -> play(carSound));

        // 4. Layout
        VBox root = new VBox(20, btn, carView);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 400, 400);

        // 5. Keyboard Events (Up Arrow & Spacebar)
        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.UP) {
                play(yay);
            } else if (event.getCode() == KeyCode.SPACE) {
                play(atm);
            }
        });

        primaryStage.setTitle("Interactive Sound Board");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Helper to keep the code clean
    private AudioClip loadSound(String path) {
        try {
            return new AudioClip(getClass().getResource(path).toExternalForm());
        } catch (Exception e) {
            System.out.println("Could not load: " + path);
            return null;
        }
    }

    private void play(AudioClip clip) {
        if (clip != null && !clip.isPlaying()) {
            clip.play();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}