module SpellingBee {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens com.erciyes.spellingBee to javafx.fxml;
    exports com.erciyes.spellingBee;
}

