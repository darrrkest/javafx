package com.example.pavel.controllers;

import com.example.pavel.entity.Person;
import com.example.pavel.service.PersonService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EditPersonController implements Initializable {

    @FXML
    private TextField nameField;

    @FXML
    private TextField secondNameField;

    @FXML
    private Label nullFieldsLabel;

    @FXML
    private AnchorPane registrationPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameField.setText(WelcomeController.user.getName());
        secondNameField.setText(WelcomeController.user.getSecondName());
    }

    public void confirm(){
        if (!isFieldsNull(nameField,secondNameField)){
            PersonService ps = new PersonService();
            ps.update(new Person(WelcomeController.user.getId(), nameField.getText(), secondNameField.getText()));
            closeRegistrationStage();
        }
        else{
            nullFieldsLabel.setText("Fields can not be null");
        }
    }

    public void closeRegistrationStage() {
        Stage stage = (Stage)registrationPane.getScene().getWindow();
        stage.close();
    }
    private boolean isFieldsNull(TextField name, TextField secondName){
        return ((name.getText().equals(""))) || (secondName.getText().equals(""));
    }

}
