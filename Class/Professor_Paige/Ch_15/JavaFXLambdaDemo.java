import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
//import javafx.scene.paint.Color; //not used now, maybe later on!
import javafx.stage.Stage;

public class JavaFXLambdaDemo extends Application {

    // Sample data class
    public static class Person {
        private final SimpleStringProperty firstName;
        private final SimpleStringProperty lastName;
        private final SimpleStringProperty email;

        public Person(String firstName, String lastName, String email) {
            this.firstName = new SimpleStringProperty(firstName);
            this.lastName = new SimpleStringProperty(lastName);
            this.email = new SimpleStringProperty(email);
        }

        public String getFirstName() { return firstName.get(); }
        public String getLastName() { return lastName.get(); }
        public String getEmail() { return email.get(); }

        public SimpleStringProperty firstNameProperty() { return firstName; }
        public SimpleStringProperty lastNameProperty() { return lastName; }
    }

    private final ObservableList<Person> people = FXCollections.observableArrayList(
            new Person("Ava", "AppleScript", "ava@example.com"),
            new Person("Ben", "Babbage", "ben@example.com"),
            new Person("Christ", "CoffeeScript", "christ@example.com")
    );

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFX CSC 190 Lambda Expressions Demo");

        // ==================== Controls ====================
        Button btnHello = new Button("WHAT'S UP?");
        Button btnAdd = new Button("Add Person");
        Button btnDelete = new Button("Delete Selected");
        Button btnClear = new Button("Clear Log");

        TextField firstNameField = new TextField();
        firstNameField.setPromptText("First Name");

        TextField lastNameField = new TextField();
        lastNameField.setPromptText("Last Name");

        TextField emailField = new TextField();
        emailField.setPromptText("Email");

        TextArea logArea = new TextArea();
        logArea.setEditable(false);
        logArea.setPrefHeight(150);

        // TableView
        TableView<Person> tableView = new TableView<>();
        TableColumn<Person, String> firstCol = new TableColumn<>("First Name");
        TableColumn<Person, String> lastCol = new TableColumn<>("Last Name");
        TableColumn<Person, String> emailCol = new TableColumn<>("Email");

        firstCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        tableView.getColumns().addAll(firstCol, lastCol, emailCol);
        tableView.setItems(people);

        // ==================== Lambda Expressions Examples ====================

        // 1. Simple button click with lambda
        btnHello.setOnAction(e -> log("WHAT'S UP?! 🌟"));

        // 2. Button with multiple statements
        btnAdd.setOnAction(e -> {
            String first = firstNameField.getText().trim();
            String last = lastNameField.getText().trim();
            String email = emailField.getText().trim();

            if (!first.isEmpty() && !last.isEmpty()) {
                people.add(new Person(first, last, email.isEmpty() ? "N/A" : email));
                log("Added: " + first + " " + last);
                clearInputFields(firstNameField, lastNameField, emailField);
            } else {
                log("Error: First and Last name are required!");
            }
        });

        // 3. Delete button with confirmation
        btnDelete.setOnAction(e -> {
            Person selected = tableView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Delete Person");
                alert.setContentText("Delete " + selected.getFirstName() + " " + selected.getLastName() + "?");

                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        people.remove(selected);
                        log("Deleted: " + selected.getFirstName() + " " + selected.getLastName());
                    }
                });
            } else {
                log("Please select a person to delete.");
            }
        });

        // 4. Clear log button
        btnClear.setOnAction(e -> logArea.clear());

        // 5. TableView selection change listener (ChangeListener with lambda)
        tableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        log("Selected: " + newValue.getFirstName() + " " + newValue.getLastName());
                    }
                }
        );

        // 6. TextField live search / filter using lambda
        firstNameField.textProperty().addListener((obs, oldText, newText) -> {
            if (newText.length() > 2) {
                log("Live search: Looking for names starting with '" + newText + "'");
            }
        });

        // 7. Method Reference example
        btnHello.setOnMouseEntered(e -> log("Hovered over WHAT'S UP?! button"));

        // ==================== Layout ====================
        VBox inputBox = new VBox(10,
                new Label("Add New Person:"),
                firstNameField,
                lastNameField,
                emailField,
                new HBox(10, btnAdd, btnDelete)
        );
        inputBox.setPadding(new Insets(10));

        HBox buttonBox = new HBox(10, btnHello, btnClear);
        buttonBox.setAlignment(Pos.CENTER);

        VBox root = new VBox(15);
        root.setPadding(new Insets(15));
        root.getChildren().addAll(
                new Label("JavaFX Lambda Demo"),
                buttonBox,
                inputBox,
                new Label("People Table:"),
                tableView,
                new Label("Log:"),
                logArea
        );

        Scene scene = new Scene(root, 700, 650);
        primaryStage.setScene(scene);
        primaryStage.show();

        log("Application started. Try clicking buttons and selecting rows!");
    }

    // Helper method to add to log (demonstrates method reference usage)
    private void log(String message) {
        // This method can be referenced with this::log
        System.out.println(message);
    }

    private void clearInputFields(TextField... fields) {
        for (TextField field : fields) {
            field.clear();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}