module com.example.logpraser {
    requires jdk.compiler;


    opens com.example.logpraser to javafx.fxml;
    exports com.example.logpraser;
}