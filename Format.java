// This format is based on Better Comments Plug-ins which is installed from IntelliJ IDEA to provide more friendly way to read the code.

public class Main extends Application {
    @Override public void start(Stage stage) throws Exception {
        // ======== LAYOUTS (ROOT NODES) ======== \\
        Pane Pane01 = new Pane();
        Scene Scene01 = new Scene(Pane01);

        // ======== OBJECTS (NODES) ======== \\

        // +[STUFF]+

        // ======== STAGE>SCENE ======== \\
        stage.setScene(Scene01);
        stage.setWidth(700);
        stage.setHeight(700);
        stage.setTitle("PUT STUFF HERE");
        stage.setResizable(false);
        stage.show(); // Displays the screen or box
    }

    // ======== METHODS ========= \\
    public static void main(String[] args) {
        launch(args);
    }
}
}

// [{ABSTRACT}]

// --- @OVERRIDE ---

// INTERFACENAME <==== [INTERFACE]

// [CLASSES]

// TODO <================================ LEFT AT BLABLABLA

// ! ERROR HERE

// // THIS COMMENT IS IRRELEVANT

//? WHAT DOES THIS DO

/*

public interface Format { // A Template/Contract that gives requirements for a class
    //==========GETTERS==========\\ NOTE: TO ACCESS THE PRIVATE VARIABLES AND USE IT TO OTHER FILES

    //==========SETTERS==========\\ NOTE: CHANGES THE VARIABLES ON THIS FILE

    //===========METHODS===========\\ NOTE: THIS ARE THE SPECIFIC PROCESS IN ORDER TO MEET THE DESIRED RESULTS
}

// Methods and Interface is static by default

*/
