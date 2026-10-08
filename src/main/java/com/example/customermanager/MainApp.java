package com.example.customermanager;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainApp extends Application {

    private final ObservableList<Customer> customers = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Customer Manager");

        // --- Navigation: MenuBar ---
        Menu fileMenu = new Menu("File");
        MenuItem closeItem = new MenuItem("Close");
        closeItem.setOnAction(e -> primaryStage.close());
        fileMenu.getItems().add(closeItem);
        MenuBar menuBar = new MenuBar(fileMenu);

        // --- Input Controls ---
        Label nameLabel = new Label("Customer Name:");
        TextField nameField = new TextField();
        nameField.setPromptText("e.g., Mary Banda");
        nameLabel.setLabelFor(nameField);

        Label provinceLabel = new Label("Province:");
        ComboBox<String> provinceBox = new ComboBox<>();
        provinceBox.getItems().addAll("Central", "Copperbelt", "Eastern", "Luapula", "Lusaka", "Muchinga", "Northern", "North-Western", "Southern", "Western");
        provinceBox.setPromptText("Choose a province");

        Label remarksLabel = new Label("Remarks:");
        TextArea remarksArea = new TextArea();
        remarksArea.setPromptText("Enter customer remarks");
        remarksArea.setWrapText(true);
        remarksArea.setPrefRowCount(3);

        Button saveButton = new Button("Save Customer");
        saveButton.setDefaultButton(true); // Pressing Enter triggers save

        Button deleteButton = new Button("Delete Selected");

        Label statusLabel = new Label();
        statusLabel.setStyle("-fx-text-fill: blue;");

        // --- Data Display: TableView ---
        TableView<Customer> table = new TableView<>();
        table.setItems(customers);

        TableColumn<Customer, String> nameCol = new TableColumn<>("Customer Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setPrefWidth(200);

        TableColumn<Customer, String> provinceCol = new TableColumn<>("Province");
        provinceCol.setCellValueFactory(new PropertyValueFactory<>("province"));
        provinceCol.setPrefWidth(150);

        table.getColumns().addAll(nameCol, provinceCol);

        // --- Action Handlers ---

        // Save Action (Validation & Logic)
        saveButton.setOnAction(event -> {
            String name = nameField.getText().trim();
            if (name.isEmpty()) {
                statusLabel.setText("Enter the customer name.");
                nameField.requestFocus();
                return;
            }

            String province = provinceBox.getValue();
            if (province == null) {
                statusLabel.setText("Choose a province.");
                provinceBox.requestFocus();
                return;
            }

            // Add customer and give feedback
            customers.add(new Customer(name, province));
            statusLabel.setText("Customer saved.");

            // Clear inputs after success
            nameField.clear();
            provinceBox.setValue(null);
            remarksArea.clear();
            nameField.requestFocus();
        });

        // Delete Action (Confirmation Alert)
        deleteButton.setOnAction(event -> {
            Customer selected = table.getSelectionModel().getSelectedItem();
            if (selected == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please select a customer to delete.");
                alert.showAndWait();
                return;
            }

            ButtonType deleteBtn = new ButtonType("Delete");
            Alert ask = new Alert(Alert.AlertType.CONFIRMATION, "Delete the selected customer?", deleteBtn, ButtonType.CANCEL);
            ask.setHeaderText("Confirm deletion");

            if (ask.showAndWait().orElse(ButtonType.CANCEL) == deleteBtn) {
                customers.remove(selected);
                statusLabel.setText("Customer deleted.");
            }
        });

        // --- Layout Structure ---
        GridPane formLayout = new GridPane();
        formLayout.setHgap(10);
        formLayout.setVgap(10);
        formLayout.setPadding(new Insets(10));

        formLayout.add(nameLabel, 0, 0);
        formLayout.add(nameField, 1, 0);
        formLayout.add(provinceLabel, 0, 1);
        formLayout.add(provinceBox, 1, 1);
        formLayout.add(remarksLabel, 0, 2);
        formLayout.add(remarksArea, 1, 2);

        HBox buttonBox = new HBox(10, saveButton, deleteButton);
        buttonBox.setAlignment(Pos.CENTER_LEFT);

        VBox leftPane = new VBox(10, formLayout, buttonBox, statusLabel);
        leftPane.setPadding(new Insets(10));

        VBox rightPane = new VBox(10, new Label("Saved Customers:"), table);
        rightPane.setPadding(new Insets(10));
        HBox.setHgrow(table, Priority.ALWAYS);

        HBox mainContent = new HBox(20, leftPane, rightPane);
        mainContent.setPadding(new Insets(10));

        VBox rootLayout = new VBox(menuBar, mainContent);

        Scene scene = new Scene(rootLayout, 700, 450);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Initial focus
        nameField.requestFocus();
    }

    public static void main(String[] args) {
        launch(args);
    }
}