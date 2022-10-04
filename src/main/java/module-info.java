module com.example.pavel {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;
    requires org.postgresql.jdbc;
    requires junit;

    opens com.example.pavel.entity to javafx.fxml;
    opens com.example.pavel to javafx.fxml;
    exports com.example.pavel.service;
    exports com.example.pavel.entity;
    exports com.example.pavel;
    exports com.example.pavel.controllers;
    exports  com.example.pavel.dao;

    opens com.example.pavel.controllers to javafx.fxml;

}