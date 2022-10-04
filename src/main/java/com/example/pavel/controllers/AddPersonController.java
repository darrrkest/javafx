package com.example.pavel.controllers;

import com.example.pavel.entity.Person;
import com.example.pavel.service.PersonService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class AddPersonController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField secondNameField;

    @FXML
    private Label nullFieldsLabel;

    @FXML
    private AnchorPane registrationPane;

    public void confirm(){
        if (!isFieldsNull(nameField,secondNameField)){
            PersonService ps = new PersonService();
            ps.add(new Person(nameField.getText(),secondNameField.getText()));
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
