module uk.ac.cam.group09.mycricket {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires javafx.web;
    requires org.apache.httpcomponents.httpclient;
    requires org.apache.httpcomponents.httpcore;

    opens uk.ac.cam.group09.mycricket to javafx.fxml;
    exports uk.ac.cam.group09.mycricket;
}