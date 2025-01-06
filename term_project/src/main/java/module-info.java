module com.project {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;

    opens com.project to javafx.fxml,javafx.base;
    exports com.project;
}
