module myCricketJavaFX {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    opens uk.ac.cam.group09.mycricket to javafx.fxml;
    exports uk.ac.cam.group09.mycricket;
    exports uk.ac.cam.group09.mycricket.controllers;
    opens uk.ac.cam.group09.mycricket.controllers to javafx.fxml;
}