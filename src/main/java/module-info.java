module uk.ac.cam.group09.mycricket {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires javafx.web;
    requires org.apache.httpcomponents.httpclient;
    requires org.apache.httpcomponents.httpcore;
    requires javax.json.api;
    requires org.json;

    exports uk.ac.cam.group09.mycricket;
    exports uk.ac.cam.group09.mycricket.controllers;
    opens uk.ac.cam.group09.mycricket.controllers to javafx.fxml;
}