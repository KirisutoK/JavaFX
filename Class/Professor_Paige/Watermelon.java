import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;

public class Watermelon extends Application {

    @Override
    public void start(Stage primaryStage) {

        Pane pane = new Pane();

        // ── Rind (dark green outer arc) ───────────────────────────────────
        Arc rind = new Arc(250, 300, 200, 200, 0, 180);
        rind.setType(ArcType.ROUND);
        rind.setFill(Color.web("#2D6A2D"));   // dark green
        rind.setStroke(Color.web("#1A3D1A"));
        rind.setStrokeWidth(2);

        // ── White inner rind ──────────────────────────────────────────────
        Arc whiteRind = new Arc(250, 300, 185, 185, 0, 180);
        whiteRind.setType(ArcType.ROUND);
        whiteRind.setFill(Color.web("#E8F5D0"));  // pale greenish white

        // ── Pink flesh ───────────────────────────────────────────────────
        Arc flesh = new Arc(250, 300, 170, 170, 0, 180);
        flesh.setType(ArcType.ROUND);
        flesh.setFill(Color.web("#F4657A"));      // watermelon pink

        // ── Seeds (small black ovals scattered across the flesh) ──────────
        // Each: centerX, centerY, radiusX, radiusY, rotation
        double[][] seeds = {
            {160, 210, 8, 5,  -20},
            {210, 170, 8, 5,   10},
            {260, 155, 8, 5,    0},
            {315, 168, 8, 5,  -10},
            {365, 200, 8, 5,   20},
            {195, 250, 8, 5,  -15},
            {275, 240, 8, 5,    5},
            {340, 248, 8, 5,   15},
            {230, 195, 8, 5,  -10},
        };

        pane.getChildren().addAll(rind, whiteRind, flesh);

        for (double[] s : seeds) {
            Ellipse seed = new Ellipse(s[0], s[1], s[2], s[3]);
            seed.setFill(Color.web("#1A1A1A"));
            seed.setRotate(s[4]);
            pane.getChildren().add(seed);
        }

        // ── Flat bottom edge (straight line across the bottom of the slice) 
        Line bottom = new Line(50, 300, 450, 300);
        bottom.setStroke(Color.web("#1A3D1A"));
        bottom.setStrokeWidth(2);
        pane.getChildren().add(bottom);

        // ── Scene & Stage ─────────────────────────────────────────────────
        Scene scene = new Scene(pane, 500, 340, Color.VIOLET);
        primaryStage.setTitle("Watermelon Slice");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
