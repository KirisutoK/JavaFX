// Creation Date: April 21, 2026. at 2:51 PM
// Last Modified: April 28, 2026. at 10:01 AM

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {
    // +[COUNTERS]+
    private static Pane Pane01 = new Pane(); // In order for other methods to access
    private static int Counter = 0; // For Different Modes

    // ======== LAYOUTS (ROOT NODES) ======== \\
    private static Scene Scene01 = new Scene(Pane01);


    @Override public void start(Stage stage) throws Exception {

        // ======== AUDIO (mp3) ======== \\
        // +[BACKGROUND MUSIC]+
        try {
            // LOAD FILE
            String AudioPath01 = new File("C:\\Users\\kiris\\OneDrive - Finger Lakes Community College\\Documents\\FLCC\\Coding\\JavaFX\\Projects\\Class Art Contest (For Myself)\\InThePool_ChainsawMan(RezeArc).mp3").toURI().toString();
            Media MediaAudio01 = new Media(AudioPath01); // Makes the File into a Media
            MediaPlayer MediaPlayerAudio01 = new MediaPlayer(MediaAudio01); // Makes it to enable to run (functionality)
            // PLAY FILE
            MediaPlayerAudio01.setCycleCount(MediaPlayer.INDEFINITE);  // Loop forever
            MediaPlayerAudio01.setVolume(1);  // 100% volume
            MediaPlayerAudio01.play();
        } catch (Exception e) {
            System.out.println("Audio file not found: " + e.getMessage());
        }

        // ======== ART(SHAPES/IMAGES/VIDEOS) [NODES] ======== \\
        // +[SKY BASE]+
        SkyBase();

        // +[SUN BASE]+
        SunBase();

        // +[TERRAIN BACKGROUND]+
        addImage("Images/TerrainBackground.png");

        // +[SEA BASE]+
        SeaBase();
        addImage("Images/SeaLighting.png");

        // +[SUN'S REFLECTION]+
        SunReflection();

        // +[CLOUD]+
        Clouds();
        addImage("Images/CloudLighting.png");

        // +[TREE BRANCHES]+
        addImage("Images/TreeBranches.png");

        // +[TERRAIN]+
        addImage("Images/Terrain.png");

        // +[BIRDS]+
        addImage("Images/Birds.png");

        // +[BUTTONS]+
        Buttons();

        // ======== STAGE>SCENE ======== \\
        
        stage.setScene(Scene01);
        stage.setWidth(700);
        stage.setHeight(700);
        stage.setTitle("Christ Aerjil Dampog Coding Art");
        stage.setResizable(false);
        stage.show(); // Displays the screen or box
    }

    // ======== METHODS ========= \\
    // +[INSTANCES]+
    public static void SkyBase() {
        Rectangle SkyBase = new Rectangle(0, 0, 700,350); // X-Coordinate, Y-Coordinate, Weight, Height
        RadialGradient SkyGradient = new RadialGradient(
                0,              // focusAngle
                0,              // focusDistance
                350, 350,       // centerX, centerY
                200,            // radius
                false,          // proportional
                CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#fc6701")),
                new Stop(1, Color.web("#964105"))
        );
        SkyBase.setFill(SkyGradient);
        Pane01.getChildren().add(SkyBase);
    }
    public static void SunBase() {
        Circle SunBase = new Circle(350, 355, 60, Color.web("#ffc9a3")); // X-Coordinate, Y-Coordinate, Radius, Color
        SunBase.setStrokeWidth(1.5);
        SunBase.setStroke(Color.web("#ffd8bd"));

        RadialGradient SunGradient = new RadialGradient(
                0,              // focusAngle
                0,              // focusDistance
                350, 350,       // centerX, centerY
                200,            // radius
                false,          // proportional
                CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#fefdeb")),
                new Stop(1, Color.web("#f5bd50"))
        );
        SunBase.setFill(SunGradient);
        Pane01.getChildren().add(SunBase);
    }
    public static void SeaBase() {
        Rectangle SeaBase = new Rectangle(0, 350, 700, 350); // X-Coordinate, Y-Coordinate, Weight, Height
        SeaBase.setFill(Color.web("#946e54"));
        SeaBase.setStrokeWidth(1.5);
        SeaBase.setStroke(Color.web("#7d391a"));
        RadialGradient SeaGradient = new RadialGradient(
                0,              // focusAngle
                0,              // focusDistance
                350, 350,       // centerX, centerY
                300,            // radius
                false,          // proportional
                CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#803401")),
                new Stop(1, Color.web("#612a04"))
        );
        SeaBase.setFill(SeaGradient);
        Pane01.getChildren().add(SeaBase);
    }
    public static void SunReflection() {
        double[][] Reflections = { // CenterX, CenterY, RadiusX, RadiusY
                {350, 350, 150, 1},
                {350, 351.5, 145, 1.5},
                {350, 353.5, 130, 1},

                {350, 354.5, 125, 1},
                {350, 356, 126, 1.5},
                {350, 357.5, 127, 1},

                {350, 358.5, 100, 1.5},
                {350, 361, 90, 1.5},
                {350, 362.5, 127, 1},

                {350, 363.5, 70, 0.5},
                {350, 365, 50, 0.3},
                {350, 366.5, 60, 0.4},

                {350, 367, 150, 1},
                {350, 368.5, 145, 1.5},
                {350, 369.5, 130, 1},

                {350, 370, 10, 1.5},
                {350, 371, 40, 1.5},
                {350, 372.5, 60, 1},

                {350, 373.5, 70, 0.5},
                {350, 374, 50, 0.3},
                {350, 375.5, 60, 0.4},

                {350, 376.5, 125, 0.6},
                {350, 378, 126, 0.7},
                {350, 379.5, 127, 0.9},

                {350, 380.5, 90, 0.5},
                {350, 381, 80, 0.3},
                {350, 382.5, 100, 0.4},

                {350, 383.5, 80, 0.6},
                {350, 384, 70, 0.7},
                {350, 385.5, 90, 0.9},

                {350, 386.5, 70, 0.6},
                {350, 387, 40, 0.7},
                {350, 388.5, 50, 0.9},

                {350, 389.5, 70, 0.5},
                {350, 390, 50, 0.3},
                {350, 391.5, 60, 0.4},

                {350, 395.5, 40, 0.5},
                {350, 396, 30, 0.3},
                {350, 397.5, 35, 0.4},

                {350, 399.5, 70, 0.6},
                {350, 400, 40, 0.7},
                {350, 401.5, 50, 0.9},

                {350, 402.5, 40, 0.5},
                {350, 403, 30, 0.3},
                {350, 404.5, 35, 0.4},

                {350, 405.5, 30, 0.5},
                {350, 406, 20, 0.3},
                {350, 407.5, 15, 0.4},

                {350, 408.5, 45, 0.5},
                {350, 409, 25, 0.3},
                {350, 410.5, 20, 0.4},

                {350, 411.5, 80, 0.6},
                {350, 412, 70, 0.7},
                {350, 413.5, 90, 0.9},
                {350, 414, 70, 0.7},
                {350, 415.5, 90, 0.9},

                {350, 416.5, 80, 0.5},
                {350, 417, 70, 0.3},
                {350, 418.5, 60, 0.4},
                {350, 420, 45, 0.5},
                {350, 421, 25, 0.3},
                {350, 422.5, 20, 0.4},

                {350, 423.5, 50, 0.6},
                {350, 424, 40, 0.7},
                {350, 425.5, 30, 0.9},

                {350, 426.5, 20, 0.5},
                {350, 427, 15, 0.3},
                {350, 428.5, 35, 0.4},

                {350, 431, 10, 0.6},
                {350, 432, 40, 0.5},
                {350, 433.5, 30, 1},

                {350, 434, 10, 0.6},
                {350, 435, 60, 0.5},
                {350, 436.5, 30, 1},

                {350, 437.5, 5, 0.5},
                {350, 438, 45, 0.3},
                {350, 439.5, 20, 0.4},

                {350, 440.5, 3, 0.2},
                {350, 441, 25, 0.10},
                {350, 442.5, 35, 0.15},

                {350, 440.5, 3, 0.2},
                {350, 441, 5, 0.10},
                {350, 442.5, 20, 0.15},

                {350, 443.5, 20, 0.1},
                {350, 445, 15, 0.05},
                {350, 447.5, 10, 0.04},

                {350, 448.5, 2, 0.1},
                {350, 449, 1, 0.05},
                {350, 450.5, 2.5, 0.04},

                {350, 451.5, 1, 0.1},
                {345, 452, 4, 0.02},
                {355, 454.5, 3, 0.05},
        };

        for (double[] i: Reflections) {
            Ellipse Reflection = new Ellipse(i[0], i[1], i[2], i[3]);
            Reflection.setFill(Color.web("#f7eae1"));
            Pane01.getChildren().add(Reflection);
        }
    }
    public static void Clouds() {
        double[][] Clouds01 = { // CenterX, CenterY, RadiusX, RadiusY
                // +[CLOUD LEFT]+
                // CLOUD LEFT 01
                {220, 330, 50, 3},
                {200, 325, 20, 5},
                {200, 323, 30, 4},

                // CLOUD LEFT 02
                {100, 300, 60, 3.5},
                {100, 310, 30, 5.5},
                {100, 303, 40, 4.5},
                {115, 295, 25, 5.5},

                // CLOUD LEFT 03
                {0, 323, 25, 5.5},

                // CLOUD LEFT 04
                {110, 232, 50, 2.5},
                {90, 225, 100, 5.5},
                {90, 218, 155, 7.5},
                {103, 210, 55, 9},
                {30, 200, 55, 9},
                {20, 190, 55, 30},

                // CLOUD LEFT 05
                {50, 40, 445, 45},
                {50, 70, 200, 50},
                {20, 100, 200, 30},
                {120, 100, 155, 7.5},
                {0, 130, 155, 7.5},
                {30, 140, 155, 7.5},
                {120, 145, 50, 4},

                {165, 155, 40, 5},
                {170, 160, 30, 3},
                {175, 165, 15, 1},

                // +[CLOUD RIGHT]+
                // CLOUD RIGHT 01
                {500, 330, 50, 3},
                {500, 325, 20, 5},
                {500, 323, 30, 4},

                // CLOUD RIGHT 02
                {570, 300, 60, 3.5},
                {570, 310, 30, 5.5},
                {570, 303, 40, 4.5},
                {585, 295, 25, 5.5},

                // CLOUD RIGHT 03
                {670, 340, 10, 0.6},
                {650, 345, 5, 0.7},
                {650, 343, 15, 0.5},
                {690, 300, 15, 19},
                {675, 320, 30, 5.5},
                {690, 315, 30, 4.5},

                // CLOUD RIGHT 04
                {610, 232, 50, 2.5},
                {590, 225, 100, 5.5},
                {590, 218, 155, 7.5},
                {603, 210, 55, 9},
                {640, 200, 55, 9},
                {670, 190, 55, 30},

                //
        };

        for (double[] i: Clouds01) {
            Ellipse Cloud01 = new Ellipse(i[0], i[1], i[2], i[3]);
            Cloud01.setFill(Color.web("#e3baaa"));
//            Cloud01.setStrokeWidth(0.5);
//            Cloud01.setStroke(Color.web("#e6c6ba"));
            Pane01.getChildren().add(Cloud01);
        }
    }
    public static void Buttons(){
        // +[COUNTER TEXT]+
        Text CounterText = new Text(15, 650, "0"); // THIS IS A NODE (A TEXT) [x-axis, y-axis, String]
        CounterText.setFont(Font.font("Times New Roman", 35)); // THIS CHANGES THE FONT AND SIZE
        CounterText.setFill(Color.web("#faefdc", 0.2)); // THIS CHANGES THE FONT COLOR and OPACITY
        // EFFECT
        DropShadow dropshadow = new DropShadow();
        dropshadow.setRadius(5);           // Blur radius
        dropshadow.setOffsetX(2);          // Horizontal offset
        dropshadow.setOffsetY(2);          // Vertical offset
        dropshadow.setColor(Color.BLACK);  // Shadow color
        CounterText.setEffect(dropshadow);
        Pane01.getChildren().add(CounterText);

        // +[BUTTON LEFT]+
        Button ButtonLeft = new Button("<");
        ButtonLeft.setStyle(
                "-fx-background-color: #dc5a02;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 10px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-padding: 5px 10px;" +
                        "-fx-background-radius: 2px;"
        );
        ButtonLeft.setLayoutX(620);
        ButtonLeft.setLayoutY(630);
        ButtonLeft.setVisible(false);
        Pane01.getChildren().add(ButtonLeft);

        // +[BUTTON RIGHT]+
        Button ButtonRight = new Button(">");
        ButtonRight.setStyle(
                "-fx-background-color: #dc5a02;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 10px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-padding: 5px 10px;" +
                        "-fx-background-radius: 2px;"
        );
        ButtonRight.setLayoutX(650);
        ButtonRight.setLayoutY(630);
        Pane01.getChildren().add(ButtonRight);

        // +[BUTTON EVENTS]+
        ButtonRight.setOnAction(actionEvent -> {
            if (Counter <= 10) { // IF IT'S LESS THAN 10
                Counter += 1; // INCREMENTS COUNTER

                // +[FILTER CONDITIONS]+
                double HueCounter = Double.parseDouble("0."+Counter); // CONVERTS COUNTER INT INTO HUE DOUBLE
                HueFilter(HueCounter); // APPLIES FILTER
                if (Counter == 10) { // IF COUNTER IS 10
                    HueFilter(1);
                }
                CounterText.setText(Counter+" "); // UPDATES COUNTER TEXT

                // +[VISIBILITY CONDITIONS]+
                if (Counter >= 10) {
                    ButtonRight.setVisible(false);
                }
                if (Counter >= 1) {
                    ButtonLeft.setVisible(true);
                }
            }
        });
        ButtonLeft.setOnAction(actionEvent -> {
            if (Counter <= 10) { // IF IT'S LESS THAN 10
                Counter -= 1; // DECREASE COUNTER

                // +[FILTER CONDITIONS]+
                double HueCounter = Double.parseDouble("0."+Counter); // CONVERTS COUNTER INT INTO HUE DOUBLE
                HueFilter(HueCounter); // APPLIES FILTER
                CounterText.setText(Counter+" "); // UPDATES COUNTER TEXT

                // +[VISIBILITY CONDITIONS]+
                if (Counter <= 10) {
                    ButtonRight.setVisible(true);
                }
                if (Counter == 0) {
                    ButtonLeft.setVisible(false);
                }
            }
        });
    }

    // +[FILTERS]+
    public static void HueFilter(double n) {
        ColorAdjust NightTime = new ColorAdjust();
        NightTime.setHue(n);
        Pane01.setEffect(NightTime);
    }
    public static void ResetFilter() {
        ColorAdjust ResetFilter = new ColorAdjust();
        ResetFilter.setHue(0);
        ResetFilter.setSaturation(0);
        ResetFilter.setBrightness(0);
        ResetFilter.setContrast(0);
        Pane01.setEffect(ResetFilter);
    }

    // +[OTHERS]+
    public static void ImageAdjustments(ImageView IV) {
        IV.setX(0);
        IV.setY(0);
        IV.setFitWidth(700);
        IV.setFitHeight(665);
    }
    public static void addImage(String filePath) {
        Image ImageObj = new Image(filePath);
        ImageView ImageView = new ImageView(ImageObj);
        ImageAdjustments(ImageView);
        Pane01.getChildren().add(ImageView);
    }

    // +[MAIN]+
    public static void main(String[] args) {
        launch(args);
    }
}

// TODO: CAN ADD ANIMATIONS TO THE WATER IMAGE (LEFT TO RIGHT)
// TODO: CAN ADD ANIMATIONS TO THE BRANCHES
