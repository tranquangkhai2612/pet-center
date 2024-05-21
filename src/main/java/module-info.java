module com.group4.petcenter {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;

    opens com.group4.petcenter to javafx.fxml;
    exports com.group4.petcenter;
    exports com.group4.petcenter.Controllers;
}
