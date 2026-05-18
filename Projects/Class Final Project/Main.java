// Creation Date: April 30, 2026. at 11:53 AM
// Last Modified: May 17, 2026. at 10:40 PM

import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.Insets; // FOR PADDING
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration; // FOR TIMERS


public class Main extends Application {
    private static Stage stage;
    // +[LAYOUTS]+  <========= SO THAT IT CAN BE ACCESSED BY OTHER METHODS
    private static StackPane IntroductionStackPane = new StackPane(); // STACKPANE MAKES THE OBJECTS OR VISUALS TO BE IN THE CENTER (IN ORDER)
    private static StackPane GameStackPane = new StackPane(); // STACKPANE MAKES THE OBJECTS OR VISUALS TO BE IN THE CENTER (IN ORDER)

    // +[SCENE]+  <========= SO THAT IT CAN BE ACCESSED BY OTHER METHODS
    Scene IntroductionScene = new Scene(IntroductionStackPane, Color.BLACK);
    Scene GameScene = new Scene(GameStackPane, Color.BLACK);

    // +[AUDIO]+
    private static AudioClip clickAudio = new AudioClip(Main.class.getResource("Medias/Audios/Click.mp3").toExternalForm()); // ... <============== CREDITS TO PROFESSOR PAIGE FOR THE AUDIOCLIP & CLAUDE FOR DEBUGGING;
    private static AudioClip hoverAudio = new AudioClip(Main.class.getResource("Medias/Audios/Hover.mp3").toExternalForm()); // ... <============== CREDITS TO PROFESSOR PAIGE FOR THE AUDIOCLIP & CLAUDE FOR DEBUGGING;

    // +[IMAGES]+
    // . Forward01
    private static Image ForwardGirlImage01 = new Image("Medias/Images/MainCharacter/Forward01.png"); // WalkingForward01 Image
    private static Image BackwardGirlImage01 = new Image("Medias/Images/MainCharacter/Backward01.png"); // WalkingBackward01 Image
    private static ImageView GirlImageView = new ImageView(ForwardGirlImage01); // the default is ForwardGirlImage01 (This object is so that we can view the image)

    // +[VARIABLES]+
    private static boolean MovingForward;
    private static boolean MovingBackward;
    private static boolean CantMove;

    @Override public void start(Stage stage) throws Exception {
        this.stage = stage;
        // ======== LAYOUTS (BRANCH NODES) ======== \\

        // ======== SCENE (ROOT NODES) ======== \\

        // ======== OBJECTS (LEAF NODES) ======== \\

        // +[INTRODUCTION]+
        // playIntroduction("Medias/Videos/HaloHaloStudios.mp4", "Medias/Videos/ContentWarning.mp4");

        GameMenu();

        // ======== STAGE>SCENE ======== \\
        stage.setScene(IntroductionScene); // FIRST SCENE TO PLAY
        stage.setWidth(1100);
        stage.setHeight(800);
        stage.getIcons().add(new Image("Medias/Images/Logo/CompanyLogo.png"));
        stage.setTitle("The Wandering Girl");
        stage.setResizable(false);
        stage.show(); // Displays the screen or box
    }

    // ======== METHODS ========= \\
    private void playIntroduction(String FilePath01, String FilePath02) throws Exception {
        try {
            // +[FIRST FILE]+
            // LOAD FILE
            String FirstFilePath = getClass().getResource(FilePath01).toExternalForm();
            Media FirstFileMedia = new Media(FirstFilePath); // Makes the File Path into a Media Object
            MediaPlayer FirstFileMediaPlayer = new MediaPlayer(FirstFileMedia); // Makes MediaPlayer for Media (ENABLING TO GIVE FUNCTIONALITY)
            MediaView FirstFileMediaView = new MediaView(FirstFileMediaPlayer); // Makes the MediaPlayer visible

            // CUSTOMIZATIONS
            FirstFileMediaView.setFitWidth(1100);
            FirstFileMediaView.setFitHeight(800);

            // PLAY FILE
            System.out.println("Video played");
            FirstFileMediaPlayer.play();

            // ADDING IT TO THE LAYOUT
            IntroductionStackPane.getChildren().add(FirstFileMediaView);

            // +[SECOND FILE]+
            FirstFileMediaPlayer.setOnEndOfMedia(() -> {
                // When the mp4 finishes
                // LOAD
                String SecondFilePath = getClass().getResource(FilePath02).toExternalForm();
                Media SecondFileMedia = new Media(SecondFilePath); // Makes the File Path into a Media Object
                MediaPlayer SecondFileMediaPlayer = new MediaPlayer(SecondFileMedia); // Makes MediaPlayer for Media (ENABLING TO GIVE FUNCTIONALITY)
                MediaView SecondFileMediaView = new MediaView(SecondFileMediaPlayer); // Makes the MediaPlayer visible

                // CUSTOMIZATIONS
                SecondFileMediaView.setFitWidth(1100);
                SecondFileMediaView.setFitHeight(760);

                // PLAY FILE
                SecondFileMediaPlayer.play();

                // ADDING IT TO THE PANE
                IntroductionStackPane.getChildren().clear(); // THIS CLEARS THE PREVIOUS MP4 (Company Logo)
                IntroductionStackPane.getChildren().add(SecondFileMediaView);

                //... CLICKING FUNCTION (AFTER 5-10 SECONDS HAD PASSED)    <============================= THIS IS MADE BY THE HELP OF CLAUDE AI
                boolean[] canClick = {false}; // To track if you can click or not

                // COOLDOWN
                PauseTransition cooldown = new PauseTransition(Duration.seconds(5)); // 5 seconds
                cooldown.setOnFinished(event -> {
                    canClick[0] = true; // allow clicking after delay
                });
                cooldown.play();

                // +[AFTER CONTENT WARNING EVENTS]+
                SecondFileMediaView.setOnMouseClicked(e1 -> {
                    if (canClick[0] == true) { // if it can click
                        canClick[0] = false;

                        // +[FADE TRANSITION]+
                        FadeTransition fade = fadeOut(IntroductionStackPane);
                        fade.setOnFinished(e -> {
                            GameMenu();
                        });

                        fade.play();
                    }
                });
                SecondFileMediaPlayer.setOnEndOfMedia(() -> {
                    GameMenu();
                });
            });
        } catch (Exception e) {
            System.out.println("Video could not load: " + FilePath01);
        }
    }
    private void GameMenu() {
        // ===== [LAYOUTS (ROOT & BRANCH NODES)] ==== \\
        BorderPane GameMenuBorderPane = new BorderPane(); // Places which side of the screen the Vbox will go
        VBox GameMenuOptionsVBox = new VBox(); // a Vertical Layout
        VBox GameMenuFooterVbox = new VBox(); // a Vertical Layout
        VBox GameMenuTitleVbox = new VBox(); // a Vertical Layout

        // ===== [NODES] ==== \\
        // +[BACKGROUND]+
        MediaView GameMenuBackgroundMediaView = new MediaView(); // Makes the MediaPlayer visible
        try {
            // LOAD FILE
            String GameMenuBackgroundFilePath = getClass().getResource("Medias/Videos/GameMenuBackground.mp4").toExternalForm();
            Media GameMenuBackgroundMedia = new Media(GameMenuBackgroundFilePath); // Makes the File Path into a Media Object
            MediaPlayer GameMenuBackgroundMediaPlayer = new MediaPlayer(GameMenuBackgroundMedia); // Makes MediaPlayer for Media (ENABLING TO GIVE FUNCTIONALITY)
            GameMenuBackgroundMediaView = new MediaView(GameMenuBackgroundMediaPlayer); // Makes the MediaPlayer visible

            // CUSTOMIZATIONS
            GameMenuBackgroundMediaView.setFitWidth(1100);
            GameMenuBackgroundMediaView.setFitHeight(800);

            // PLAY FILE
            GameMenuBackgroundMediaPlayer.setAutoPlay(true);
            GameMenuBackgroundMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

            // ADDING IT TO THE LAYOUT
            GameMenuOptionsVBox.getChildren().add(GameMenuBackgroundMediaView);
        } catch (Exception e) {
            System.out.println("Game Menu Background can't be found (mp4)");
        }

        // +[FOOTER]
        Text FooterText01 = FooterText("CSC-190 Final Project");
        Text FooterText02 = FooterText("Date Published: May 4, 2026");

        // +[GAMETITLE]+
        Text GameTitle = new Text("The Wandering Girl"); // Text Content
        GameTitle.setFill(Color.WHITE); // Text Color
        GameTitle.setFont(Font.font("Arial Black", FontWeight.BOLD, 65)); // Font Style, Bold, Size
        GameTitle.setStroke(Color.BLACK); // Stroke Color
        GameTitle.setStrokeWidth(2); // Stroke Width

        // +[BUTTON STYLES]+
        String NormalButton = "-fx-border-width: 2px;" + // ... <=========== CREDITS TO COPILOT FOR THE SYNTAX
                "-fx-border-radius: 8px;" +
                "-fx-border-color: white;" +
                "-fx-font-size: 30px;" +
                "-fx-text-fill: White;" +
                "-fx-font-weight: bold;" +
                "-fx-font-family: Arial Black;" +
                "-fx-stroke: black;" +
                "-fx-stroke-width: 2px;" +
                "-fx-background-color: transparent;" +
                "-fx-effect: dropshadow(gaussian, black, 3, 0, 0, 0);"; // blurType, Color, Radius, spread, offsetX, offsetY

        // +[PLAY BUTTON]+
        Button PlayButton = new Button("Play"); // Creates a button
        PlayButton.setPrefSize(200, 50); // Width, Height
        PlayButton.setStyle(NormalButton);
        PlayButton.setCursor(Cursor.HAND); // ... <=========== CREDITS TO COPILOT FOR THE SYNTAX

        // +[CREDITS BUTTON]+
        Button CreditsButton = new Button("Credits");
        CreditsButton.setPrefSize(200, 50); // Width, Height
        CreditsButton.setStyle(NormalButton);
        CreditsButton.setCursor(Cursor.HAND); // ... <=========== CREDITS TO COPILOT FOR THE SYNTAX

        // +[OPTION BUTTON]+
        Button OptionsButton = new Button("Options");
        OptionsButton.setPrefSize(200, 50); // Width, Height
        OptionsButton.setStyle(NormalButton);
        OptionsButton.setCursor(Cursor.HAND); // ... <=========== CREDITS TO COPILOT FOR THE SYNTAX

        // ===== [LAYOUT CUSTOMIZATIONS] ==== \\
        // +[BORDERPANE]+
        GameMenuBorderPane.setCenter(GameMenuOptionsVBox);
        GameMenuBorderPane.setBottom(GameMenuFooterVbox);
        GameMenuBorderPane.setTop(GameMenuTitleVbox);

        // +[GameMenuOptionsVBOX]+
        GameMenuOptionsVBox.setAlignment(Pos.CENTER); // ... CREDITS TO CLAUDE AI FOR MAKING IT CENTER
        GameMenuOptionsVBox.setMargin(PlayButton, new Insets(60, 0, 35, 0)); // Margin (top, right, bottom, left) <======== THANKS TO CLAUDE
        GameMenuOptionsVBox.setMargin(CreditsButton, new Insets(0, 0, 35, 0)); // Margin (top, right, bottom, left) <======== THANKS TO CLAUDE
        GameMenuOptionsVBox.setMargin(OptionsButton, new Insets(0, 0, 35, 0)); // Margin (top, right, bottom, left) <======== THANKS TO CLAUDE
        GameMenuOptionsVBox.getChildren().addAll(PlayButton, CreditsButton, OptionsButton);

        // +[GameMenuTitleVbox]+
        GameMenuTitleVbox.setAlignment(Pos.CENTER); // ... CREDITS TO CLAUDE AI FOR MAKING IT CENTER
        GameMenuTitleVbox.setMargin(GameTitle, new Insets(120, 0, 0, 0)); // Margin (top, right, bottom, left) <======== THANKS TO CLAUDE
        GameMenuTitleVbox.getChildren().add(GameTitle);


        // +[GameMenuFooterVbox]+
        GameMenuFooterVbox.setMargin(FooterText01, new Insets(0, 0, 0, 10)); // Margin (top, right, bottom, left) <======== THANKS TO CLAUDE
        GameMenuFooterVbox.setMargin(FooterText02, new Insets(0, 0, 10, 10)); // Margin (top, right, bottom, left) <======== THANKS TO CLAUDE
        GameMenuFooterVbox.getChildren().addAll(FooterText01, FooterText02);

        // +[GameMenuStackPane]+
        StackPane GameMenuStackPane = new StackPane(GameMenuBackgroundMediaView, GameMenuBorderPane); // ... CREDITS TO CLAUDE AI FOR MAKING ME FIGURE OUT HOW TO FIX BACKGROUND BY USING STACKPANE
        GameMenuStackPane.setOpacity(0);
        IntroductionScene.setRoot(GameMenuStackPane);

        // +[FADE TRANSITION]+
        FadeTransition fadeGameMenuStackPane = fadeIn(GameMenuStackPane);
        fadeGameMenuStackPane.play();

        // ===== [BUTTON EVENTS] ==== \\
        boolean[] CanClick = {true}; // ... <============== CREDITS TO CLAUDE FOR TEACHING ME THAT MANIPULATION LOCAL VARIABLES IN A LAMBDA LINE IS ILLEGAL SO USING AN ARRAY IS NEEDED

        // +[PLAY BUTTON]+
        PlayButton.setOnMouseClicked(e1 -> {
            if (CanClick[0] == true) {
                CanClick[0] = false;
                clickAudio.stop();
                clickAudio.play();

                // +[TRANSITION]+
                FadeTransition fade = fadeOut(GameMenuStackPane);
                fade.play();
                fade.setOnFinished(e0 -> {
                    Game();
                });

                // +[COOLDOWN]+
                PauseTransition cooldown = new PauseTransition(Duration.seconds(5));
                cooldown.setOnFinished(event -> {
                    CanClick[0] = true; // re-enable clicking
                });
                cooldown.play();
            } else {
                System.out.println("BUTTON IS ON COOLDOWN");
            }
        });
        PlayButton.setOnMouseEntered(e -> {
            hoverAudio.play();
            PlayButton.setPrefSize(220, 70); // Width, Height
        });
        PlayButton.setOnMouseExited(e -> {
            PlayButton.setPrefSize(200, 50); // Width, Height
        });

        // +[CREDITS BUTTON]+
        CreditsButton.setOnMouseClicked(e0 -> {
            clickAudio.stop();
            clickAudio.play();
        });
        CreditsButton.setOnMouseEntered(e2 -> {
            hoverAudio.play();
            CreditsButton.setPrefSize(220, 70); // Width, Height
        });
        CreditsButton.setOnMouseExited(e3 -> {
            CreditsButton.setPrefSize(200, 50); // Width, Height
        });

        // +[OPTIONS BUTTON]+
        OptionsButton.setOnMouseClicked(e0 -> {
            clickAudio.stop();
            clickAudio.play();
        });
        OptionsButton.setOnMouseEntered(e2 -> {
            hoverAudio.play();
            OptionsButton.setPrefSize(220, 70); // Width, Height
        });
        OptionsButton.setOnMouseExited(e3 -> {
            OptionsButton.setPrefSize(200, 50); // Width, Height
        });
    }
    private void Game() {
        // ===== [LAYOUTS (ROOT & BRANCH NODES)] ==== \\
        GameStackPane.setOpacity(0); // makes it not visible
        BorderPane GameBorderPane = new BorderPane(); // Places which side of the screen the Vbox/Hbox will go
        HBox GameOptionsHBox = new HBox(); // a Horizontal Layout

        // ===== [NODES] ==== \\
        // +[BUTTON STYLES]+
        String onButton = "-fx-border-width: 2px;" + //... <============== CREDITS TO COPILOT FOR THE SYNTAX
                "-fx-border-radius: 8px;" +
                "-fx-border-color: black;" +
                "-fx-font-size: 10px;" +
                "-fx-text-fill: black;" +
                "-fx-font-weight: bold;" +
                "-fx-font-family: Arial Black;" +
                "-fx-stroke: white;" +
                "-fx-stroke-width: 2px;" +
                "-fx-background-color: white;" +
                "-fx-background-radius: 10px;" +
                "-fx-effect: dropshadow(gaussian, white, 3, 0, 0, 0);"; // blurType, Color, Radius, spread, offsetX, offsetY
        String offButton = "-fx-border-width: 2px;" + //... <============== CREDITS TO COPILOT FOR THE SYNTAX
                "-fx-border-radius: 8px;" +
                "-fx-border-color: white;" +
                "-fx-font-size: 10px;" +
                "-fx-text-fill: White;" +
                "-fx-font-weight: bold;" +
                "-fx-font-family: Arial Black;" +
                "-fx-stroke: black;" +
                "-fx-stroke-width: 2px;" +
                "-fx-background-color: transparent;" +
                "-fx-effect: dropshadow(gaussian, black, 3, 0, 0, 0);"; // blurType, Color, Radius, spread, offsetX, offsetY

        // +[GO FORWARD BUTTON]+
        Button GoForwardButton = new Button("Go Forward");
        GoForwardButton.setPrefSize(200, 50); // Width, Height
        GoForwardButton.setStyle(onButton);
        GoForwardButton.setCursor(Cursor.HAND); // ... <=========== CREDITS TO COPILOT FOR THE SYNTAX

        // +[GO BACKWARD BUTTON]+
        Button GoBackwardButton = new Button("Go Backward");
        GoBackwardButton.setPrefSize(200, 50); // Width, Height
        GoBackwardButton.setStyle(offButton);
        GoBackwardButton.setCursor(Cursor.HAND); // ... <=========== CREDITS TO COPILOT FOR THE SYNTAX

        // ===== [LAYOUT CUSTOMIZATIONS] ==== \\

        // +[GameBorderPane]+
        GameBorderPane.setBottom(GameOptionsHBox);


        // +[GameOptionsHBox]+
        GameOptionsHBox.setAlignment(Pos.TOP_CENTER);
        GameOptionsHBox.getChildren().addAll(GoBackwardButton, GoForwardButton);
        GameOptionsHBox.setMargin(GoForwardButton, new Insets(0, 0, 20, 650)); // Margin (top, right, bottom, left) <======== THANKS TO CLAUDE

        // +[GameStackPane]+
        GameStackPane.setStyle("-fx-background-color: black;"); //... <============== CREDITS TO CLAUDE FOR HELPING FIX SCENE FROM TURNING WHITE
        GameStackPane.getChildren().addAll(GameBorderPane, GirlImageView);

        // +[STAGE]+
        stage.setScene(GameScene);

        // +[ANIMATION]+
        Timeline[] GirlImageWalking = {Walking(GirlImageView)}; // DEFAULT TIMELINE (THIS NEEDS TO BE IN AN ARRAY BECAUSE WE CANT CHANGE IN A LAMBDA
        GirlImageWalking[0].play();

        // +[FADE TRANSITION]+
        FadeTransition GameTransition = fadeIn(GameStackPane);
        GameTransition.play();

        // ===== [BUTTON EVENTS] ==== \\
        boolean[] CanClick = {true}; // ... <============== CREDITS TO CLAUDE FOR TEACHING ME THAT MANIPULATION LOCAL VARIABLES IN A LAMBDA LINE IS ILLEGAL SO USING AN ARRAY IS NEEDED
        MovingForward = true; // The Main Character starts walking first

        // +[COOLDOWN]+
        PauseTransition cooldown = new PauseTransition(Duration.seconds(2)); // HOW LONG THE COOLDOWN FOR THE BUTTONS ARE
        cooldown.setOnFinished(event -> {
            CanClick[0] = true; // re-enable clicking
        });

        // +[GO FORWARD BUTTON]+
        GoForwardButton.setOnMouseClicked(e -> {
            if (CanClick[0] == true) {
                if (CantMove) {
                    // MAIN CHARACTER
                    GirlImageWalking[0].stop(); // stops the animation

                    System.out.println(" YOU CANT MOVE");
                } else {
                    CanClick[0] = false;
                    if (!MovingForward) { // if it is not moving forward
                        MovingForward = true;
                        GoForwardButton.setStyle(onButton); // Changes Button Style
                        // MAIN CHARACTER <============================== THANKS TO CLAUDE
                        changeWalking(GirlImageWalking, ForwardGirlImage01);

                        MovingBackward = false;
                        GoBackwardButton.setStyle(offButton);
                        
                        // play music something
                        // play video something
                    } else { // if it is moving forward
                        MovingForward = false;
                        GoForwardButton.setStyle(offButton);

                        MovingBackward = true;
                        GoBackwardButton.setStyle(onButton);
                        // MAIN CHARACTER <============================== THANKS TO CLAUDE
                        changeWalking(GirlImageWalking, BackwardGirlImage01); // Timeline, Image

                        // play music something
                        // play video something
                    }

                    // +[COOLDOWN]+
                    cooldown.stop(); // RESETS THE COOLDOWN (to prevent from spam clicks)
                    cooldown.play(); // PLAYS THE COOLDOWN
                }
            } else {
                System.out.println("FORWARD BUTTON IS ON COOLDOWN");
            }

        });

        // +[GO BACKWARD BUTTON]+
        GoBackwardButton.setOnMouseClicked(e -> {
            if (CanClick[0] == true) {
                if (CantMove) {
                    // MAIN CHARACTER
                    GirlImageWalking[0].stop(); // stops the animation

                    System.out.println(" YOU CANT MOVE");
                } else {
                    CanClick[0] = false;
                    if (!MovingBackward) { // if it is not moving backward
                        MovingBackward = true;
                        GoBackwardButton.setStyle(onButton);
                        // MAIN CHARACTER <============================== THANKS TO CLAUDE
                        changeWalking(GirlImageWalking, BackwardGirlImage01); // Timeline, Image

                        MovingForward = false;
                        GoForwardButton.setStyle(offButton);

                    } else { // if it is moving backward
                        MovingBackward = false;
                        GoBackwardButton.setStyle(offButton);

                        MovingForward = true;
                        GoForwardButton.setStyle(onButton);
                        // MAIN CHARACTER <============================== THANKS TO CLAUDE
                        changeWalking(GirlImageWalking, ForwardGirlImage01); // Timeline, Image
                    }

                    // +[COOLDOWN]+
                    cooldown.stop(); // RESETS THE COOLDOWN (to prevent from spam clicks)
                    cooldown.play(); // PLAYS THE COOLDOWN
                }
            } else {
                System.out.println("BACKWARD BUTTON IS ON COOLDOWN");
            }
        });
    }

    // ======== OTHER METHODS ========= \\

    // +[ANIMATIONS]+
    private Timeline Walking(ImageView imgView) {
        Timeline Walking = new Timeline(
                new KeyFrame(
                        Duration.seconds(0.5),
                        new KeyValue(imgView.translateXProperty(), 10),
                        new KeyValue(imgView.translateYProperty(), 10)
                ),
                new KeyFrame(
                        Duration.seconds(1),
                        new KeyValue(imgView.translateXProperty(), 0),
                        new KeyValue(imgView.translateYProperty(), 0)
                        ),
                new KeyFrame(
                        Duration.seconds(1.5),
                        new KeyValue(imgView.translateXProperty(), -20),
                        new KeyValue(imgView.translateYProperty(), 10)
                ),
                new KeyFrame(
                        Duration.seconds(2),
                        new KeyValue(imgView.translateXProperty(), 0),
                        new KeyValue(imgView.translateYProperty(), 0)
                )
        );

        Walking.setCycleCount(Walking. INDEFINITE);

        return Walking;
    };
    private void changeWalking(Timeline[] tmLine, Image img) { // Animation, Image
        tmLine[0].stop(); // Stops (to prevent animation from stacking together)
        GirlImageView.setTranslateX(0); // Resets X position (to reset the saved imageview position from the timeline)
        GirlImageView.setTranslateY(0); // Resets Y position (to reset the saved imageview position from the timeline)
        GirlImageView.setImage(img); // Changes the Image View
        tmLine[0] = Walking(GirlImageView); // Changes the Timeline
        tmLine[0].play(); // Plays
    }

    // +[TRANSITIONS]+
    private FadeTransition fadeOut(Node node) { // Node is what's inside the scene
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(2), node);
        fadeOut.setFromValue(1.0); // fully visible
        fadeOut.setToValue(0.0);   // fully invisible

        return fadeOut;
    } //...  <============== CREDITS TO CLAUDE FOR HELPING ME FIGURE OUT WHAT DATA TYPE PARAMETERS TO USE
    private FadeTransition fadeIn(Node node) { // Node is what's inside the scene
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(2), node);
        fadeIn.setFromValue(0.0); // fully invisible
        fadeIn.setToValue(1.0);   // fully visible

        return fadeIn;
    } //...  <============== CREDITS TO CLAUDE FOR HELPING ME FIGURE OUT WHAT DATA TYPE PARAMETERS TO USE

    // +[FOOTER TEXT]+
    private Text FooterText(String s) {
        Text FooterText = new Text(s); // Text Content
        FooterText.setFill(Color.WHITE); // Text Color
        FooterText.setFont(Font.font("Arial Black", 10)); // Font Style, Bold, Size
        FooterText.setStroke(Color.BLACK); // Stroke Color
        FooterText.setStrokeWidth(0.3); // Stroke Width
        FooterText.setOpacity(0.2); // Opacity

        return FooterText;
    }

    // +[MAIN METHOD]+
    public static void main(String[] args) {
        launch(args);
    }
}

// TODO: INITIALLY THINKING OF REPLACING THE GAME TITLE INTO AN IMAGE (BETTER TEXT VISUALS)
// TODO: CREATE MORE VIDEO BACKGROUND FOR THE GAME MENU AND ADD EERIE BACKGROUND MUSIC
// TODO: ADD COOLDOWN FOR BUTTONS IN THE MENU PS: ADD THE COOLDOWN FOR THE OTHER BACKWARD BUTTON IN THE GAME SCENE

// streak

//! TODO: SHORTER THE VIDEO BACKGROUND FOR THE GAME.
//! TODO: FIX THE GIT PUSH ISSUE (SOME FILES ARE TOO LARGE FOR GITHUB)