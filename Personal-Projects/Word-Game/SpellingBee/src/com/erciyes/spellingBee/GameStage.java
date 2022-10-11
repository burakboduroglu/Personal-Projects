package com.erciyes.spellingBee;

import com.erciyes.spellingBee.GamePane;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class GameStage {
    public GameStage() throws IOException {
        Stage subStage = new Stage();
        GamePane root = new GamePane();
        root.createPane();
        root.setStyle("-fx-background-color: #FBFCFC");
        stageOperations(subStage, root);
    }

    public void stageOperations(Stage stage, Pane root) {
        /* Stage icon */
        Scene scene = new Scene(root, 600, 600);
        scene.getStylesheets().add( getClass().getResource( "style.css" ).toExternalForm());
        Image icon = new Image("https://www.nytimes.com/games-assets/v2/metadata/sb-apple-touch-icon.png?v=v2204211537");
        stage.setScene(scene);
        stage.setTitle("Spelling Bee");
        stage.getIcons().add(icon);
        stage.setHeight(600);
        stage.setWidth(600);
        stage.setResizable(false);

        stage.show();
    }
}