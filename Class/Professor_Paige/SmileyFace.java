import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.stage.Stage;

public class SmileyFace extends Application {

    // Keep track of whether the face is happy or sad
    boolean isHappy = true;

    // The mouth shape (we will change this when the button is clicked)
    Arc mouth = new Arc();

    // The button
    Button toggleButton = new Button("Make Sad");

    @Override
    public void start(Stage primaryStage) {

        // ── Face (yellow circle) ─────────────────────────────────────────────
        Circle face = new Circle(100);          // radius = 100
        face.setFill(Color.YELLOW);
        face.setStroke(Color.BLACK);
        face.setStrokeWidth(3);

        // ── Eyes (two small black circles) ───────────────────────────────────
        Circle leftEye  = new Circle(-35, -30, 10, Color.BLACK);
        Circle rightEye = new Circle( 35, -30, 10, Color.BLACK);

        // ── Mouth (an Arc shape) ──────────────────────────────────────────────
        // Arc(centerX, centerY, radiusX, radiusY, startAngle, length)
        mouth.setCenterX(0);
        mouth.setCenterY(20);
        mouth.setRadiusX(50);
        mouth.setRadiusY(30);
        mouth.setStartAngle(200);        // starting angle of the arc
        mouth.setLength(140);            // how far the arc sweeps
        mouth.setType(ArcType.OPEN);     // just a curved line, not a filled slice
        mouth.setStroke(Color.BLACK);
        mouth.setStrokeWidth(4);
        mouth.setFill(Color.TRANSPARENT);

        // ── Group all face parts together ────────────────────────────────────
        // We use a Pane so we can position parts with (x, y) offsets from center
        javafx.scene.layout.Pane faceGroup = new javafx.scene.layout.Pane();
        faceGroup.setPrefSize(220, 220);

        // Move everything so the center of the face sits in the middle of faceGroup
        double cx = 110, cy = 110;
        face.setCenterX(cx);       face.setCenterY(cy);
        leftEye.setCenterX(cx + leftEye.getCenterX());
        leftEye.setCenterY(cy + leftEye.getCenterY());
        rightEye.setCenterX(cx + rightEye.getCenterX());
        rightEye.setCenterY(cy + rightEye.getCenterY());
        mouth.setCenterX(cx + mouth.getCenterX());
        mouth.setCenterY(cy + mouth.getCenterY());

        faceGroup.getChildren().addAll(face, leftEye, rightEye, mouth);

        // ── Button ────────────────────────────────────────────────────────────
        toggleButton.setStyle(
            "-fx-font-size: 16px;" +
            "-fx-padding: 10px 30px;" +
            "-fx-background-color: #4C35E5;" +
            "-fx-text-fill: white;" +
            "-fx-background-radius: 8px;"
        );

        // What happens when the button is clicked:
        toggleButton.setOnAction(event -> toggleFace());

        // ── Layout: stack the face and button vertically ─────────────────────
        VBox root = new VBox(20);           // 20px gap between children
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #f0f0f0; -fx-padding: 30px;");
        root.getChildren().addAll(faceGroup, toggleButton);

        // ── Scene & Stage ─────────────────────────────────────────────────────
        Scene scene = new Scene(root, 320, 340);
        primaryStage.setTitle("Smiley Face");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    // This method runs every time the button is clicked
    private void toggleFace() {
        if (isHappy) {
            // Switch to SAD face
            mouth.setStartAngle(20);     // flip the arc upside down
            mouth.setLength(140);
            mouth.setCenterY(mouth.getCenterY() + 15);  // move mouth down a bit
            toggleButton.setText("Make Happy");
            toggleButton.setStyle(
                "-fx-font-size: 16px;" +
                "-fx-padding: 10px 30px;" +
                "-fx-background-color: #e53935;" +
                "-fx-text-fill: white;" +
                "-fx-background-radius: 8px;"
            );
            isHappy = false;
        } else {
            // Switch back to HAPPY face
            mouth.setStartAngle(200);
            mouth.setLength(140);
            mouth.setCenterY(mouth.getCenterY() - 15);  // move mouth back up
            toggleButton.setText("Make Sad");
            toggleButton.setStyle(
                "-fx-font-size: 16px;" +
                "-fx-padding: 10px 30px;" +
                "-fx-background-color: #4CAF50;" +
                "-fx-text-fill: white;" +
                "-fx-background-radius: 8px;"
            );
            isHappy = true;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
