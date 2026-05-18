// Creation Date: April 28, 2026. at 11:32 AM
// Last Modified: April 28, 2026. at 11:35 AM

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main extends Application {
    private File selectedFile;
    private TextArea logArea;
    private ProgressBar progressBar;
    private Label statusLabel;

    @Override
    public void start(Stage stage) {
        VBox root = new VBox(15);
        root.setStyle("-fx-padding: 20; -fx-background-color: #2c3e50;");

        // Title
        Label title = new Label("FFmpeg File Converter");
        title.setStyle("-fx-font-size: 24px; -fx-text-fill: white; -fx-font-weight: bold;");

        // File selection
        Button selectButton = new Button("📁 Select File");
        selectButton.setStyle("-fx-font-size: 14px; -fx-padding: 10px 20px;");
        Label fileLabel = new Label("No file selected");
        fileLabel.setStyle("-fx-text-fill: white;");

        selectButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select File to Convert");
            selectedFile = fileChooser.showOpenDialog(stage);
            if (selectedFile != null) {
                fileLabel.setText("Selected: " + selectedFile.getName());
            }
        });

        // Format options
        Label formatLabel = new Label("Output Format:");
        formatLabel.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");

        ComboBox<String> formatBox = new ComboBox<>();
        formatBox.getItems().addAll(
                "MP4 (Video)",
                "AVI (Video)",
                "MOV (Video)",
                "MP3 (Audio)",
                "WAV (Audio)",
                "PNG (Image)",
                "JPG (Image)"
        );
        formatBox.setValue("MP4 (Video)");
        formatBox.setStyle("-fx-font-size: 14px;");

        // Convert button
        Button convertButton = new Button("🔄 Convert");
        convertButton.setStyle(
                "-fx-font-size: 16px; " +
                        "-fx-padding: 12px 30px; " +
                        "-fx-background-color: #3498db; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-weight: bold;"
        );

        convertButton.setOnAction(e -> {
            if (selectedFile != null) {
                String format = formatBox.getValue().split(" ")[0].toLowerCase();
                convertFileAsync(selectedFile, format);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No File Selected");
                alert.setHeaderText("Please select a file first!");
                alert.showAndWait();
            }
        });

        // Progress bar
        progressBar = new ProgressBar(0);
        progressBar.setPrefWidth(460);
        progressBar.setVisible(false);

        // Status label
        statusLabel = new Label("");
        statusLabel.setStyle("-fx-text-fill: white; -fx-font-size: 12px;");

        // Log area
        logArea = new TextArea();
        logArea.setEditable(false);
        logArea.setPrefHeight(150);
        logArea.setStyle("-fx-font-family: 'Courier New'; -fx-font-size: 11px;");

        root.getChildren().addAll(
                title,
                selectButton,
                fileLabel,
                formatLabel,
                formatBox,
                convertButton,
                progressBar,
                statusLabel,
                new Label("Conversion Log:") {{ setStyle("-fx-text-fill: white;"); }},
                logArea
        );

        Scene scene = new Scene(root, 500, 550);
        stage.setScene(scene);
        stage.setTitle("FFmpeg Converter");
        stage.show();
    }

    private void convertFileAsync(File inputFile, String outputFormat) {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                Platform.runLater(() -> {
                    progressBar.setVisible(true);
                    progressBar.setProgress(-1); // Indeterminate
                    statusLabel.setText("Converting...");
                    logArea.clear();
                });

                String inputPath = inputFile.getAbsolutePath();
                String outputPath = inputPath.substring(0, inputPath.lastIndexOf('.'))
                        + "_converted." + outputFormat;

                String ffmpegPath = "C:/path/to/ffmpeg.exe"; // UPDATE THIS!

                ProcessBuilder processBuilder = new ProcessBuilder(
                        ffmpegPath,
                        "-i", inputPath,
                        "-y", // Overwrite output file
                        outputPath
                );

                processBuilder.redirectErrorStream(true);
                Process process = processBuilder.start();

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(process.getInputStream())
                );

                String line;
                while ((line = reader.readLine()) != null) {
                    final String logLine = line;
                    Platform.runLater(() -> logArea.appendText(logLine + "\n"));
                }

                int exitCode = process.waitFor();

                Platform.runLater(() -> {
                    if (exitCode == 0) {
                        progressBar.setProgress(1.0);
                        statusLabel.setText("✓ Conversion successful!");
                        statusLabel.setStyle("-fx-text-fill: #2ecc71; -fx-font-size: 14px; -fx-font-weight: bold;");

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Success");
                        alert.setHeaderText("File converted successfully!");
                        alert.setContentText("Output: " + outputPath);
                        alert.showAndWait();
                    } else {
                        progressBar.setProgress(0);
                        statusLabel.setText("✗ Conversion failed!");
                        statusLabel.setStyle("-fx-text-fill: #e74c3c; -fx-font-size: 14px; -fx-font-weight: bold;");
                    }
                });

                return null;
            }
        };

        new Thread(task).start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}