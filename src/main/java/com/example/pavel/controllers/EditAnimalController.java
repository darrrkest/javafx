package com.example.pavel.controllers;

import com.example.pavel.entity.Animal;
import com.example.pavel.service.AnimalService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EditAnimalController implements Initializable {

    @FXML
    private AnchorPane addAnimalPane;

    @FXML
    private TextField typeField;

    @FXML
    private TextField nicknameField;

    @FXML
    private TextField colorField;

    @FXML
    private Label nullFieldsLabel;

    public void confirm(){
        if (!isFieldsNull(typeField, nicknameField, colorField)){
            AnimalService as = new AnimalService();
            as.update(new Animal(MainController.animal.getId(),typeField.getText(),nicknameField.getText(), colorField.getText(),WelcomeController.user.getId()));
            closeEditAnimalStage();
        }
        else{
            nullFieldsLabel.setText("Fields can not be null");
        }
    }

    public void closeEditAnimalStage() {
        Stage stage = (Stage)addAnimalPane.getScene().getWindow();
        stage.close();
    }
    private boolean isFieldsNull(TextField type, TextField nickname, TextField color){
        return (type.getText().equals("")) || (nickname.getText().equals("")) || (color.getText().equals(""));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typeField.setText(MainController.animal.getType());
        nicknameField.setText(MainController.animal.getNickname());
        colorField.setText(MainController.animal.getColor());
    }
}
