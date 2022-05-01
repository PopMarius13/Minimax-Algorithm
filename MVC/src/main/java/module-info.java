module com.example.tema1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires lombok;
    requires java.sql;
    requires org.postgresql.jdbc;

    opens image;
    exports com.example.tema1;
    opens com.example.tema1 to javafx.fxml;
}