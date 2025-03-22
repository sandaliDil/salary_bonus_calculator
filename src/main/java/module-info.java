module com.example.salary_bonus_calculatordemo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.graphics;
    requires javafx.media;
    requires javafx.swing;


    // Third-party libraries
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;


    // Core Java modules
    requires java.desktop;
    requires java.sql;
    requires annotations;

    opens com.example.salary_bonus_calculatordemo to javafx.fxml, javafx.graphics;
    opens com.example.salary_bonus_calculatordemo.controller to javafx.fxml;
    exports com.example.salary_bonus_calculatordemo to javafx.graphics;

    exports com.example.salary_bonus_calculatordemo.controller;
    exports com.example.salary_bonus_calculatordemo.model;

    exports com.example.salary_bonus_calculatordemo.repository;
    exports com.example.salary_bonus_calculatordemo.database;

}


