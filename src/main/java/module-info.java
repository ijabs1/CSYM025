module com.example.csym025 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.csym025 to javafx.fxml;
    exports com.example.csym025;
}