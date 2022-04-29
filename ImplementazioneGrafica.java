package com.example.carrace12;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
/*
   Leonte Vlad Vasile
   29/04/2022
   Funzionamento di base della grafica con controlli base
 */

public class ImplementazioneGrafica extends Application {
    int newY,newX;

    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        InputStream stream = new FileInputStream("C:\\Users\\vladv\\Desktop\\mappa2.png");
        Image img = new Image(stream);
        ImageView background = new ImageView();
        background.setImage(img);
        background.setX(0);
        background.setY(-700);
        background.setFitWidth(500);
        background.setFitHeight(1500);
        //imgView.setPreserveRatio(true);

        stream = new FileInputStream("C:\\Users\\vladv\\Desktop\\car.png");
        img = new Image(stream);
        ImageView car = new ImageView();
        car.setImage(img);
        car.setX(200);
        car.setY(400);
        car.setPreserveRatio(true);



        TranslateTransition tr = new TranslateTransition(Duration.millis(3000));
        tr.setNode(background);
        //tr.setDelay(Duration.millis(1000));
        tr.setByY(700);
        tr.setCycleCount(TranslateTransition.INDEFINITE);
        tr.setInterpolator(Interpolator.LINEAR);
        tr.play();

        Group root = new Group(background,car);
        Scene scene = new Scene(root, 600, 500);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                if(event.getCode()== KeyCode.W){
                    newY=newY-3;
                    car.setTranslateY(newY);

                }
                if(event.getCode()== KeyCode.S){
                    newY=newY+3;
                    car.setTranslateY(newY);
                }
                if(event.getCode()== KeyCode.A){
                    newX=newX-3;
                    car.setTranslateX(newX);
                }
                if(event.getCode()== KeyCode.D){
                    newX=newX+3;
                    car.setTranslateX(newX);
                }
            }
        });

        stage.setTitle("Car Race");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}