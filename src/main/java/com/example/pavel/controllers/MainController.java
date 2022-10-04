package com.example.pavel.controllers;

import com.example.pavel.MainApplication;
import com.example.pavel.entity.Animal;
import com.example.pavel.entity.Person;
import com.example.pavel.service.AnimalService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private final Person user = WelcomeController.user;
    @FXML
    private TableView<Animal> TableView;

    @FXML
    private TableColumn<Animal, String> typeColumn;

    @FXML
    private TableColumn<Animal, String> nicknameColumn;

    @FXML
    private TableColumn<Animal, String> colorColumn;

    @FXML
    private Label personAnimalLabel;

    @FXML
    private Button deleteAnimalButton;

    @FXML
    private Button editAnimalButton;

    private Animal currentAnimal;

    static public Animal animal;


    public void switchToWelcomeScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/pavel/WelcomeScene.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableInit();
        editAnimalButton.setDisable(true);
        deleteAnimalButton.setDisable(true);
        personAnimalLabel.setText("Hello " + user.toString() + "! There are your animals");
        TableView.getSelectionModel().selectedItemProperty().addListener((observableValue, person, t1) -> {
            currentAnimal = TableView.getSelectionModel().getSelectedItem();
            animal = currentAnimal;
            deleteAnimalButton.setDisable(false);
            editAnimalButton.setDisable(false);
        });


    }

    public void refresh() {
        TableView.getItems().clear();
        TableView.setItems(FXCollections.observableList(new AnimalService().getByPerson(user)));
    }

    private void tableInit(){
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        nicknameColumn.setCellValueFactory(new PropertyValueFactory<>("nickname"));
        colorColumn.setCellValueFactory(new PropertyValueFactory<>("color"));
        TableView.setItems(FXCollections.observableList(new AnimalService().getByPerson(user)));
        typeColumn.setResizable(false);
        nicknameColumn.setResizable(false);
        colorColumn.setResizable(false);
        TableView.setPlaceholder(new Label("There are no animals! Add first one."));

    }
    public void switchAddAnimalStage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/pavel/addAnimalStage.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Stage newWindow = new Stage();
        newWindow.setTitle("Adding new animal");
        newWindow.setScene(new Scene(root));
        newWindow.getIcons().add(new Image(Objects.requireNonNull(MainApplication.class.getResourceAsStream("/icons/addAnimalIcon.png"))));
        newWindow.initModality(Modality.WINDOW_MODAL);
        newWindow.initOwner(stage);
        newWindow.show();
    }

    public void switchEditAnimalStage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/pavel/editAnimalStage.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Stage newWindow = new Stage();
        newWindow.setTitle("Editing " + currentAnimal.getNickname() + "...");
        newWindow.setScene(new Scene(root));
        newWindow.getIcons().add(new Image(Objects.requireNonNull(MainApplication.class.getResourceAsStream("/icons/addAnimalIcon.png"))));
        newWindow.initModality(Modality.WINDOW_MODAL);
        newWindow.initOwner(stage);
        newWindow.show();
    }

    public void deleteAnimal(){
        AnimalService animalService = new AnimalService();
        animalService.remove(currentAnimal);
        refresh();
    }
}