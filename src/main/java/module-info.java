module com.group4.petcenter {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;
    requires de.jensd.fx.glyphs.fontawesome;
    requires de.jensd.fx.glyphs.commons;
    
    opens com.group4.petcenter to javafx.fxml;
    opens com.group4.petcenter.Controllers to javafx.fxml;
    opens com.group4.petcenter.Controllers.Admin to javafx.fxml;
    exports com.group4.petcenter;
    exports com.group4.petcenter.Controllers;
    exports com.group4.petcenter.Controllers.Admin;
    exports com.group4.petcenter.Controllers.Saler;
    exports com.group4.petcenter.Models;
    exports com.group4.petcenter.Views;
}
