module com.epl.parceljavafxtp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.epl.parceljavafxtp to javafx.fxml;
    opens com.epl.parceljavafxtp.controller to javafx.fxml;
    exports com.epl.parceljavafxtp;
}
