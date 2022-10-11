package com.erciyes.spellingBee;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class DisplayStage extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        GamePane pane = new GamePane();
        Image icon = new Image("https://www.nytimes.com/games-assets/v2/metadata/sb-apple-touch-icon.png?v=v2204211537");
        ImageView imageView = new ImageView(icon);
        imageView.setFitHeight(30);
        imageView.setFitWidth(40);

        stage.setTitle("Spelling Bee");
        Pane root = new Pane();
        Scene scene = new Scene(root, 300, 200);
        scene.getStylesheets().add( getClass().getResource( "style.css" ).toExternalForm());
        stage.setScene(scene);
        Label heading = new Label("Welcome to Spelling Bee");
        stage.getIcons().add(icon);

        heading.setLayoutX(50);
        heading.setLayoutY(55);
        heading.getStyleClass().add("enter-heading");

        DropShadow ds = new DropShadow();
        ds.setColor(Color.YELLOW);
        ds.setOffsetX(5);
        ds.setOffsetY(5);
        ds.setRadius(5);
        ds.setSpread(0.2);
        heading.setEffect(ds);

        Image img = new Image("https://media1.giphy.com/avatars/Bzzzonder/mY6KXXT2aT8P.gif");
        ImageView imgView = new ImageView(img);
        imgView.setFitWidth(100);
        imgView.setFitHeight(50);
        Label gif = new Label();
        gif.setGraphic(imgView);
        gif.setLayoutX(105);
        gif.setLayoutY(5);


        Label lbl = new Label("Play Game");
        lbl.setLayoutX(115);
        lbl.setLayoutY(160);
        lbl.getStyleClass().add("enter-label");
        pane.createTransition(lbl);

        Button btn = new Button();
        btn.setLayoutX(115);
        btn.setLayoutY(110);
        btn.getStyleClass().add("enter-button");
        btn.setOnAction(e -> {
            try {
                new GameStage();
                stage.hide();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        pane.createEffect(btn);
        btn.setGraphic(imageView);
        root.getStyleClass().add("root");
        root.getChildren().addAll(btn, heading, lbl, gif);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}


