package com.example.pavel.controllers;

import com.example.pavel.MainApplication;
import com.example.pavel.entity.Person;
import com.example.pavel.service.PersonService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class WelcomeController implements Initializable {

    @FXML
    private ListView<Person> myListView;

    @FXML
    private Label userSelectLabel;

    @FXML
    private Button loginButton;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;

    Person currentUser;
    static Person user;


    public void switchToMainScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/pavel/MainScene.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void switchToRegistrationStage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/pavel/addPersonStage.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Stage newWindow = new Stage();
        newWindow.setTitle("Registration");
        newWindow.setScene(new Scene(root));
        newWindow.getIcons().add(new Image(Objects.requireNonNull(MainApplication.class.getResourceAsStream("/icons/registerPersonIcon.png"))));
        newWindow.initModality(Modality.WINDOW_MODAL);
        newWindow.initOwner(stage);
        newWindow.show();
    }

    public void switchToEditStage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/pavel/editPersonStage.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Stage newWindow = new Stage();

        newWindow.setTitle("Editing " + currentUser.getName() + " " + currentUser.getSecondName() + "...");
        newWindow.setScene(new Scene(root));
        newWindow.getIcons().add(new Image(Objects.requireNonNull(MainApplication.class.getResourceAsStream("/icons/registerPersonIcon.png"))));
        newWindow.initModality(Modality.WINDOW_MODAL);
        newWindow.initOwner(stage);
        newWindow.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginButton.setDisable(true);
        editButton.setDisable(true);
        deleteButton.setDisable(true);
        myListView.getItems().addAll(new PersonService().getAll());
        myListView.getSelectionModel().selectedItemProperty().addListener((observableValue, person, t1) -> {
            currentUser = myListView.getSelectionModel().getSelectedItem();
            userSelectLabel.setText(currentUser.toString());
            user = myListView.getSelectionModel().getSelectedItem();
            loginButton.setDisable(false);
            editButton.setDisable(false);
            deleteButton.setDisable(false);
        });
    }

    public void refreshPersonList(){
        // TODO mylistview currentUser null

        myListView.getItems().clear();
        myListView.getItems().addAll(new PersonService().getAll());
    }

    public void deletePerson(){
        PersonService ps = new PersonService();
        ps.remove(currentUser);
        refreshPersonList();
    }
}
