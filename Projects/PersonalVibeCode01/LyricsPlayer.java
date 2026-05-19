// Creation Date: May 18, 2026. at 11:20 PM
// Last Modified: May 19, 2026. at  1:34 AM

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Synchronized Lyrics Player Application
 * Displays lyrics with typewriter effect synchronized to MP3 playback
 * Author: Student Project
 * Course: Java Programming - Finger Lakes Community College
 */
public class LyricsPlayer extends Application {

    // ==================== UI COMPONENTS ====================

    // Left Panel Components
    private TextArea lyricsDisplay;           // Main lyrics display area
    private Label currentTxtLabel;            // Shows current TXT file name
    private Label currentMp3Label;            // Shows current MP3 file name
    private Button addButton;                 // Add selected file
    private Button removeButton;              // Remove current file
    private Button playButton;                // Play/Stop playback
    private Button continueButton;            // Continue from pause

    // Right Panel Components
    private ListView<String> videoListView;   // List of MP3 files
    private ListView<String> textListView;    // List of TXT files

    // ==================== DATA MANAGEMENT ====================

    private File mediaFolder;                 // Media folder containing files
    private String currentMp3File = null;     // Currently added MP3
    private String currentTxtFile = null;     // Currently added TXT
    private String fullLyricsText = "";       // Complete lyrics content

    // ==================== PLAYBACK STATE ====================

    private MediaPlayer mediaPlayer;          // MP3 player
    private Timer typewriterTimer;            // Timer for typewriter effect
    private boolean isPlaying = false;        // Is currently playing
    private int currentCharIndex = 0;         // Current character position in lyrics
    private double charDelay = 0;             // Delay between characters (ms)
    private int lastPausedIndex = 0;          // Position when paused

    // ==================== MAIN METHOD ====================

    public static void main(String[] args) {
        launch(args);
    }

    // ==================== APPLICATION START ====================

    @Override
    public void start(Stage primaryStage) {
        // Initialize media folder
        initializeMediaFolder();

        // Create main layout
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #2E5CB8;");
        root.setPadding(new Insets(20));

        // Create left panel (lyrics display and controls)
        VBox leftPanel = createLeftPanel();

        // Create right panel (file lists)
        VBox rightPanel = createRightPanel();

        // Add panels to root
        root.setLeft(leftPanel);
        root.setRight(rightPanel);

        // Create and show scene
        Scene scene = new Scene(root, 1200, 700);
        primaryStage.setTitle("Lyrics Player - Synchronized Playback");
        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest(e -> cleanup());
        primaryStage.show();

        // Load files from Media folder
        loadMediaFiles();
    }

    // ==================== MEDIA FOLDER INITIALIZATION ====================

    /**
     * Creates Media folder if it doesn't exist
     * The folder should be at the same level as the application
     */
    private void initializeMediaFolder() {
        mediaFolder = new File("Projects/Other Class Final Project/Media");
        if (!mediaFolder.exists()) {
            boolean created = mediaFolder.mkdir();
            if (created) {
                System.out.println("Media folder created successfully");
            } else {
                System.err.println("Failed to create Media folder");
            }
        }
    }

    // ==================== UI CREATION - LEFT PANEL ====================

    /**
     * Creates the left panel containing lyrics display and controls
     */
    private VBox createLeftPanel() {
        VBox leftPanel = new VBox(15);
        leftPanel.setPadding(new Insets(15));
        leftPanel.setPrefWidth(600);

        // Lyrics display area with styled border
        VBox lyricsContainer = new VBox();
        lyricsContainer.setStyle(
                "-fx-background-color: #E0E0E0;" +
                        "-fx-border-color: #2E5CB8;" +
                        "-fx-border-width: 5;" +
                        "-fx-border-radius: 15;" +
                        "-fx-background-radius: 15;"
        );
        lyricsContainer.setPadding(new Insets(20));
        lyricsContainer.setPrefHeight(450);

        // Title label
        Label titleLabel = new Label("Lyrics this, Lyrics That");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titleLabel.setTextFill(Color.web("#2E5CB8"));
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setMaxWidth(Double.MAX_VALUE);

        // Lyrics text area
        lyricsDisplay = new TextArea();
        lyricsDisplay.setEditable(false);
        lyricsDisplay.setWrapText(true);
        lyricsDisplay.setStyle(
                "-fx-control-inner-background: #E0E0E0;" +
                        "-fx-text-fill: #2E5CB8;" +
                        "-fx-font-size: 16px;" +
                        "-fx-font-weight: bold;"
        );
        lyricsDisplay.setPrefHeight(380);
        VBox.setVgrow(lyricsDisplay, Priority.ALWAYS);

        lyricsContainer.getChildren().addAll(titleLabel, lyricsDisplay);

        // File name display boxes
        currentTxtLabel = createFileLabel("TXT FILE NAME HERE");
        currentMp3Label = createFileLabel("MP3 FILE NAME HERE");

        // Control buttons
        HBox buttonBox = new HBox(15);
        buttonBox.setAlignment(Pos.CENTER);

        addButton = createStyledButton("ADD");
        removeButton = createStyledButton("REMOVE");
        playButton = createStyledButton("PLAY");
        continueButton = createStyledButton("CONTINUE");

        // Button actions
        addButton.setOnAction(e -> handleAdd());
        removeButton.setOnAction(e -> handleRemove());
        playButton.setOnAction(e -> handlePlayStop());
        continueButton.setOnAction(e -> handleContinue());

        buttonBox.getChildren().addAll(addButton, removeButton, playButton, continueButton);

        leftPanel.getChildren().addAll(lyricsContainer, currentMp3Label, currentTxtLabel, buttonBox);

        return leftPanel;
    }

    // ==================== UI CREATION - RIGHT PANEL ====================

    /**
     * Creates the right panel containing VIDEO and TEXT file lists
     */
    private VBox createRightPanel() {
        VBox rightPanel = new VBox(20);
        rightPanel.setPadding(new Insets(15));
        rightPanel.setPrefWidth(450);

        // VIDEO section (MP3 files)
        VBox videoSection = createFileListSection("Video", true);
        videoListView = (ListView<String>) ((VBox) videoSection.getChildren().get(1)).getChildren().get(0);

        // TEXT section (TXT files)
        VBox textSection = createFileListSection("TEXT", false);
        textListView = (ListView<String>) ((VBox) textSection.getChildren().get(1)).getChildren().get(0);

        rightPanel.getChildren().addAll(videoSection, textSection);

        return rightPanel;
    }

    /**
     * Creates a file list section (VIDEO or TEXT)
     */
    private VBox createFileListSection(String title, boolean isVideo) {
        VBox section = new VBox(10);

        // Section header
        Label headerLabel = new Label(title);
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        headerLabel.setTextFill(Color.WHITE);
        headerLabel.setStyle(
                "-fx-background-color: #2E5CB8;" +
                        "-fx-border-color: white;" +
                        "-fx-border-width: 3;" +
                        "-fx-border-radius: 20;" +
                        "-fx-background-radius: 20;" +
                        "-fx-padding: 10 30 10 30;"
        );
        headerLabel.setMaxWidth(Double.MAX_VALUE);
        headerLabel.setAlignment(Pos.CENTER);

        // File list
        ListView<String> listView = new ListView<>();
        listView.setPrefHeight(250);
        listView.setStyle(
                "-fx-background-color: #2E5CB8;" +
                        "-fx-border-color: white;" +
                        "-fx-border-width: 3;" +
                        "-fx-border-radius: 15;" +
                        "-fx-background-radius: 15;"
        );

        // Custom cell factory for styling
        listView.setCellFactory(lv -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item);

                    // Check if this file is currently added
                    boolean isCurrentFile = false;
                    if (isVideo && item.equals(currentMp3File)) {
                        isCurrentFile = true;
                    } else if (!isVideo && item.equals(currentTxtFile)) {
                        isCurrentFile = true;
                    }

                    // Style based on selection and current status
                    if (isCurrentFile) {
                        setStyle(
                                "-fx-background-color: #1a2980;" +
                                        "-fx-text-fill: white;" +
                                        "-fx-font-weight: bold;" +
                                        "-fx-font-style: italic;" +
                                        "-fx-padding: 8;"
                        );
                    } else if (isSelected()) {
                        setStyle(
                                "-fx-background-color: #5a7fc4;" +
                                        "-fx-text-fill: white;" +
                                        "-fx-padding: 8;"
                        );
                    } else {
                        setStyle(
                                "-fx-background-color: #E0E0E0;" +
                                        "-fx-text-fill: #2E5CB8;" +
                                        "-fx-padding: 8;"
                        );
                    }
                }
            }
        });

        VBox listContainer = new VBox(listView);
        listContainer.setPadding(new Insets(10));
        listContainer.setStyle(
                "-fx-background-color: #2E5CB8;" +
                        "-fx-border-color: white;" +
                        "-fx-border-width: 3;" +
                        "-fx-border-radius: 15;" +
                        "-fx-background-radius: 15;"
        );

        section.getChildren().addAll(headerLabel, listContainer);

        return section;
    }

    // ==================== UI HELPER METHODS ====================

    /**
     * Creates a styled label for file name display
     */
    private Label createFileLabel(String text) {
        Label label = new Label(text);
        label.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        label.setTextFill(Color.web("#2E5CB8"));
        label.setStyle(
                "-fx-background-color: #E0E0E0;" +
                        "-fx-border-color: #2E5CB8;" +
                        "-fx-border-width: 3;" +
                        "-fx-border-radius: 20;" +
                        "-fx-background-radius: 20;" +
                        "-fx-padding: 10 20 10 20;"
        );
        label.setMaxWidth(Double.MAX_VALUE);
        label.setAlignment(Pos.CENTER);
        return label;
    }

    /**
     * Creates a styled button
     */
    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        button.setStyle(
                "-fx-background-color: #E0E0E0;" +
                        "-fx-text-fill: #2E5CB8;" +
                        "-fx-border-color: #2E5CB8;" +
                        "-fx-border-width: 3;" +
                        "-fx-border-radius: 20;" +
                        "-fx-background-radius: 20;" +
                        "-fx-padding: 10 25 10 25;"
        );
        button.setOnMouseEntered(e -> button.setStyle(
                "-fx-background-color: #2E5CB8;" +
                        "-fx-text-fill: white;" +
                        "-fx-border-color: white;" +
                        "-fx-border-width: 3;" +
                        "-fx-border-radius: 20;" +
                        "-fx-background-radius: 20;" +
                        "-fx-padding: 10 25 10 25;"
        ));
        button.setOnMouseExited(e -> button.setStyle(
                "-fx-background-color: #E0E0E0;" +
                        "-fx-text-fill: #2E5CB8;" +
                        "-fx-border-color: #2E5CB8;" +
                        "-fx-border-width: 3;" +
                        "-fx-border-radius: 20;" +
                        "-fx-background-radius: 20;" +
                        "-fx-padding: 10 25 10 25;"
        ));
        return button;
    }

    // ==================== FILE LOADING ====================

    /**
     * Loads all MP3 and TXT files from Media folder
     * Separates them into VIDEO and TEXT lists
     */
    private void loadMediaFiles() {
        videoListView.getItems().clear();
        textListView.getItems().clear();

        File[] files = mediaFolder.listFiles();
        if (files != null) {
            for (File file : files) {
                String fileName = file.getName();

                // Check if file is MP3
                if (fileName.toLowerCase().endsWith(".mp3")) {
                    videoListView.getItems().add(fileName);
                }
                // Check if file is TXT
                else if (fileName.toLowerCase().endsWith(".txt")) {
                    textListView.getItems().add(fileName);
                }
            }
        }

        System.out.println("Loaded " + videoListView.getItems().size() + " MP3 files");
        System.out.println("Loaded " + textListView.getItems().size() + " TXT files");
    }

    // ==================== BUTTON HANDLERS ====================

    /**
     * Handles ADD button click
     * Adds selected file from either VIDEO or TEXT list
     */
    private void handleAdd() {
        // Check if a VIDEO (MP3) file is selected
        String selectedMp3 = videoListView.getSelectionModel().getSelectedItem();
        if (selectedMp3 != null) {
            // Check if user already has an MP3 added
            if (currentMp3File != null) {
                showAlert("You can only add 1 MP3 file at a time!");
                return;
            }
            currentMp3File = selectedMp3;
            currentMp3Label.setText(selectedMp3);
            videoListView.refresh(); // Refresh to update highlighting
            System.out.println("Added MP3: " + selectedMp3);
        }

        // Check if a TEXT (TXT) file is selected
        String selectedTxt = textListView.getSelectionModel().getSelectedItem();
        if (selectedTxt != null) {
            // Check if user already has a TXT added
            if (currentTxtFile != null) {
                showAlert("You can only add 1 TXT file at a time!");
                return;
            }
            currentTxtFile = selectedTxt;
            currentTxtLabel.setText(selectedTxt);
            textListView.refresh(); // Refresh to update highlighting

            // Load lyrics content
            loadLyrics(selectedTxt);
            System.out.println("Added TXT: " + selectedTxt);
        }

        // If nothing selected
        if (selectedMp3 == null && selectedTxt == null) {
            showAlert("Please select a file from VIDEO or TEXT list first!");
        }
    }

    /**
     * Handles REMOVE button click
     * Removes currently added MP3 or TXT file
     */
    private void handleRemove() {
        boolean removed = false;

        // Check which list has a selection or current file
        String selectedMp3 = videoListView.getSelectionModel().getSelectedItem();
        if (selectedMp3 != null && selectedMp3.equals(currentMp3File)) {
            currentMp3File = null;
            currentMp3Label.setText("MP3 FILE NAME HERE");

            // Stop media if playing
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer = null;
            }

            videoListView.refresh();
            removed = true;
            System.out.println("Removed MP3");
        }

        String selectedTxt = textListView.getSelectionModel().getSelectedItem();
        if (selectedTxt != null && selectedTxt.equals(currentTxtFile)) {
            currentTxtFile = null;
            currentTxtLabel.setText("TXT FILE NAME HERE");
            fullLyricsText = "";
            lyricsDisplay.clear();
            textListView.refresh();
            removed = true;
            System.out.println("Removed TXT");
        }

        // If nothing to remove
        if (!removed) {
            showAlert("Please select the currently added/playing file to remove!");
        }

        // Reset playback state
        if (removed) {
            stopPlayback();
        }
    }

    /**
     * Handles PLAY/STOP button click
     * Starts playback or stops and resets
     */
    private void handlePlayStop() {
        if (!isPlaying) {
            // Start playback
            if (currentMp3File == null) {
                showAlert("Please add an MP3 file before playing!");
                return;
            }

            startPlayback();
            playButton.setText("STOP");
        } else {
            // Stop playback and reset
            stopPlayback();
            playButton.setText("PLAY");

            // Reset display
            lyricsDisplay.clear();
            currentCharIndex = 0;
            lastPausedIndex = 0;
        }
    }

    /**
     * Handles CONTINUE button click
     * Continues playback from last paused position
     */
    private void handleContinue() {
        if (currentMp3File == null) {
            showAlert("Please add an MP3 file before continuing!");
            return;
        }

        if (isPlaying) {
            showAlert("Already playing! Use STOP to pause.");
            return;
        }

        // Continue from last position
        currentCharIndex = lastPausedIndex;
        startPlayback();
        playButton.setText("STOP");
    }

    // ==================== PLAYBACK CONTROL ====================

    /**
     * Starts MP3 playback and typewriter effect
     */
    private void startPlayback() {
        try {
            // Initialize media player if needed
            if (mediaPlayer == null) {
                File mp3File = new File(mediaFolder, currentMp3File);
                Media media = new Media(mp3File.toURI().toString());
                mediaPlayer = new MediaPlayer(media);

                // When media is ready, calculate typewriter speed
                mediaPlayer.setOnReady(() -> {
                    double durationSeconds = mediaPlayer.getTotalDuration().toSeconds();
                    calculateTypewriterSpeed(durationSeconds);
                    System.out.println("MP3 Duration: " + durationSeconds + " seconds");
                    System.out.println("Character delay: " + charDelay + " ms");
                });

                // Handle end of playback
                mediaPlayer.setOnEndOfMedia(() -> {
                    stopPlayback();
                    playButton.setText("PLAY");
                });
            }

            // Start playing
            mediaPlayer.play();
            isPlaying = true;

            // Start typewriter effect
            startTypewriterEffect();

        } catch (Exception e) {
            showAlert("Error playing MP3: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Stops playback and saves current position
     */
    private void stopPlayback() {
        isPlaying = false;

        // Stop typewriter timer
        if (typewriterTimer != null) {
            typewriterTimer.cancel();
            typewriterTimer = null;
        }

        // Pause media player (don't stop, so we can resume)
        if (mediaPlayer != null) {
            mediaPlayer.pause();
            lastPausedIndex = currentCharIndex;
        }
    }

    // ==================== LYRICS MANAGEMENT ====================

    /**
     * Loads lyrics from TXT file
     */
    private void loadLyrics(String fileName) {
        try {
            File txtFile = new File(mediaFolder, fileName);
            fullLyricsText = Files.readString(txtFile.toPath());
            System.out.println("Loaded lyrics: " + fullLyricsText.length() + " characters");
        } catch (IOException e) {
            showAlert("Error reading lyrics file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Calculates typewriter speed based on MP3 duration and lyrics length
     */
    private void calculateTypewriterSpeed(double mp3DurationSeconds) {
        if (fullLyricsText.isEmpty()) {
            charDelay = 100; // Default delay
            return;
        }

        // Calculate delay per character
        // Total time (ms) / number of characters = delay per character
        double totalMilliseconds = mp3DurationSeconds * 1000;
        charDelay = totalMilliseconds / fullLyricsText.length() / 1.5;

        // Ensure minimum delay for readability
        if (charDelay < 10) {
            charDelay = 10;
        }
    }

    // ==================== TYPEWRITER EFFECT ====================

    /**
     * Starts the typewriter effect animation
     */
    private void startTypewriterEffect() {
        if (fullLyricsText.isEmpty()) {
            System.out.println("No lyrics to display");
            return;
        }

        // Cancel existing timer
        if (typewriterTimer != null) {
            typewriterTimer.cancel();
        }

        typewriterTimer = new Timer(true);

        typewriterTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    if (!isPlaying) {
                        return;
                    }

                    // Check if we've reached the end
                    if (currentCharIndex >= fullLyricsText.length()) {
                        // Reset and start over
                        currentCharIndex = 0;
                        lyricsDisplay.clear();
                    }

                    // Add next character
                    String displayText = lyricsDisplay.getText();
                    displayText += fullLyricsText.charAt(currentCharIndex);
                    lyricsDisplay.setText(displayText);
                    currentCharIndex++;

                    // Auto-scroll to bottom
                    lyricsDisplay.setScrollTop(Double.MAX_VALUE);

                    // Check if text area is getting full (clear and continue)
                    if (displayText.length() > 500) { // Adjust based on visible area
                        // Check if there's more text to display
                        if (currentCharIndex < fullLyricsText.length()) {
                            lyricsDisplay.clear();
                        }
                    }
                });
            }
        }, 0, (long) charDelay);
    }

    // ==================== UTILITY METHODS ====================

    /**
     * Shows an alert dialog
     */
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Lyrics Player");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Cleanup when application closes
     */
    private void cleanup() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        if (typewriterTimer != null) {
            typewriterTimer.cancel();
        }
    }
}
